package matrix;

import dataset.model.Word;
import java.math.*;
import java.util.List;

import static matrix.CoocuranceMatrix.findPosition;

public class PPMI {


    private int calculateNumberOfOccurenses(List<Word> allwords, String word)
    {
        int wynik = 0;

        for(Word item : allwords)
        {
            if((item.getWord().equals(word)))
            {
                wynik++;
            }
        }
        return wynik;
    }

    private double calculatePMI(String word1, String word2, List<Word> allwords, Short[][] coocurenceMatrix, List<Word> dedulicateWords)
    {
        double PMI = 0;
        int x,y =0;
        x = findPosition(word1,dedulicateWords);
        y = findPosition(word2,dedulicateWords);
        //pmi = log(coocurence/p[word1]*p[word2]) -- z tego wzoru robię PMI,
        // p to prawdompdobeinstwo wystapienia slowa word we wszystkich zdaniach
        PMI = Math.log10((coocurenceMatrix[x][y])/probability(word1, allwords)*probability(word2,allwords));

        return  PMI;
    }
    private float probability(String word, List<Word> allwords)
    {
        return calculateNumberOfOccurenses(allwords, word)/(allwords.size());
    }

    private double calculatePPMI(String word1, String word2, List<Word> allwords, Short[][] coocurenceMatrix, List<Word> dedulicateWords)
    {
        if(calculatePMI(word1,word2,allwords,coocurenceMatrix,dedulicateWords)<0)
        {
            return 0;
        }
        else
        {
            return calculatePMI(word1,word2,allwords,coocurenceMatrix,dedulicateWords);
        }
    }
}
