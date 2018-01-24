package qa;

import org.apache.commons.lang3.StringUtils;
import qa.model.Answer;
import qa.model.Question;
import qa.model.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Questions {

    private static final String PATH_TO_ESL_FILE = "src/main/resources/public/test_sets/esl/test.set";
    private static final String PATH_TO_TOEFL_FILE = "src/main/resources/public/test_sets/toefl/test.set";

    private List<String> getContentFromFile(String path) throws IOException {
        File file = new File(path);
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

    private List<Question> getESLQuestionsAndAnswers() throws IOException {
        List<Question> questionsWithAnswersList = new ArrayList<>();

        getContentFromFile(PATH_TO_ESL_FILE).forEach(s -> {
            List<String> answerStringList = Arrays.stream(StringUtils.substringBetween(s, "|", "\"").split("\\|")).collect(Collectors.toList());
            List<Answer> answers = new ArrayList<>();
            answerStringList.forEach(a -> answers.add(new Answer(a)));
            questionsWithAnswersList.add(new Question(StringUtils.substringBetween(s, "\"", "|"),
                    StringUtils.substringBetween(s, "[", "]"), answers, StringUtils.substringAfter(s, ":"), Type.ESL));
        });

        return questionsWithAnswersList;
    }

    private List<Question> getTOEFLQuestionsAndAnswers() throws IOException {

        List<String> stringTOEFLFileList = getContentFromFile(PATH_TO_TOEFL_FILE);

        List<Question> questionsWithAnswersList = new ArrayList<>();

        List<Answer> aAnswers = new ArrayList<>();
        List<Answer> bAnswers = new ArrayList<>();
        List<Answer> cAnswers = new ArrayList<>();
        List<Answer> dAnswers = new ArrayList<>();
        List<Answer> allAnswersForQuestion = new ArrayList<>();

        List<Answer> correct_answers = new ArrayList<>();

        stringTOEFLFileList.forEach(s -> {

            if (s.matches("\\d.\\D*")) {
                String[] wordContent = s.split("\\s+");
                questionsWithAnswersList.add(new Question(wordContent[1], wordContent[1], Type.TOEFL));
            }

            if (s.matches("[a].\\D*")) {
                String[] answer = s.split("\\s+");
                aAnswers.add(new Answer(answer[1]));
            }
            if (s.matches("[b].\\D*")) {
                String[] answer = s.split("\\s+");
                bAnswers.add(new Answer(answer[1]));
            }
            if (s.matches("[c].\\D*")) {
                String[] answer = s.split("\\s+");
                cAnswers.add(new Answer(answer[1]));
            }
            if (s.matches("[d].\\D*")) {
                String[] answer = s.split("\\s+");
                dAnswers.add(new Answer(answer[1]));
            }

            if (s.matches("\\d.+\\W\\D")) {
                String[] answer = s.split("\\s+");
                correct_answers.add(new Answer(answer[3]));
            }

        });

        ///merge 4 answersLists to 1 finalListAnswersForQuestion
        final int[] iterator = {0};
        final String[] correctCharAnswer = new String[1];
        questionsWithAnswersList.forEach(s -> {
            allAnswersForQuestion.add(aAnswers.get(iterator[0]));
            allAnswersForQuestion.add(bAnswers.get(iterator[0]));
            allAnswersForQuestion.add(cAnswers.get(iterator[0]));
            allAnswersForQuestion.add(dAnswers.get(iterator[0]));
            s.setAnswers(new ArrayList<>(allAnswersForQuestion));
            allAnswersForQuestion.clear();
            correctCharAnswer[0] = String.valueOf(correct_answers.get(iterator[0]));
            if(correctCharAnswer[0].endsWith("a)")) {
                s.setCorrectAnswer(aAnswers.get(iterator[0]).getContent());
            }
            if(correctCharAnswer[0].endsWith("b)")) {
                s.setCorrectAnswer(bAnswers.get(iterator[0]).getContent());
            }
            if(correctCharAnswer[0].endsWith("c)")) {
                s.setCorrectAnswer(cAnswers.get(iterator[0]).getContent());
            }
            if(correctCharAnswer[0].endsWith("d)")) {
                s.setCorrectAnswer(dAnswers.get(iterator[0]).getContent());
            }
            iterator[0]++;
        });

        return questionsWithAnswersList;
    }

    public List<Question> getAllQuestions() throws IOException {
        return Stream.concat(getESLQuestionsAndAnswers().stream(), getTOEFLQuestionsAndAnswers().stream()).collect(Collectors.toList());
    }

}
