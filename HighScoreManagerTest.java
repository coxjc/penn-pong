import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by coxjc on 12/7/16.
 */
public class HighScoreManagerTest {

    @Test
    public void getHighScoresTests() {
        try {
            //Generate two timestamps.. have to Thread.sleep to ensure they
            // don't duplicate, because that'll screw up the key set.
            String firstTs = HighScoreManager.getCurrentTimestampString();
            Thread.sleep(10);
            String secondTs = HighScoreManager.getCurrentTimestampString();
            Thread.sleep(10);
            //Generate test string using the generated timestamps
            String testData = "user1,1," + firstTs + "\nuser2,2," +
                    secondTs;
            CSVReader reader = new CSVReader(new StringReader(testData));
            //Get the TreeMap from the test data
            ArrayList<ScoreRecord> highScoresTreeMap = HighScoreManager
                    .getSortedHighScores(reader);
            assertEquals(highScoresTreeMap.size(), 2);
            assertEquals(highScoresTreeMap.get(0).getName(), "user1");
            assertEquals(highScoresTreeMap.get(0).getScore(), 1);
            assertEquals(highScoresTreeMap.get(1).getName(), "user2");
            assertEquals(highScoresTreeMap.get(1).getScore(), 2);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void addHighScoreTests() {
        try {
            //Generate two timestamps.. have to Thread.sleep to ensure they
            // don't duplicate, because that'll screw up the key set.
            String firstTs = HighScoreManager.getCurrentTimestampString();
            Thread.sleep(10);
            String secondTs = HighScoreManager.getCurrentTimestampString();
            Thread.sleep(10);
            //Generate test string using the generated timestamps
            String testData = "user1,1," + firstTs + "\nuser2,2," + secondTs;
            //Create CSVWriter using StringWriter
            StringWriter stringWriter = new StringWriter();
            CSVWriter writer = new CSVWriter(stringWriter, ',', CSVWriter
                    .NO_QUOTE_CHARACTER);
            //Add high score to the writer
            HighScoreManager.addHighScore(writer, "USER3", 8);
            CSVReader reader = new CSVReader(new StringReader(stringWriter.toString()));
            //There should only be the USER3 record at this point
            assertEquals(HighScoreManager.getSortedHighScores(reader).size(), 1);
            //Add the test data described earlier to the writer
            stringWriter.append(testData);
            //Create StringReader from the StringWriter
            reader = new CSVReader(new StringReader(stringWriter.toString()));
            // There should be the original USER3 as well as the two
            // generated records
            assertEquals(HighScoreManager.getSortedHighScores(reader).size(), 3);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }


}
