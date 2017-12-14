package qa.model;

import lombok.Data;

@Data
public class Answer {

    private String content;

    public Answer(String content) {
        this.content = content;
    }
}
