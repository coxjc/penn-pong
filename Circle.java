/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;

/**
 * A basic game object displayed as a yellow circle, starting in the upper left
 * corner of the game court.
 * 
 */
public class Circle extends GameObj {

	public static final int SIZE = 20;
	public static final int INIT_POS_X = 300;
	public static final int INIT_POS_Y = 150;
	public static final int INIT_VEL_X = 2;
	public static final int INIT_VEL_Y = 4;

	public Circle(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE,
				courtWidth, courtHeight);
	}

	@Override
	public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(pos_x, pos_y, width, height);
	}

}
