import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class TestJackson {

    @Test
    public void whenJavaGotFromXmlStr_thenCorrect() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        SimpleBean value =
                xmlMapper.readValue("<SimpleBean><x>1</x><y>2</y></SimpleBean>", SimpleBean.class);
        assertTrue(value.getX() == 1 && value.getY() == 2);
    }
}
