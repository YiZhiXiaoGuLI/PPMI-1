import model.CoocuranceMatrix;
import model.Root;
import model.WordWithPosition;
import qa.Questions;

import java.io.*;
import java.util.List;

public class PMI {


    public static void main(String[] args) throws IOException {
        Dataset dataset = new Dataset();

        Root wackypediaSections = dataset.getWackypediaSections();
        dataset.showAllSections(wackypediaSections);
        List<String> firstColumn = dataset.getFirstColumnFromSections(wackypediaSections);

        System.out.println("------------------------------------");
        List<WordWithPosition> wordWithPositionList = dataset.convertToWordWithPositionListWithFiltering(wackypediaSections);

        System.out.println("------SHOW FIRST COLUMN WITH POSITION------------");
        wordWithPositionList.forEach(System.out::println);



        System.out.println("------------ GET QUESTIONS AND ANSWERS FROM FILE------------------");
        Questions questions = new Questions();
        questions.getQuestionsAndAnswersFromFile();

        System.out.println("------------ GET OCCURENCE MATRIX------------------");
        //TODO: sentencje trzeba zrobic bo raczej są zle - w senise zdania bo robienie matrixa dziala ale mam zle sentencje jakby
        // potrzebowal bym liste zdan tych logicznych - chyba je wyswietlasz ale niewiem czy to te
        CoocuranceMatrix coocuranceMatrix = new CoocuranceMatrix();
        coocuranceMatrix.createCoocurance(wordWithPositionList);
    }

}
