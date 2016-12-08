/**
 * Created by coxjc on 12/4/16.
 */
public class PongTimer {
    private long startMS = 0L; //Current timestamp in MS
    private long finalMS = 0L; //Final timestamp in MS
    private long elapsedMS = 0L; //MILLISECONDS elapsed since start of timer

    //Instantiates a timer, sets initial MS
    public void startTimer() {
        this.startMS = System.currentTimeMillis();
    }

    //Ends a timer, sets final MS, makes call to set elapsed seconds
    public void endTimer() {
        this.finalMS = System.currentTimeMillis();
        this.setElapsedMS();
    }

    //Converts difference between final and initial MS
    private void setElapsedMS() {
        this.elapsedMS = this.finalMS - this.startMS;
    }

    //Returns elapsed milliseconds
    public long getElapsedMS() {
        return this.elapsedMS;
    }
}
