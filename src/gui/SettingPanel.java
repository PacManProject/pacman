//Initial file creator: https://github.com/TobiwanSUMB
// https://github.com/SomeOtherGod
// https://github.com/dadope

package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingPanel extends JFrame {
    final Gui gui;
    final JButton resume = new JButton("Zurück zum Spiel");
    final JSlider musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0 , 100, 50);
    final JLabel musicVolumeLabel = new JLabel("Music Volume");
    final JSlider effectVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0 , 100, 50);
    final JLabel effectVolumeLabel = new JLabel("Effect Volume");


    public SettingPanel(Gui g, boolean ResumeGame){
        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(500,500);
        this.setBackground(Color.DARK_GRAY);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        gui = g;

        ActionListener resumeGame = e -> {

            if(ResumeGame) {
                gui.updateGameGraphics();
                gui.unpause();
            }
            this.dispose();
        };

        musicVolumeSlider.setBackground(Color.GRAY);
        musicVolumeSlider.setBounds(45, 260, 400 , 30);
        musicVolumeSlider.addChangeListener(e -> gui.soundController.setMusicVolume(musicVolumeSlider.getValue()));
        musicVolumeSlider.setValue(gui.pacman.settingsController.settings.sound.get("music"));


        musicVolumeLabel.setBounds(225, 220,100, 40);
        musicVolumeLabel.setForeground(Color.WHITE);

        effectVolumeSlider.setBackground(Color.GRAY);
        effectVolumeSlider.setBounds(45, 200, 400 , 30);
        effectVolumeSlider.addChangeListener(e -> gui.soundController.setEffectVolume(effectVolumeSlider.getValue()));
        effectVolumeSlider.setValue(gui.pacman.settingsController.settings.sound.get("effects"));

        effectVolumeLabel.setBounds(225, 160,100, 40);
        effectVolumeLabel.setForeground(Color.WHITE);

        resume.setBackground(Color.GREEN);
        resume.setBounds(150, 350, 200, 50);
        resume.addActionListener(resumeGame);

        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.add(effectVolumeSlider);
        this.add(effectVolumeLabel);
        this.add(musicVolumeSlider);
        this.add(musicVolumeLabel);
        this.add(resume);

        this.setVisible(true);
    }
}
