import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Root;
import model.WordModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dataset {

    private static final String PATH_TO_WACKYPEDIA_FILE = "src/main/resources/public/wackypedia_en1_with_root_word_5k";
    private static final String SENTENCE_STOP_WORD_P = "P";

    public List<WordModel> convertToWordWithPositionListWithFiltering(Root root) {

        List<WordModel> wordModelList = new ArrayList<>();

        root.getS().stream().forEach(s -> s.getWord().stream().forEach(s1 -> {
            String[] wordWithDescription = s1.getWord().split("\\s+");

            if (!wordWithDescription[wordWithDescription.length - 1].matches(SENTENCE_STOP_WORD_P)) {
                wordModelList.add(new WordModel(wordWithDescription[0]));
            }
        }));

        return wordModelList;
    }

    private static void showFirstColumn(List<String> firstColumn) {
        firstColumn.forEach(System.out::println);
    }

    public List<String> getFirstColumnFromSections(Root root) {

        List<String> firstColumn = new ArrayList<>();

        root.getS().stream().forEach(s -> s.getWord().stream().forEach(s1 -> {
            String[] wordWithDescription = s1.getWord().toString().split("\\s+");
            firstColumn.add(wordWithDescription[0]);
        }));

        return firstColumn;
    }

    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public Root getWackypediaSections() throws IOException {
        File file = new File(PATH_TO_WACKYPEDIA_FILE);

        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        Root root = xmlMapper.readValue(xml, Root.class);

        return root;

    }

    public void showAllSections(Root root) {
        root.getS().stream().forEach(s -> s.getWord().stream().forEach(s1 -> System.out.println(s1.getWord())));
    }

}
