import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by coxjc on 12/11/16.
 */
public class HighScoresPage extends JPanel {
    private static final int INTERVAL = 35;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final String[] cols = new String[]{"Average MS Per " +
            "Point", "Name", "Date"};
    private Object[][] scoreData;

    public HighScoresPage(ArrayList<ScoreRecord> userData, String winnerName) {
        this.setLayout(new GridLayout(2, 1));
        this.scoreData = new Object[userData.size()][3];
        ScoreRecord tmp;
        for (int i = 0; i < userData.size(); i++) {
            tmp = userData.get(i);
            this.scoreData[i][0] = tmp.getScore();
            this.scoreData[i][1] = tmp.getName();
            this.scoreData[i][2] = timestampStringToDateString(tmp
                    .getTimestamp());
        }
        final JTable scoreTable = new JTable(scoreData, cols);
        final JLabel exitLabel = new JLabel(winnerName + " WINS! Thanks for " +
                "playing, Quaker!");
        exitLabel.setForeground(Color.RED);
        this.add(exitLabel);
        this.add(new JScrollPane(scoreTable));

        scoreTable.setForeground(Color.RED);
        scoreTable.setBackground(Color.WHITE);
        this.setBackground(Color.BLUE);
    }

    public static String timestampStringToDateString(String ts) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultDate = new Date(Long.valueOf(ts));
        return sdf.format(resultDate);
    }

    void tick() {
        repaint();
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
