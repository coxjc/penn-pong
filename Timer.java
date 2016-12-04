/**
 * Created by coxjc on 12/4/16.
 */
public class Timer {
    private long startMS = 0L; //Current timestamp in MS
    private long finalMS = 0L; //Final timestamp in MS
    private long elapsedS = 0L; //SECONDS elapsed since start of timer

    //Instantiates a timer, sets initial MS
    public void startTimer() {
        this.startMS = System.currentTimeMillis();
    }

    //Ends a timer, sets final MS, makes call to set elapsed seconds
    public void endTimer() {
        this.finalMS = System.currentTimeMillis();
        this.setElapsedS();
    }

    //Converts difference between final and initial MS into elapsed seconds
    private void setElapsedS() {
        this.elapsedS = Math.round((this.finalMS - this.startMS) * .001);
    }

    //Returns elapsed seconds
    public long getElapsed() {
        return this.elapsedS;
    }
}
