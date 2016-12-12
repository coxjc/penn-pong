/**
 * Created by coxjc on 12/10/16.
 */
public class User {

    private String nickname;
    private int score;
    private boolean hasPossession;

    public User(String nn, boolean poss) {
        this.nickname = nn;
        this.score = 0;
        this.hasPossession = poss;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrScore() {
        this.score++;
    }

    public boolean isHasPossession() {
        return hasPossession;
    }

    public void setHasPossession(boolean hasPossession) {
        this.hasPossession = hasPossession;
    }


}
