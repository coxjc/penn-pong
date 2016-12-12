/**
 * Created by coxjc on 12/8/16.
 */
public class ScoreRecord {
    private final String name;
    private final double score;
    private final String timestamp;

    public ScoreRecord(String n, double s, String ts) {
        this.name = n;
        this.score = s;
        this.timestamp = ts;
    }

    public String getName() {
        return this.name;
    }

    public double getScore() {
        return this.score;
    }

    public String getTimestamp() {
        return this.timestamp;
    }
}
