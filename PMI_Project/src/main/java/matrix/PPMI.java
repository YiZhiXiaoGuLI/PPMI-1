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

//        int wyniktest = wynik;
//        if(wyniktest>50)
//        {
//            System.out.println("Number of occurences: "+word+" " + wyniktest);
//        }
        return wynik;
    }

    private float calculatePMI(String word1, String word2, List<Word> allwords, short[][] coocurenceMatrix, List<Word> dedulicateWords)
    {
        float PMI = 0f;
        int x,y =0;
        x = findPosition(word1,dedulicateWords);
        y = findPosition(word2,dedulicateWords);
        //pmi = log(coocurence/p[word1]*p[word2]) -- z tego wzoru robię PMI,
        // p to prawdompdobeinstwo wystapienia slowa word we wszystkich zdaniach
        float prob1 = probability(word1, allwords);
        float prob2 = probability(word2, allwords);



        PMI = (float) Math.log((coocurenceMatrix[x][y])/(probability(word1, allwords)*probability(word2,allwords)));
      if(coocurenceMatrix[x][y]>0) {
           //System.out.println("PMI=log(" +coocurenceMatrix[x][y] +")/ ("+ probability(word1,allwords)+")*("+probability(word2,allwords)+")");
//          System.out.println("cooc" + coocurenceMatrix[x][y]);
        //  System.out.println("multi" + (coocurenceMatrix[x][y])/(probability(word1, allwords)*probability(word2,allwords))*10);

      }

        return  PMI;
    }
    private float probability(String word, List<Word> allwords)
    {
        float wynik = (float)calculateNumberOfOccurenses(allwords, word)/(allwords.size());
        return wynik;
    }

    public double calculatePPMI(String word1, String word2, List<Word> allwords, short[][] coocurenceMatrix, List<Word> dedulicateWords)
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
