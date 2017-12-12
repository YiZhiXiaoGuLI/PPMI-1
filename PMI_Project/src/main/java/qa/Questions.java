package qa;

import org.apache.commons.lang3.StringUtils;
import qa.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//        getContentFromFile().forEach(System.out::println);
        getContentFromFile().stream().forEach( s ->{
            questionsWithAnswersList.add(new Question(StringUtils.substringBetween(s,"\"",".")));
        });

        questionsWithAnswersList.forEach( s-> System.out.println(s.getQuestion()));

        return questionsWithAnswersList;
    }
}
