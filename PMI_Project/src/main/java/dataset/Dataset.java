package dataset;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dataset.model.Root;
import dataset.model.Sections;
import dataset.model.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dataset {

    private static final String PATH_TO_WACKYPEDIA_FILE = "src/main/resources/public/wackypedia_en1_with_root_word_15k";
    private static final String PATH_TO_WACKYPEDIA_FILE_WITHOUT_XML = "src/main/resources/public/wackypedia_en1_100k_without_text";
    private static final String SENTENCE_STOP_WORD_P = "P";

    public List<Word> getAllWords(Root root) {

        List<Word> wordList = new ArrayList<>();
        root.getS().forEach(s -> s.getWord().forEach(s1 -> {
            wordList.add(new Word(s1.getWord()));
        }));
        return wordList;
    }

    private static void showFirstColumn(List<String> firstColumn) {
        firstColumn.forEach(System.out::println);
    }

    public List<String> getSecondColumnFromSections(Root root) {

        List<String> firstColumn = new ArrayList<>();

        root.getS().forEach(s -> s.getWord().forEach(s1 -> {
            String[] wordWithDescription = s1.getWord().split("\\s+");
            firstColumn.add(wordWithDescription[1]);
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

        return xmlMapper.readValue(xml, Root.class);
    }

    public Root inputStreamToStringWithoutXml(InputStream is) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        Root root = new Root();
        Sections sections = new Sections();
        List<Sections> sectionsList = new ArrayList<>();
        List<Word> wordList = new ArrayList<>();

        while ((line = br.readLine()) != null) {

            if (line.contains("<s>")) {
                sections = new Sections();
                wordList = new ArrayList<>();
            }

            if (!line.contains("<s>") && !line.contains("</s>")) {

                String[] wordWithDescription = line.split("\\s+");
                if (!wordWithDescription[wordWithDescription.length - 1].matches(SENTENCE_STOP_WORD_P)) {
                    Word word = new Word(wordWithDescription[1]);
                    wordList.add(word);
                }
            }

            if (line.contains("</s>")) {
                sections.setWord(new ArrayList<>(wordList));
                wordList.clear();
                sectionsList.add(sections);
            }
        }
        br.close();
        root.setS(sectionsList);
        return root;
    }

    public Root getWackypediaSectionsWithoutXml() throws IOException {
        File file = new File(PATH_TO_WACKYPEDIA_FILE_WITHOUT_XML);
        return inputStreamToStringWithoutXml(new FileInputStream(file));
    }


    public void showAllSections(Root root) {
        root.getS().forEach(s -> s.getWord().forEach(s1 -> System.out.println(s1.getWord())));
    }

}
