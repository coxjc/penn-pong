/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 *
 * @version 2.0, Mar 2013
 * <p>
 * <p>
 * EDITED FOR PennPong by @coxjc
 * Dec 2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {

    private final JPanel rootCardContainer = new JPanel(new CardLayout());
    private String user_one_nickname;
    private String user_two_nickname;
    private int points_to_win;

    /*
     * Main method run to start and run the game Initializes the GUI elements
     * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
     * this in the final submission of your game.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }

    public void run() {
        // Top-level frame in which every element resides
        final JFrame rootFrame = new JFrame("PennPong");
        rootFrame.setLocation(300, 300);

        final JPanel rootGameCourtPanel = new JPanel();

        final GameLaunch gameLaunch = new GameLaunch(this);
        final InstructionsPage instructionsPage = new InstructionsPage(this);

        // Status panel
//		final JPanel status_panel = new JPanel();
//		rootGameCourtPanel.add(status_panel, BorderLayout.SOUTH);
//		final JLabel status = new JLabel("Running...");
//		status_panel.add(status);
        // Main playing area
        final GameCourt court = new GameCourt();
        rootGameCourtPanel.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        rootGameCourtPanel.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset
        // button, we define it as an anonymous inner class that is
        // an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed,
        // actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        this.rootCardContainer.add(instructionsPage);
        this.rootCardContainer.add(gameLaunch);
        this.rootCardContainer.add(rootGameCourtPanel);
        rootFrame.add(this.rootCardContainer);


        // Put the frame on the screen
        rootFrame.pack();
        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setVisible(true);

        // Start game
        court.reset();
    }

    public void nextCard() {
        CardLayout tmp = (CardLayout) this.rootCardContainer.getLayout();
        tmp.next(this.rootCardContainer);
    }

    public String getUser_one_nickname() {
        return user_one_nickname;
    }

    public void setUser_one_nickname(String nn) {
        this.user_one_nickname = nn;
    }

    public String getUser_two_nickname() {
        return user_two_nickname;
    }

    public void setUser_two_nickname(String nn) {
        this.user_two_nickname = nn;
    }

    public int getPoints_to_win() {
        return points_to_win;
    }

    public void setPoints_to_win(int points_to_win) {
        this.points_to_win = points_to_win;
    }
}
