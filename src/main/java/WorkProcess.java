import org.json.simple.JSONObject;
import java.io.*;

/**
 * Created by lovissa on 6/17/16.
 */
public class WorkProcess {

    public static final String FOLDER_NAME = "src/main/java/logs";
    public static final String DEST_FOLDER_NAME
            = "src/main/java/logs_tally/tally_";


    public static void main(String[] args) {

        /* Checks a folder for all file names in it */
        File folder = new File(FOLDER_NAME);
        File[] listOfFiles = folder.listFiles();

        /* Goes through all the files in the folder */
        for (int i = 0; i < listOfFiles.length; i++) {

            /* Consumes the file: Get the JSON data from .json file */
            JSONObject toTransform = Process.consumer(FOLDER_NAME + "/"
                    + listOfFiles[i].getName());

            /* Transforms the data: Count the tally of the email's
            number of appearance */
            JSONObject toProduce = Process.transformer(toTransform);

            /* Produces a resulting file: Write a file containing the
            tally data */
            Process.producer(toProduce, DEST_FOLDER_NAME
                    + listOfFiles[i].getName());
        }
    }
}
