/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 *
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
    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;
    /**
     * TODO
     * Need to get this path shit figured out.
     */
    private static final String BG_IMG_LINK = "/Users/coxjc/Google " +
            "Drive/Penn/SemI/CIS120/Java/hw09/imgs/pennBackground.jpg";
    public boolean playing = false; // whether the game is running
    // the state of the game logic
    private Rectangle paddle_left;
    private Rectangle paddle_right;
    private Circle snitch; // the Golden Snitch, bounces
    //Image link for background img
    private JLabel status; // Current status text (i.e. Running...)
    private Image backgroundImage;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.GREEN));

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
        this.backgroundImage = Toolkit.getDefaultToolkit().createImage
                (BG_IMG_LINK);
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
            // ...and the mushroom


            // check for the game end conditions
            if (this.snitch.pos_x < this.paddle_left.width / 2 || // if the
                    // snitch is less than half the width of the left paddle
                    this.snitch.pos_x + this.snitch.width > COURT_WIDTH -
                            (this.paddle_right.width / 2)) {
                this.endGame();
            }

            snitch.bounce(snitch.hitObj(this.paddle_left));
            snitch.bounce(snitch.hitObj(this.paddle_right));

            snitch.bounce(snitch.hitWall());
            // update the display
            repaint();
        }
    }

    public void endGame() {
        this.snitch.v_x = 0;
        this.snitch.v_y = 0;
        status.setText("Game Over");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, null);
        paddle_left.draw(g);
        paddle_right.draw(g);
        snitch.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
