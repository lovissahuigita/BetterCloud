import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class TestTransformer extends TestCase {

    @Test (expected = IllegalArgumentException.class)
    public void testTransformerIllegalArgumentException() {
        try {
            Process.transformer(null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = NullPointerException.class)
    public void testTransformerNullPointerException() {
        JSONObject toTransform = Process.consumer("src/main/java/test/log_test.json");
        try {
            JSONObject toProduce = Process.transformer(toTransform);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTransformReturnType() {
        for (int i = 0; i < 100; i++) {
            assertTrue(Process.transformer(Process.consumer("src/main/java/logs/logs_" + i + ".json")) instanceof JSONObject);
        }
    }

    @Test
    public void testTransformerReturnObject() {
        JSONObject toProduce = Process.transformer(Process.consumer("src/main/java/test/transform_test.json"));

        /* this map was manually inserted using data from a simple test file */
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a@me.com", 5);
        map.put("b@dropbox.net", 1);
        map.put("c@bettercloud.com", 1);

        JSONArray arr = (JSONArray) toProduce.get("tally");
        for (int i = 0; i < arr.size(); i++ ) {
            String email = (String) ((JSONObject) arr.get(i)).get("email");
            assertEquals(map.get(email), ((JSONObject) arr.get(i)).get("total"));
        }
    }

}