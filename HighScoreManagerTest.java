import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by coxjc on 12/7/16.
 */
public class HighScoreManagerTest {
    @Test
    public void isValidFormatTests() {
        assertFalse("Empty line returns false",
                HighScoreManager.isValidFormat(null));
    }
}
