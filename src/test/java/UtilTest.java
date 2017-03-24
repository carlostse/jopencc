import jopencc.util.Util;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * Test for <class>jopencc.util.UtilTest</class>
 * <p>
 * Created by carlos on 22/3/2017.
 */

public class UtilTest {

    @Test
    public void testIsMissing() {
        String nullString = null;
        assertTrue("isMissing null String", Util.isMissing(nullString));
        assertTrue("isMissing empty String", Util.isMissing(""));
        assertTrue("isMissing empty HashMap", Util.isMissing(new HashMap<>()));
        assertTrue("isMissing empty ArrayList", Util.isMissing(new ArrayList<>()));
    }

}
