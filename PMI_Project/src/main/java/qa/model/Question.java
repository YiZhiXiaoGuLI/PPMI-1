package qa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question {

    private String question;
    private String content;
    private List<Answer> answers;
    private String correctAnswer;

}
