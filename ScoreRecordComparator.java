import java.util.Comparator;

/**
 * Created by coxjc on 12/11/16.
 */
public class ScoreRecordComparator implements Comparator<ScoreRecord> {
    @Override
    public int compare(ScoreRecord obj1, ScoreRecord obj2) {
        return obj1.getScore();
    }
}
