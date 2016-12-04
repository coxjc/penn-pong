/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel implements MouseMotionListener {

	// Game constants
    public static final int COURT_WIDTH = 600;
    public static final int COURT_HEIGHT = 300;
	public static final int SQUARE_VELOCITY = 4;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;
    public boolean playing = false; // whether the game is running
    // the state of the game logic
    private Rectangle paddle_left;
    private Rectangle paddle_right;
    private Circle snitch; // the Golden Snitch, bounces
    private Poison poison; // the Poison Mushroom, doesn't move
    private JLabel status; // Current status text (i.e. Running...)

	public GameCourt(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		this.status = status;

        this.addMouseMotionListener(this);

    }

    public void mouseMoved(MouseEvent e) {
        this.paddle_left.setPos_y(e.getY());
    }

    public void mouseDragged(MouseEvent e) {
    }

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {

        paddle_left = new Rectangle(0, 0, COURT_WIDTH, COURT_HEIGHT);
        paddle_right = new Rectangle(this.getWidth() - Rectangle.SIZE_X
                , 0, COURT_WIDTH, COURT_HEIGHT); //Have to remove the width
        // of the Rectangle in order to keep it on the board.
        poison = new Poison(COURT_WIDTH, COURT_HEIGHT);
		snitch = new Circle(COURT_WIDTH, COURT_HEIGHT);

		playing = true;
		status.setText("Running...");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {
            // advance the paddle_left and snitch in their
            // current direction.
            paddle_left.move();
            snitch.move();

			// make the snitch bounce off walls...
			snitch.bounce(snitch.hitWall());
			// ...and the mushroom
			snitch.bounce(snitch.hitObj(poison));

			// check for the game end conditions
            if (paddle_left.intersects(poison)) {
                playing = false;
				status.setText("You lose!");

            } else if (paddle_left.intersects(snitch)) {
                playing = false;
				status.setText("You win!");
			}

			// update the display
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        paddle_left.draw(g);
        paddle_right.draw(g);
        poison.draw(g);
		snitch.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
