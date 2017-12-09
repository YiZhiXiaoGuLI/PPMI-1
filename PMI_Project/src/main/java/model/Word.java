package model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;


@Data
public class Word {

    @JacksonXmlProperty(localName = "word")
    private String word;

    public Word(String word) {
        this.word = word;
    }
}
