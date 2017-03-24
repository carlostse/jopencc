import jopencc.Convertor;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test for <class>Convertor</class>
 *
 * Created by carlos on 22/3/2017.
 */
public class ConvertorTest {

    @Test
    public void testZhtToZhs() {
        assertEquals("convert to ZHS", "开放中文转换", (new Convertor()).convertToZhs("開放中文轉換"));
    }

    @Test
    public void testZhsToZht() {
        assertEquals("convert to ZHT", "開放中文轉換", (new Convertor()).convertToZht("开放中文转换"));
    }
}
