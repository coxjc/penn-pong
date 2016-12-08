import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;

import static org.junit.Assert.*;

/**
 * Created by coxjc on 12/7/16.
 */
public class HighScoreManagerTest {

    private final String testScores = "/Users/coxjc/Google " +
            "Drive/Penn/SemI/CIS120/Java/hw09/sampleScores.csv";

    @Test
    public void isValidFormatTests() {
        /**
         * Start failing tests
         */
        assertFalse("Null returns false",
                HighScoreManager.isValidFormat(null));
        assertFalse("Empty string returns false",
                HighScoreManager.isValidFormat(""));
        assertFalse("Correct text without comma returns false",
                HighScoreManager.isValidFormat("User123"));
        assertFalse("User without score returns false",
                HighScoreManager.isValidFormat("User,"));
        assertFalse("Score without user returns false",
                HighScoreManager.isValidFormat(",1"));
        assertFalse("Non-numerical score returns false",
                HighScoreManager.isValidFormat("User,III"));
        assertFalse("More than one comma returns false",
                HighScoreManager.isValidFormat("Us,er,1"));
        /**
         * Start passing tests
         */
        assertTrue("String user name w/ numerical score returns true",
                HighScoreManager.isValidFormat("User,1"));
        assertTrue(HighScoreManager.isValidFormat("AABBCC,112233"));
        assertTrue("Space around name or score returns true",
                HighScoreManager.isValidFormat(" User1 , 1 "));
    }

    @Test
    public void getHighScoresTests() {
        try {
            CSVReader reader = new CSVReader(new FileReader(this.testScores));
            assertEquals(HighScoreManager.getHighScores(reader).size(), 2);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void addHighScoreTests() {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(this.testScores));
            assertTrue(HighScoreManager.addHighScore(writer, "USER3, 8"));
            CSVReader reader = new CSVReader(new FileReader(this.testScores));
            assertEquals(HighScoreManager.getHighScores(reader).size(), 3);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }


}
