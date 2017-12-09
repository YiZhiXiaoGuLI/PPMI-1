package model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "root")
public class Root {

    @JacksonXmlElementWrapper(localName = "s", useWrapping = false)
    private List<Sections> s;

}
