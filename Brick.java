import java.awt.*;

/**
 * Created by coxjc on 12/11/16.
 */
public class Brick extends ImplodingGameObj {
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 20;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final int STRENGTH = 1;

    public Brick(int initX, int initY, int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, initX, initY, SIZE_X, SIZE_Y,
                courtWidth, courtHeight, STRENGTH);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        if (!this.isImploded()) {
            g.fillRect(pos_x, pos_y, width, height);
        } else {
            g.drawRect(pos_x, pos_y, width, height);
        }
    }

}
