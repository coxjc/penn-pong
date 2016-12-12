/**
 * Created by coxjc on 12/8/16.
 */
public class ScoreRecord {
    private final String name;
    private final int score;
    private final String timestamp;

    public ScoreRecord(String n, int s, String ts) {
        this.name = n;
        this.score = s;
        this.timestamp = ts;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public String getTimestamp() {
        return this.timestamp;
    }
}
