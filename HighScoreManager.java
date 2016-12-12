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

    public static String getCurrentTimestampString() {
        return Long.toString(System.currentTimeMillis());
    }

    public static boolean
    addHighScore(CSVWriter writer, String name, double score) {
        if (writer == null || name == null || score < 0)
            throw new IllegalArgumentException();
        String[] colsFinal = {name, String.valueOf(score),
                getCurrentTimestampString()};
        writer.writeNext(colsFinal);
        try {
            writer.close();
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static TreeMap<String, ScoreRecord> getHighScores(CSVReader reader) {
        if (reader == null) throw new IllegalArgumentException();
        String[] nextLine;
        TreeMap<String, ScoreRecord> highScores = new TreeMap<>();
        String name;
        int score;
        String timestamp;
        try {
            while ((nextLine = reader.readNext()) != null) {
                name = nextLine[0].trim();
                score = Integer.valueOf(nextLine[1].trim());
                timestamp = nextLine[2].trim();
                highScores.put(timestamp,
                        new ScoreRecord(name, score, timestamp));
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException();
        }
        return highScores;
    }

}
