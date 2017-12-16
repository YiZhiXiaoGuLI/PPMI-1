package qa;

import org.apache.commons.lang3.StringUtils;
import qa.model.Answer;
import qa.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Question> getESLQuestionsAndAnswers() throws IOException {
        List<Question> questionsWithAnswersList = new ArrayList<>();

        getContentFromFile().forEach(s -> {
            List<String> answerStringList = Arrays.stream(StringUtils.substringBetween(s, "|", "\"").split("\\|")).collect(Collectors.toList());
            List<Answer> answers =  new ArrayList<>();
            answerStringList.forEach(a -> answers.add(new Answer(a)));
            questionsWithAnswersList.add(new Question(StringUtils.substringBetween(s, "\"", "|"),
                    StringUtils.substringBetween(s, "[", "]"),answers,StringUtils.substringAfter(s,":")));
        });

        return questionsWithAnswersList;
    }

}
