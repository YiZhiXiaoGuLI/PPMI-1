import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.CoocuranceMatrix;
import model.Root;
import model.WordWithPosition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PMI {

    private static final String PATH_TO_WACKYPEDIA_FILE = "src/main/resources/public/wackypedia_en1_with_root_word_5k";
    private static final String SENTENCE_STOP_WORD_P = "P";

    public static void main(String[] args) throws IOException {

        Root wackypediaSections = getWackypediaSections();
        showAllSections(wackypediaSections);
        List<String> firstColumn = getFirstColumnFromSections(wackypediaSections);

        System.out.println("------------------------------------");
        List<WordWithPosition> wordWithPositionList = convertToWordWithPositionListWithFiltering(wackypediaSections);

        System.out.println("------SHOW FIRST COLUMN WITH POSITION------------");
        wordWithPositionList.forEach(System.out::println);
        CoocuranceMatrix coocuranceMatrix = new CoocuranceMatrix();
        coocuranceMatrix.createCoocurance(wordWithPositionList);
    }

    private static List<WordWithPosition> convertToWordWithPositionListWithFiltering(Root root) {

        List<WordWithPosition> wordWithPositionList = new ArrayList<>();
        int[] position = { 0 };

        root.getS().stream().forEach(s -> s.getWord().stream().forEach(s1 -> {
            String[] wordWithDescription = s1.getWord().split("\\s+");

            if (!wordWithDescription[wordWithDescription.length-1].matches(SENTENCE_STOP_WORD_P)){
                wordWithPositionList.add(new WordWithPosition(position[0]++,wordWithDescription[0]));
            }
        }));

        return wordWithPositionList;
    }

    private static void showFirstColumn(List<String> firstColumn) {
        firstColumn.forEach(System.out::println);
    }

    private static List<String> getFirstColumnFromSections(Root root) {

        List<String> firstColumn = new ArrayList<>();

        root.getS().stream().forEach(s -> s.getWord().stream().forEach(s1 -> {
            String[] wordWithDescription = s1.getWord().toString().split("\\s+");
            firstColumn.add(wordWithDescription[0]);
        }));

        return firstColumn;
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    private static Root getWackypediaSections() throws IOException {
        File file = new File(PATH_TO_WACKYPEDIA_FILE);

        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        Root root = xmlMapper.readValue(xml, Root.class);

        return root;

    }

    private static void showAllSections(Root root) {
        root.getS().stream().forEach(s -> s.getWord().stream().forEach(s1 -> System.out.println(s1.getWord())));
    }

}
