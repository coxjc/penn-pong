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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * GameCourt
 * <p>
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel implements KeyListener {

    // Game constants
    public static final int COURT_WIDTH = 600;
    public static final int COURT_HEIGHT = 300;
    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;
    private static final String BG_IMG_LINK =
            Config.gameCourtBackgroundFileName;
    private final Game game;
    public boolean playing = false; // whether the game is running
    private Square paddle_left;
    private Square paddle_right;
    private Circle snitch;
    private Image backgroundImage;
    private Brick[][] bricks = new Brick[2][28];
    private boolean lastHitWasPoint = false;

    public GameCourt(Game game) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.WHITE));

        this.game = game;

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

        this.backgroundImage = Toolkit.getDefaultToolkit().createImage
                (BG_IMG_LINK);
        this.addKeyListener(this);

        requestFocusInWindow();
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'q':
                this.paddle_left.setPos_y(this.paddle_left.pos_y - this
                        .paddle_left.height / 2);
                this.paddle_left.clip();
                return;
            case 'a':
                this.paddle_left.setPos_y(this.paddle_left.pos_y + this
                        .paddle_left.height / 2);
                this.paddle_left.clip();
                return;
            case 'p':
                this.paddle_right.setPos_y(this.paddle_right.pos_y - this
                        .paddle_right.height / 2);
                this.paddle_right.clip();
                return;
            case 'l':
                this.paddle_right.setPos_y(this.paddle_right.pos_y + this
                        .paddle_right.height / 2);
                this.paddle_right.clip();
                return;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void setCourtToInitialState() {
        for (int j = 0; j < bricks[0].length; j++) {
            bricks[0][j] = new Brick(20 + (Brick.SIZE_X * j), 0, this
                    .getWidth(), this.getHeight());
        }
        for (int j = 0; j < bricks[1].length; j++) {
            bricks[1][j] = new Brick(20 + (Brick.SIZE_X * j), COURT_HEIGHT -
                    Brick.SIZE_Y, this
                    .getWidth(), this.getHeight());
        }

        paddle_left = new Square(0, 0, COURT_WIDTH, COURT_HEIGHT);

        paddle_right = new Square(this.getWidth() - Square.SIZE_X
                , 0, COURT_WIDTH, COURT_HEIGHT); //Have to remove the width
        // of the Square in order to keep it on the board.
        snitch = new Circle(COURT_WIDTH, COURT_HEIGHT);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    public void setCourtAfterPoint() {
        this.playing = false;
        //Bricks are reset after each point
        for (int j = 0; j < bricks[0].length; j++) {
            bricks[0][j] = new Brick(20 + (Brick.SIZE_X * j), 0, this
                    .getWidth(), this.getHeight());
        }
        for (int j = 0; j < bricks[1].length; j++) {
            bricks[1][j] = new Brick(20 + (Brick.SIZE_X * j), COURT_HEIGHT -
                    Brick.SIZE_Y, this
                    .getWidth(), this.getHeight());
        }
        this.snitch.setPos_x(COURT_WIDTH / 2);
        this.snitch.setPos_y(COURT_HEIGHT / 2);
        this.snitch.v_x = -this.snitch.v_x; //flip the direction of the ball
        // after each point (it should go to the other player)
        this.playing = true;
    }

    public void startGame() {
        this.playing = true;
    }

    public void stopGame() {
        this.playing = false;
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
            paddle_right.move();
            snitch.move();

            //if the ball went past user one's paddle
            if (this.snitch.pos_x < this.paddle_left.width / 2) {
                //if it isn't a repeat of the last point
                if (!this.lastHitWasPoint) {
                    this.game.incrUserTwoScore();
                    this.setCourtAfterPoint();
                    this.lastHitWasPoint = true;
                }
                this.snitch.bounce(snitch.hitWall());
            } else if (this.snitch.pos_x + this.snitch.width > COURT_WIDTH -
                    (this.paddle_right.width / 2)) {
                if (!this.lastHitWasPoint) {
                    this.game.incrUserOneScore();
                    this.setCourtAfterPoint();
                    this.lastHitWasPoint = true;
                }
                this.snitch.bounce(snitch.hitWall());
            } else {
                this.lastHitWasPoint = false;
            }

            Direction plDir = snitch.hitObj(this.paddle_left);
            Direction prDir = snitch.hitObj(this.paddle_right);
            if (plDir != null) {
                this.game.user_one.setHasPossession(true); // user one hit
                this.game.user_two.setHasPossession(false);
                // the ball last
                snitch.bounce(snitch.hitObj(this.paddle_left));
            } else if (prDir != null) {
                this.game.user_one.setHasPossession(false); // user two hit
                this.game.user_two.setHasPossession(true);
                snitch.bounce(snitch.hitObj(this.paddle_right));

            } else {
                snitch.bounce(snitch.hitWall());
            }

            for (int i = 0; i < this.bricks.length; i++) {
                for (int j = 0; j < this.bricks[i].length; j++) {
                    if (this.snitch.intersects(this.bricks[i][j]) && !this
                            .bricks[i][j].isImploded()) {
                        snitch.bounce(snitch.hitObj(this.bricks[i][j]));
                        bricks[i][j].collide();
                        break;
                    } else if (this.snitch.intersects(this.bricks[i][j]) && this
                            .bricks[i][j].isImploded() && !this.lastHitWasPoint) {
                        if (this.game.user_one.isHasPossession()) {
                            this.game.incrUserTwoScore();
                            this.setCourtAfterPoint();
                            this.lastHitWasPoint = true;
                            break;
                        } else if (this.game.user_two.isHasPossession()) {
                            this.game.incrUserOneScore();
                            this.setCourtAfterPoint();
                            this.lastHitWasPoint = true;
                            break;
                        }
                    }
                }
            }
            /**
             * Check for win here
             */
            this.playing = !this.game.checkForWinner();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, null);
        paddle_left.draw(g);
        paddle_right.draw(g);
        snitch.draw(g);
        for (int i = 0; i < this.bricks.length; i++)
            for (int j = 0; j < this.bricks[i].length; j++)
                this.bricks[i][j].draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}
