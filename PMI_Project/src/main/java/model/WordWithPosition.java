package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WordWithPosition {

    private int position;
    private String word;

}
