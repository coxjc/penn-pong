import java.awt.*;

/**
 * Created by coxjc on 12/3/16.
 */
public class Rectangle extends GameObj {
    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 80;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    public Rectangle(int initX, int initY, int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, initX, initY, SIZE_X, SIZE_Y,
                courtWidth,
                courtHeight);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(pos_x, pos_y, width, height);
    }

}
