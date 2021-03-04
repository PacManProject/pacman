package src.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingPanel extends JPanel {
    Gui gui;
    JButton resume = new JButton("Zurück zum Spiel");
    JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0 , 100, 50);
    JLabel volumeLabel = new JLabel("Volume");
    public SettingPanel(Gui g){
        gui = g;

        ActionListener resumeGame = e -> {
            gui.updateGameGraphics();
            //gui.continue();
        };

        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setMinorTickSpacing(1);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setBackground(Color.GRAY);
        volumeSlider.setBounds(45, 250, 400 , 60);
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gui.soundController.setVolume((int)volumeSlider.getValue());
            }
        });
        volumeLabel.setBounds(225, 210,100, 40);
        volumeLabel.setForeground(Color.WHITE);

        resume.setBackground(Color.GREEN);
        resume.setBounds(150, 350, 200, 50);
        resume.addActionListener(resumeGame);

        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.add(volumeSlider);
        this.add(volumeLabel);
        this.add(resume);
    }
}
