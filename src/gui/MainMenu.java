package src.gui;

//Initial file creator: https://github.com/TobiwanSUMB
//Other contributors:
// https://github.com/SomeOtherGod
// https://github.com/trolol-bot
// Leif

import src.models.Map;
import src.models.Score;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JPanel{

    Gui gui;
    JButton startButton = new JButton("START");
    JButton createPlayer = new JButton("Spieler erstellen");

    Choice mapSelection = new Choice();
    Choice playerSelection = new Choice();
    Choice ghostNumberSelection = new Choice();

    JTextField nameInput = new JTextField("Hier namen eingeben");

    JLabel header = new JLabel("PACMAN");
    JLabel mapText = new JLabel("Map auswählen:");
    JLabel nameText = new JLabel("Namen auswählen:");
    JLabel ghostText = new JLabel("Anzahl der Geister auswählen:");



    public MainMenu(Gui gui) {
        this.gui = gui;

        ActionListener startListener = e -> {
            this.gui.start();
            gui.setGhosts(ghostNumberSelection.getSelectedIndex() + 1);
            gui.makeScene(mapSelection.getSelectedItem());
        };

        ActionListener namenHinzufuegen = e -> {
            playerSelection.add(nameInput.getText());
            System.out.println("Spieler erstellt mit namen: " + nameInput.getText());
        };

        this.setBackground(Color.DARK_GRAY);

        //TODO: fixme
        this.setLayout(new GridLayout(0, 2));

        //startButton.setSize( 200, 100);
        startButton.setBackground(Color.GREEN);
        startButton.addActionListener(startListener);


        mapText.setSize(300, 30);
        mapText.setFont(header.getFont().deriveFont(15.0f));
        mapText.setForeground(Color.GRAY);

        ghostText.setBounds(20, 190, 300, 30);
        ghostText.setFont(header.getFont().deriveFont(15.0f));
        ghostText.setForeground(Color.GRAY);

        createPlayer.setBounds(20, 350, 200, 30);
        createPlayer.setBackground(Color.GRAY);
        createPlayer.addActionListener(namenHinzufuegen);

        nameText.setBounds(20, 270, 300, 30);
        nameText.setFont(header.getFont().deriveFont(15.0f));
        nameText.setForeground(Color.GRAY);

        playerSelection.setBounds(20, 300, 300, 30);
        playerSelection.setBackground(Color.GRAY);

        nameInput.setBounds(20, 385, 300, 30);
        nameInput.setBackground(Color.GRAY);

        header.setBounds(20, 20, 800, 75);
        header.setFont(header.getFont().deriveFont(64.0f));
        header.setForeground(Color.GRAY);

        SetupChoices();

        JPanel p1 = new JPanel();
        p1.setBackground(Color.DARK_GRAY);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.DARK_GRAY);

        this.add(header);
        this.add(p1);
        this.add(startButton);
        this.add(p2);
        this.add(nameText);
        this.add(playerSelection);
        this.add(nameInput);
        this.add(createPlayer);
        this.add(mapText);
        this.add(mapSelection);
        this.add(ghostText);
        this.add(ghostNumberSelection);
    }

    private void SetupChoices(){

        for (Map map: gui.mapController.getAvailableMaps()) {
            mapSelection.add(map.name);
        }
        mapSelection.setBounds(20, 140, 300, 30);
        mapSelection.setBackground(Color.GRAY);


        //TODO: add max_ghosts to map
        for (int i = 1; i <= 5; i++) {
            ghostNumberSelection.add(Integer.toString(i));
        }

        ghostNumberSelection.setBounds(20, 220, 300, 30);
        ghostNumberSelection.setBackground(Color.GRAY);

        for (Score player: gui.pacman.getScoreboard().getAvailableScores()) {
            playerSelection.add(player.playername);
        }

        playerSelection.setBounds(20, 300, 300, 30);
        playerSelection.setBackground(Color.GRAY);
    }
}


