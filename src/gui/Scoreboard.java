package src.gui;

import src.models.Pacman;
import src.models.Score;
import javax.swing.*;

public class Scoreboard extends JFrame {
    public Scoreboard(Pacman pacman){
        this.setTitle("SCOREBOARD");
        this.setSize(500, pacman.getScoreboard().getAvailableScores().size()*100);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        int y = 0;

        for (Score sc: pacman.getScoreboard().getAvailableScores()) {
            JLabel label = new JLabel(sc.playername);
            label.setBounds(0, y, 100, 100);
            label.setVisible(true);

            this.add(label);
        }
    }
}
