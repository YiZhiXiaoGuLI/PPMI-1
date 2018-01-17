import dataset.Dataset;
import dataset.model.Root;
import dataset.model.Word;
import matrix.CoocuranceMatrix;
import matrix.PPMI;
import qa.Questions;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class PMI {


    public static void main(String[] args) throws IOException {
        Dataset dataset = new Dataset();

        Root wackypediaSections = dataset.getWackypediaSectionsWithoutXml();
//      //dataset.showAllSections(wackypediaSections);

        System.out.println("------SHOW SECOND COLUMN WITH FILTERING------------");
        List<Word> wordList = dataset.getAllWords(wackypediaSections);
//      //wordList.forEach(System.out::println);


        System.out.println("------------ GET QUESTIONS AND ANSWERS FROM FILE------------------");
        Questions questions = new Questions();
        questions.getAllQuestions().forEach(System.out::println);


        System.out.println("------------ ADD Question to Initial Dataset [LIBRARY] ------------");
        wordList = dataset.getAllWordsWithQuestionWord(wordList, questions); // all words with question words // to check algorithm

        System.out.println("------------ GET OCCURENCE MATRIX------------------");
        //TODO: sentencje trzeba zrobic bo raczej są zle - w senise zdania bo robienie matrixa dziala ale mam zle sentencje jakby
        // potrzebowal bym liste zdan tych logicznych - chyba je wyswietlasz ale niewiem czy to te
        CoocuranceMatrix coocuranceMatrix = new CoocuranceMatrix();
        //coocuranceMatrix.createCoocurance(wordList);

        //todo: do metody przerobic
        PPMI ppmi = new PPMI();
        List<Word> wordListwithNoRep = coocuranceMatrix.createLiblary(wordList);
        short[][] coocurnaceMatrixxx = coocuranceMatrix.createCoocurance(wordList);
        coocuranceMatrix.printMatrix(coocurnaceMatrixxx, wordListwithNoRep.size());

        double[][] ppmiMatrix = new double [wordListwithNoRep.size()][wordListwithNoRep.size()];

        System.out.println("AAA");

        for(int i =0; i<10003;i++)
        {
            for (int j=0; j<10003; j++) {
                if(coocurnaceMatrixxx[i][j]>0) {
                    //System.out.print(coocurnaceMatrixxx[i][j] + " ");
                    ppmiMatrix[i][j] = ppmi.calculatePPMI(wordListwithNoRep.get(i).getWord(), wordListwithNoRep.get(j).getWord(), wordList, coocurnaceMatrixxx, wordListwithNoRep);


                }
                if( (ppmiMatrix[i][j])>(float)0)
                {
                     System.out.println(ppmiMatrix[i][j]);
                }
            }
            System.out.println("");
        }




    }

}
