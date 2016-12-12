/**
 * Created by coxjc on 12/4/16.
 */
public class PongTimer {
    private int startMS = 0; //Current timestamp in MS
    private int finalMS = 0; //Final timestamp in MS
    private int elapsedMS = 0; //MILLISECONDS elapsed since start of timer

    //Instantiates a timer, sets initial MS
    public void startTimer() {
        this.startMS = (int) System.currentTimeMillis();
    }

    //Ends a timer, sets final MS, makes call to set elapsed seconds
    public void endTimer() {
        this.finalMS = (int) System.currentTimeMillis();
        this.setElapsedMS();
    }

    //Converts difference between final and initial MS
    private void setElapsedMS() {
        this.elapsedMS = this.finalMS - this.startMS;
    }

    //Returns elapsed milliseconds
    public int getElapsedMS() {
        return this.elapsedMS;
    }
}
