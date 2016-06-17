import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oracle.javafx.jmx.json.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BetterCloud {
    public static final String successMsg = "successful";

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/main/java/logs/logs_0.json"));

            JSONObject jsonObject = (JSONObject) obj;

            String id = (String) jsonObject.get("id");

            // 1. Consume
            JSONArray logs = (JSONArray) jsonObject.get("logs");
            Map<String, List<Log>> map = new HashMap<String, List<Log>>();

            Log log;
            String msg, email, logID;
            for (int i = 0; i < logs.size(); i++) {

                // Get the values of each log from the JSON file
                msg = (String) ((JSONObject) logs.get(i)).get("message");
                email = (String) ((JSONObject) logs.get(i)).get("email");
                logID = (String) ((JSONObject) logs.get(i)).get("id");

                // Create a new log object
                log = new Log(logID, email, msg, msg.contains(successMsg));

                // 2. Transform
                if (map.containsKey(email)) {
                    map.get(email).add(log);
                } else {
                    List<Log> logList = new ArrayList<Log>();
                    logList.add(log);
                    map.put(email, logList);
                }
            }

            // 3.Produce
            JSONArray arr = new JSONArray();
            Iterator iter = map.entrySet().iterator();
            JSONObject o;
            while (iter.hasNext()) {
                Map.Entry pair = (Map.Entry) iter.next();
                o = new JSONObject();
                o.put("email", pair.getKey());
                o.put("total", ((ArrayList<Log>) pair.getValue()).size());
                arr.add(o);
//                iter.remove(); // avoids a ConcurrentModificationException
            }
            for (int i = 0; i < arr.size(); i++) {
//                System.out.println(arr.get(i));
            }

//            System.out.println(map);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}