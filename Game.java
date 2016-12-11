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
    private User user_one;
    private JLabel userOneLabel;
    private User user_two;
    private JLabel userTwoLabel;
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
        rootGameCourtPanel.setBackground(Color.BLUE);

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
        final JPanel control_panel = new JPanel(new GridLayout(3, 1));
        control_panel.setBackground(Color.BLUE);
        rootGameCourtPanel.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the setCourtToInitialState
        // button, we define it as an anonymous inner class that is
        // an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed,
        // actionPerformed() will be called.
        JButton startButton = new JButton("Start");
        userOneLabel = new JLabel("");
        userOneLabel.setForeground(Color.WHITE);
        userTwoLabel = new JLabel("");
        userTwoLabel.setForeground(Color.WHITE);
        startButton.setForeground(Color.RED);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.startGame();
                startButton.setEnabled(false);
            }
        });
        control_panel.add(userOneLabel);
        control_panel.add(startButton);
        control_panel.add(userTwoLabel);
        court.setCourtToInitialState();

        this.rootCardContainer.add(instructionsPage);
        this.rootCardContainer.add(gameLaunch);
        this.rootCardContainer.add(rootGameCourtPanel);
        rootFrame.add(this.rootCardContainer);


        // Put the frame on the screen
        rootFrame.pack();
        rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootFrame.setVisible(true);

        // Start game
        court.setCourtToInitialState();
    }

    public void nextCard() {
        CardLayout tmp = (CardLayout) this.rootCardContainer.getLayout();
        tmp.next(this.rootCardContainer);
    }

    public void createUserOne(String nn) {
        //USER ONE GETS 1st POSSESSION
        this.user_one = new User(nn, true);
        this.userOneLabel.setText(nn + "\n" + 0);

    }

    public void createUserTwo(String nn) {
        this.user_two = new User(nn, false);
        this.userTwoLabel.setText(nn + "\n" + 0);
    }

    public int getPoints_to_win() {
        return points_to_win;
    }

    public void setPoints_to_win(int points_to_win) {
        this.points_to_win = points_to_win;
    }
}
