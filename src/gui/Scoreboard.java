//Initial file creator: https://github.com/TobiwanSUMB
//Other contributors:
// https://github.com/Dadope
// https://github.com/trolol-bot
// Leif

package src.gui;

import src.models.Pacman;
import src.models.Score;
import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JFrame {
    public Scoreboard(Pacman pacman){
        this.setTitle("SCOREBOARD");
        this.setSize(500, pacman.getScoreboard().getAvailableScores().size()*105+50); // Länge des Frames abhängig von der anzahl der spieler mit 105 abstand
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //Bei schließung nur das Fenster
        this.setVisible(true);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setLocationRelativeTo(null);  // Start in der mitte des Bildschirms

        int y = 20;

        JLabel ueberschrift = new JLabel("SCOREBOARD");
        ueberschrift.setBounds(20, 20, 400, 50);
        ueberschrift.setFont(ueberschrift.getFont().deriveFont(25.0f));  // Große Schrift
        ueberschrift.setVisible(true);
        ueberschrift.setForeground(Color.GRAY);
        this.add(ueberschrift);

        for (Score sc: pacman.getScoreboard().getAvailableScores()) {
            JLabel scoreLabel = new JLabel("Punkte: "+sc.scores.toString());  // Scores Label wird erstellt
            scoreLabel.setBounds(20, y+65, 400, 10);
            scoreLabel.setForeground(Color.GRAY);
            scoreLabel.setVisible(true);

            JLabel nameLabel = new JLabel("Name: "+ sc.playername);  // Namen Label wird erstellt
            nameLabel.setBounds(20, y+50, 400, 15);
            nameLabel.setForeground(Color.GRAY);
            nameLabel.setVisible(true);

            this.add(scoreLabel);
            this.add(nameLabel);
            y += 100;

        }
        JLabel leeresLabel = new JLabel("");  // Leeres Label muss aus Formatierungsgründen hinzugefügt werden
        this.add(leeresLabel);

    }
}
