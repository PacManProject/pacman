package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingPanel extends JPanel {
    Gui gui;
    JButton resume = new JButton("Zurück zum Spiel");
    public SettingPanel(Gui g){
        gui = g;

        ActionListener resumeGame = e -> {
            gui.updateGameGraphics();
            //gui.continue();
        };

        resume.setBackground(Color.GREEN);
        resume.setBounds(150, 350, 200, 50);
        resume.addActionListener(resumeGame);

        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.add(resume);
    }
}
