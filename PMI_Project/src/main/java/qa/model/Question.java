package qa.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private String question;
    private String content;
    private List<Answer> answers;
    private String correctAnswer;

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, String content) {
        this.question = question;
        this.content = content;
    }

    public Question(String question, String content, List<Answer> answers) {
        this.question = question;
        this.content = content;
        this.answers = answers;
    }
}
