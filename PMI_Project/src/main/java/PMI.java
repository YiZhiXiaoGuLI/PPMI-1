import model.CoocuranceMatrix;
import model.Root;
import model.WordWithPosition;

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
        CoocuranceMatrix coocuranceMatrix = new CoocuranceMatrix();
        coocuranceMatrix.createCoocurance(wordWithPositionList);
    }

}
