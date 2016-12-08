/**
 * Created by coxjc on 12/7/16.
 */

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

    public static boolean isValidFormat(String line) {
        String scorerName = "";
        String scoreSecs = "";
        try {
            scorerName = line.substring(0, line.indexOf(','));
            scoreSecs = line.substring(line.indexOf(','), line.length());
        } catch (Throwable e) { //catch array out of bounds and whatnot
            return false;
        }
        if (scorerName.length() < 1) { //ensures there is a name
            return false;
        } else if (!scoreSecs.matches("[0-9]+")) { //regex to match #s only
            return false;
        }
        return true;
    }

}
