package dataset.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "s")
public class Sections {

    @JacksonXmlElementWrapper(localName = "word", useWrapping = false)
    private List<Word> word;

}
