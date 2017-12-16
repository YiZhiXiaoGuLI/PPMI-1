import dataset.Dataset;
import matrix.CoocuranceMatrix;
import dataset.model.Root;
import dataset.model.Word;
import qa.Questions;

import java.io.*;
import java.util.List;

public class PMI {


    public static void main(String[] args) throws IOException {
        Dataset dataset = new Dataset();

        Root wackypediaSections = dataset.getWackypediaSections();
        dataset.showAllSections(wackypediaSections);
//        List<String> firstColumn = dataset.getSecondColumnFromSections(wackypediaSections);


        System.out.println("------SHOW SECOND COLUMN WITH FILTERING------------");
        List<Word> wordList = dataset.convertToWordListWithFiltering(wackypediaSections);
        wordList.forEach(System.out::println);


        System.out.println("------------ GET QUESTIONS AND ANSWERS FROM FILE------------------");
        Questions questions = new Questions();
        questions.getAllQuestions().forEach(System.out::println);


        System.out.println("------------ GET OCCURENCE MATRIX------------------");
        //TODO: sentencje trzeba zrobic bo raczej są zle - w senise zdania bo robienie matrixa dziala ale mam zle sentencje jakby
        // potrzebowal bym liste zdan tych logicznych - chyba je wyswietlasz ale niewiem czy to te
        CoocuranceMatrix coocuranceMatrix = new CoocuranceMatrix();
        coocuranceMatrix.createCoocurance(wordList);
    }

}
