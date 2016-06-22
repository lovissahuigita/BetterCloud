import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileNotFoundException;

public class TestConsumer extends TestCase {

    private final String LOG0_DIR = "src/main/java/logs/logs_0.json";
    private final String WRONG_DIRECTORY ="random/directory";

    @Test (expected = FileNotFoundException.class)
    public void testConsumerIOException() throws Exception {
        Process.consumer(WRONG_DIRECTORY);
    }

    @Test (expected = ParseException.class)
    public void testConsumerParseExceotion() throws Exception {
        Process.consumer("src/main/java/test/log.txt");
    }

    @Test
    public void testConsumerReturnType() { /* checks if the method returns correct object type */
        for (int i = 0; i < 100; i++) {
            assertTrue(Process.consumer("src/main/java/logs/logs_" + i + ".json") instanceof JSONObject);
        }
    }

    @Test
    public void testConsumerReturnObject() { /* checks if the method returns correct object */
        assertEquals("56f83bed-3705-4115-9067-73930cbecbc0", Process.consumer(LOG0_DIR).get("id"));
        assertTrue(Process.consumer(LOG0_DIR).get("logs") instanceof JSONArray);
    }

}