//Initial file creator: https://github.com/TobiwanSUMB
// https://github.com/SomeOtherGod

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
    JSlider musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0 , 100, 100);
    JLabel musicVolumeLabel = new JLabel("Music Volume");
    JSlider effectVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0 , 100, 100);
    JLabel effectVolumeLabel = new JLabel("Effect Volume");
    public SettingPanel(Gui g){
        gui = g;

        ActionListener resumeGame = e -> {
            gui.updateGameGraphics();
            gui.unpause();
        };

        volumeSlider.setBackground(Color.GRAY);
        volumeSlider.setBounds(45, 250, 400 , 30);
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gui.soundController.setMasterVolume((int)volumeSlider.getValue());
            }
        });
        volumeLabel.setBounds(225, 210,100, 40);
        volumeLabel.setForeground(Color.WHITE);

        musicVolumeSlider.setBackground(Color.GRAY);
        musicVolumeSlider.setBounds(45, 150, 400 , 30);
        musicVolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gui.soundController.setMusicVolume((int)volumeSlider.getValue());
            }
        });
        musicVolumeLabel.setBounds(225, 110,100, 40);
        musicVolumeLabel.setForeground(Color.WHITE);

        effectVolumeSlider.setBackground(Color.GRAY);
        effectVolumeSlider.setBounds(45, 200, 400 , 30);
        effectVolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gui.soundController.setEffectVolume((int)volumeSlider.getValue());
            }
        });
        effectVolumeLabel.setBounds(225, 160,100, 40);
        effectVolumeLabel.setForeground(Color.WHITE);

        resume.setBackground(Color.GREEN);
        resume.setBounds(150, 350, 200, 50);
        resume.addActionListener(resumeGame);

        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.add(volumeSlider);
        this.add(volumeLabel);
        this.add(effectVolumeSlider);
        this.add(effectVolumeLabel);
        this.add(musicVolumeSlider);
        this.add(musicVolumeLabel);
        this.add(resume);
    }
}
