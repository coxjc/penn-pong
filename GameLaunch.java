import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by coxjc on 12/10/16.
 */
public class GameLaunch extends JPanel implements ActionListener {
    private static final int INTERVAL = 35;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    //    private static String CLIENT_IP_ADDRESS;
    private final int defaultPoints = 7;
    private final int maxPoints = 100;
    private Game game;
    //    private JLabel enterIpLabel;
    private JLabel enterPlayerOneNicknameLabel;
    private JLabel enterPlayerTwoNicknameLabel;
    //    private JTextField ipInputField;
    private JTextField playerOneNicknameInputField;
    private JTextField playerTwoNicknameInputField;
    //# of points the players want to play
    private JLabel pointsSelectorLabel;
    private JSpinner pointsSelector;
    private JButton proceedButton;

//    private JLabel clientIpAddrLabel;

    public GameLaunch(Game game) {
        this.game = game;
//        try {
//            CLIENT_IP_ADDRESS = InetAddress.getLocalHost().toString();
//        }
//        catch (Throwable e) {
//            CLIENT_IP_ADDRESS = "UNABLE TO FETCH YOUR IP ADDRESS";
//        }

        setBorder(BorderFactory.createLineBorder(Color.RED));
        setBackground(Color.BLUE);

        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        this.enterPlayerOneNicknameLabel = new JLabel("Enter PLAYER ONE " +
                "desired nickname: ");
        this.enterPlayerOneNicknameLabel.setForeground(Color.RED);
        // player 2
        this.enterPlayerTwoNicknameLabel = new JLabel("Enter PLAYER TWO " +
                "desired nickname: ");
        this.enterPlayerTwoNicknameLabel.setForeground(Color.RED);

        this.playerOneNicknameInputField = new JTextField(20);
        this.playerOneNicknameInputField.setBackground(Color.WHITE);
        this.playerOneNicknameInputField.setForeground(Color.BLUE);
        // player 2
        this.playerTwoNicknameInputField = new JTextField(20);
        this.playerTwoNicknameInputField.setBackground(Color.WHITE);
        this.playerTwoNicknameInputField.setForeground(Color.BLUE);

//        this.enterIpLabel = new JLabel("Enter the IP address of your " +
//                "partner, or wait for them to connect to you.");
//        this.enterIpLabel.setForeground(Color.RED);

//        this.ipInputField = new JTextField(15);
//        this.ipInputField.setBackground(Color.WHITE);
//        this.ipInputField.setForeground(Color.RED);
        this.pointsSelectorLabel = new JLabel("Number of points " +
                "needed to win:");
        this.pointsSelectorLabel.setForeground(Color.RED);
        this.pointsSelector = new JSpinner(new SpinnerNumberModel(defaultPoints, 1,
                maxPoints, 1));
        this.pointsSelector.setForeground(Color.BLUE);

        this.proceedButton = new JButton("Proceed");
        this.proceedButton.setForeground(Color.RED);
        this.proceedButton.setActionCommand("PROCEED");
        this.proceedButton.addActionListener(this);

//        this.clientIpAddrLabel = new JLabel("Your IP:\n" +
//                CLIENT_IP_ADDRESS);
//        this.clientIpAddrLabel.setForeground(Color.YELLOW);
        this.add(this.enterPlayerOneNicknameLabel);
        this.add(this.playerOneNicknameInputField);
        this.add(this.enterPlayerTwoNicknameLabel);
        this.add(this.playerTwoNicknameInputField);
        this.add(this.pointsSelectorLabel);
        this.add(this.pointsSelector);
//        this.add(this.enterIpLabel);
//        this.add(this.ipInputField);
        this.add(this.proceedButton);
//        this.add(this.clientIpAddrLabel);
    }

    void tick() {
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if ("PROCEED".equals(e.getActionCommand())) {
            this.game.createUserOne(this.playerOneNicknameInputField
                    .getText().trim().toUpperCase());
            this.game.createUserTwo(this.playerTwoNicknameInputField
                    .getText().trim().toUpperCase());
            try {
                this.game.setPoints_to_win((int) this.pointsSelector.getValue());
            }
            //error would be thrown if it is not a number
            catch (Throwable error) {
                this.game.setPoints_to_win(7);
            }
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
