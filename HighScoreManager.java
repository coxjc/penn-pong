/**
 * Created by coxjc on 12/7/16.
 */

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * returns scores recorded *SORTED*
     *
     * @param reader
     * @return
     */
    public static ArrayList<ScoreRecord> getSortedHighScores(CSVReader reader) {
        if (reader == null) throw new IllegalArgumentException();
        String[] nextLine;
        String name;
        ArrayList<ScoreRecord> highScores = new ArrayList<>();
        int score;
        String timestamp;
        try {
            while ((nextLine = reader.readNext()) != null) {
                name = nextLine[0].trim();
                score = Integer.valueOf(nextLine[1].trim());
                timestamp = nextLine[2].trim();
                highScores.add(new ScoreRecord(name, score, timestamp));
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException();
        }
        Collections.sort(highScores, new ScoreRecordComparator());
        return highScores;
    }

}
