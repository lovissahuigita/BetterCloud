import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * Created by Lovissa Winyoto
 * BS Computer Science May 2016
 * Georgia Institute of Technology
 *
 */
public class Process {

    public static final String successMsg = "successful";

    /**
     *
     * The consumer reads in a message (file) of a specified format (.json)
     * and parses the .json file into a JSON object.
     *
     * @param fileName a directory to a .json file
     * @return JSON object generated from the .json file
     */
    public static JSONObject consumer(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject toReturn = null;
        try {
            toReturn = (JSONObject) parser.parse(new FileReader(fileName));
        } catch (FileNotFoundException e) { //TODO: Check this
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    /**
     *
     * The transformer uses the consumed data as input, acts on the data, and
     * transforms it into the resulting output of a specific format.
     *
     * The transformer reads each entry in the json object input, saves it
     * into separate log objects while it updates a map with the tally count
     * of the unique email's number of appearance, and uses the data from
     * the map to create an output JSON object.
     *
     * @param toTransform JSONObject that contains the data to be processed
     * @return the resulting JSONObject from the toUpdate map
     */
    public static JSONObject transformer(JSONObject toTransform) {

        JSONArray logs = (JSONArray) toTransform.get("logs");

        /* A map of log entries, key: email */
        Map<String, List<Log>> toUpdate = new HashMap<String, List<Log>>();
        Log log;
        String msg, email, logID;

        /* Iterates through the JSONArray log and put the updates on the map */
        for (int i = 0; i < logs.size(); i++) {
            /* Gets the values of each log from the JSON file */
            msg = (String) ((JSONObject) logs.get(i)).get("message");
            email = (String) ((JSONObject) logs.get(i)).get("email");
            logID = (String) ((JSONObject) logs.get(i)).get("id");

            /* Creates a new Log object */
            log = new Log(logID, email, msg, msg.contains(successMsg));

            /* Updates the tally of unique email count */
            if (toUpdate.containsKey(email)) { /* if email already exists */
                toUpdate.get(email).add(log);
            } else {
                /* if new unique email is found */
                List<Log> logList = new ArrayList<Log>();
                logList.add(log);
                toUpdate.put(email, logList);
            }
        }

        /* process the data to look like the sample output file */
        JSONArray arr = new JSONArray();
        Iterator iter = toUpdate.entrySet().iterator();
        JSONObject obj;
        while (iter.hasNext()) {
            Map.Entry pair = (Map.Entry) iter.next();
            obj = new JSONObject();
            obj.put("email", pair.getKey());
            obj.put("total", ((ArrayList<Log>) pair.getValue()).size());
            arr.add(obj);
//                iter.remove(); // avoids a ConcurrentModificationException //TODO: check what this is
        }
        JSONObject toReturn = new JSONObject();
        toReturn.put("tally", arr);
        return toReturn;
    }

    /**
     *
     * The producer writes out the output to a file destination.
     *
     * @param toProduce JSONObject to be converted to a file
     * @param destFileName Destination file directory
     */
    public static void producer(JSONObject toProduce, String destFileName) {
        try {
            FileWriter file = new FileWriter(destFileName);
            toProduce.writeJSONString(file);
            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}