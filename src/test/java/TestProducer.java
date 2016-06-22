import junit.framework.TestCase;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.FileNotFoundException;

public class TestProducer extends TestCase {

    private final String WRONG_DIRECTORY ="random/directory";
    private final String TEST_OUTPUT_DIRECTORY ="src/main/java/test/tally.json";

    @Test (expected = FileNotFoundException.class)
    public void testProducerFileNotFoundException() {
        Process.producer(Process.transformer(Process.consumer("src/main/java/test/transform_test.json")), WRONG_DIRECTORY);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testProducerIllegalArgumentException() {
        try {
            Process.producer(null, TEST_OUTPUT_DIRECTORY);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProducerFileCreated() {
        JSONObject toProduce = Process.transformer(Process.consumer("src/main/java/test/transform_test.json"));
        Process.producer(toProduce, TEST_OUTPUT_DIRECTORY);
        assertEquals(toProduce.hashCode(), Process.consumer(TEST_OUTPUT_DIRECTORY).hashCode());
    }
}