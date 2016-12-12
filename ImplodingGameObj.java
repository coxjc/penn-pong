/**
 * Created by coxjc on 12/11/16.
 */
public class ImplodingGameObj extends GameObj {
    private boolean imploded;
    private int numCollisions;
    private int strength; //number of collisions required to implode

    public ImplodingGameObj(int v_x, int v_y, int pos_x, int pos_y,
                            int width, int height, int court_width, int court_height,
                            int strength) {
        super(v_x, v_y, pos_x, pos_y, width, height, court_width, court_height);
        this.strength = strength;
        this.numCollisions = 0;
    }

    public void setImploded() {
        this.imploded = (this.numCollisions >= this.strength);
    }

    public boolean isImploded() {
        return this.imploded;
    }

    public void collide() {
        this.numCollisions++;
        this.setImploded();
    }
}
