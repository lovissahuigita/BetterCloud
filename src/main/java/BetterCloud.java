import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BetterCloud {
    public static final String successMsg = "successful";

    public static void main(String[] args) {
        System.out.print(("hello").contains("ell"));

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/main/java/logs/logs_0.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String id = (String) jsonObject.get("id");
            System.out.println("\nid:" + id);

            // loop array
            JSONArray logs = (JSONArray) jsonObject.get("logs");
            System.out.println("\nlogs:" + logs);
            for (int i = 0; i < logs.size(); i++) {
                String msg = (String) ((JSONObject) logs.get(i)).get("message");
                Log log = new Log((String) ((JSONObject) logs.get(i)).get("id"), (String) ((JSONObject) logs.get(i)).get("email"), msg, msg.contains(successMsg));
                System.out.println(log);
            }
//            System.out.println(logs.size());
//            Iterator<String> iterator = logs.iterator();
//            while (iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}