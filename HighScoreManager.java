/**
 * Created by coxjc on 12/7/16.
 */

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.util.TreeMap;

/**
 * Scores are stored in a basic .csv file..
 * Scores are stored according to the following format
 * <p>
 * SCORER NAME, SCORE\n
 * <p>
 * The score is designated as the number of seconds it takes for the winning
 * player to win.
 **/

public class HighScoreManager {

    //Ensures that a line follows the following format
    //  "USER'S NAME,NUMERICAL SCORE"
    public static boolean isValidFormat(String line) {
        String scorerName = "";
        String scoreSecs = "";
        try {
            scorerName = line.substring(0, line.indexOf(',')).trim();
            scoreSecs = line.substring(line.indexOf(',') + 1, line.length())
                    .trim();
        } catch (Throwable e) { //catch array out of bounds and whatnot
            return false;
        }
        if (scorerName.length() < 1) { //ensures there is a name
            return false;
        } else if (!scoreSecs.matches("[0-9]+")) { //regex to match #s only
            return false;
        } else if (line.indexOf(',') != line.lastIndexOf(',')) {
            return false; // if there is more than one comma in the line
        }
        return true;
    }

    public static boolean addHighScore(CSVWriter writer, String line) {
        if (!isValidFormat(line)) return false;
        String[] cols = line.split(",");
        writer.writeNext(cols);
        try {
            writer.close();
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static TreeMap<Integer, String> getHighScores(CSVReader reader) {
        String[] nextLine;
        TreeMap<Integer, String> highScores = new TreeMap<>();
        try {
            while ((nextLine = reader.readNext()) != null) {
                if (isValidFormat(nextLine[0] + "," + nextLine[1])) {
                    nextLine[0] = nextLine[0].trim();
                    nextLine[1] = nextLine[1].trim();
                    highScores.put(Integer.valueOf(nextLine[1]),
                            nextLine[0]);
                }
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException();
        }
        return highScores;
    }

}
