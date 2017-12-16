package qa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Question {

    private String question;
    private String content;
    private List<Answer> answers;
    private String correctAnswer;
    private Type type;

    public Question(String question, String content, Type type) {
        this.question = question;
        this.content = content;
        this.type = type;
    }
}
