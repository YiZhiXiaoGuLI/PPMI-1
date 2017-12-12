package qa;

import qa.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Questions {

    private static final String PATH_TO_ESL_FILE = "src/main/resources/public/test_sets/esl/test.set";

    private List<String> getContentFromFile() throws IOException {
        File file = new File(PATH_TO_ESL_FILE);
        return getLinesFromFile(new FileInputStream(file));
    }

    private List<String> getLinesFromFile(InputStream is) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }

    public List<Question> getQuestionsAndAnswersFromFile() throws IOException {
        List<Question> questionsWithAnswersList = new ArrayList<>();

        getContentFromFile().forEach(System.out::println);

        return questionsWithAnswersList;
    }
}
