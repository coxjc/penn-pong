/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


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
    private Rectangle rectangle;
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

        // This key listener allows the rectangle to move as long
        // as an arrow key is pressed, by changing the rectangle's
        // velocity accordingly. (The tick method below actually
        // moves the rectangle.)
        addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    rectangle.v_x = -SQUARE_VELOCITY;
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    rectangle.v_x = SQUARE_VELOCITY;
                else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    rectangle.v_y = SQUARE_VELOCITY;
                else if (e.getKeyCode() == KeyEvent.VK_UP)
                    rectangle.v_y = -SQUARE_VELOCITY;
            }

			public void keyReleased(KeyEvent e) {
                rectangle.v_x = 0;
                rectangle.v_y = 0;
            }
		});

		this.status = status;

        this.addMouseMotionListener(this);

    }

    public void mouseMoved(MouseEvent e) {
        this.rectangle.setPos_y(e.getY());
    }

    public void mouseDragged(MouseEvent e) {
    }

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {

        rectangle = new Rectangle(COURT_WIDTH, COURT_HEIGHT);
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
            // advance the rectangle and snitch in their
            // current direction.
            rectangle.move();
            snitch.move();

			// make the snitch bounce off walls...
			snitch.bounce(snitch.hitWall());
			// ...and the mushroom
			snitch.bounce(snitch.hitObj(poison));

			// check for the game end conditions
            if (rectangle.intersects(poison)) {
                playing = false;
				status.setText("You lose!");

            } else if (rectangle.intersects(snitch)) {
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
        rectangle.draw(g);
        poison.draw(g);
		snitch.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
