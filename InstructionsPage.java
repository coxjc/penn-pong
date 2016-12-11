import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by coxjc on 12/10/16.
 */
public class InstructionsPage extends JPanel implements ActionListener {
    private static final int INTERVAL = 35;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final String GAME_INSTRUCTIONS = "This is a remake (with " +
            "some twists, of course) of the classic game Pong. PLAYER ONE is " +
            "on the left side, and PLAYER TWO is on the right. PLAYER ONE " +
            "uses the 'q' key to move the paddle up, and the 'a' key to move " +
            "the paddle down. Conversely, PLAYER TWO uses the 'p' key to move" +
            " the paddle up, and the 'l' key to move the paddle down. \n\n" +
            "There are bricks on the top and bottom of the game " +
            "court. When a brick is hit, the ball bounces off of the brick, " +
            "and the brick immediately implodes. EACH BRICK IMPLODES AFTER " +
            "ONE COLLISION. If a player hits the ball and it goes through the" +
            " location of a now-imploded (the brick will no longer appear) " +
            "brick, " +
            "the ball has went out of bounds and the opposite player receives" +
            " a point.\n\nThe " +
            "winner is the player who reaches the designated point value " +
            "first. Win quickly to set a high score!";
    private static final JTextArea gameInstructionsLabel =
            new JTextArea(GAME_INSTRUCTIONS, 10, 50);
    private static final JButton proceedButton = new JButton("Understood, " +
            "let's play!");
    private Game game;

    public InstructionsPage(Game game) {
        this.game = game;

        setBorder(BorderFactory.createLineBorder(Color.RED));
        setBackground(Color.BLUE);

        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        gameInstructionsLabel.setForeground(Color.WHITE);
        gameInstructionsLabel.setLineWrap(true);
        gameInstructionsLabel.setWrapStyleWord(true);
        gameInstructionsLabel.setOpaque(false);
        gameInstructionsLabel.setEditable(false);
        this.add(gameInstructionsLabel);

        proceedButton.setActionCommand("PROCEED");
        proceedButton.addActionListener(this);
        this.add(proceedButton);
    }

    void tick() {
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if ("PROCEED".equals(e.getActionCommand())) {
            this.game.nextCard();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

}
