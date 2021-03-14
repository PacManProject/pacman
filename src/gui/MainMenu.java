package src.gui;

//Initial file creator: https://github.com/TobiwanSUMB
//Other contributors:
// https://github.com/SomeOtherGod
// https://github.com/trolol-bot
// Leif

import src.models.Items;
import src.models.Map;
import src.models.Score;
import src.util.ScoreController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainMenu extends JPanel{

    Gui gui;
    JButton startButton = new JButton("START");
    JButton createPlayer = new JButton("Spieler erstellen");
    JButton editorStarten = new JButton("Editor Starten");

    Choice mapSelection = new Choice();
    Choice playerSelection = new Choice();
    Choice ghostNumberSelection = new Choice();

    JTextField nameInput = new JTextField("Hier namen eingeben");

    JLabel header = new JLabel("PACMAN");
    JLabel mapText = new JLabel("Map auswählen:");
    JLabel nameText = new JLabel("Namen auswählen:");
    JLabel ghostText = new JLabel("Anzahl der Geister auswählen:");
   // JLabel recommendedGhost = new JLabel("Empfolene Geisteranzahl")



    public MainMenu(Gui gui) {
        this.gui = gui;
        ActionListener editorListener = e -> {
            Map_Designer mapDesigner = new Map_Designer();
            mapDesigner.start();
        };

        ActionListener startListener = e -> {
            gui.pacman.setScoreController(new ScoreController(playerSelection.getSelectedItem()));
            this.gui.start();
            gui.setGhosts(ghostNumberSelection.getSelectedIndex() + 1);
            gui.makeScene(mapSelection.getSelectedItem());
        };

        ActionListener namenHinzufuegen = e -> {
            playerSelection.add(nameInput.getText());
            Score scoreToAdd = new Score();
            scoreToAdd.scores = new ArrayList<>();
            scoreToAdd.playername = nameInput.getText();
            scoreToAdd.highscore = 0;

            ScoreController scoreController = new ScoreController(scoreToAdd);
            scoreController.saveScore();

            gui.pacman.setScoreController(scoreController);
            System.out.println("Spieler erstellt mit namen: " + nameInput.getText());
        };

        ItemListener mapChangeListener = new ItemListener () {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int recommendedMapGhosts = 0;
                for (Map map: gui.mapController.getAvailableMaps()) {
                    if (map.name == mapSelection.getSelectedItem()){
                        recommendedMapGhosts = map.itemData.length*map.itemData[0].length;

                    }
                }
                recommendedMapGhosts =  recommendedMapGhosts / 25;
                ghostNumberSelection.select(recommendedMapGhosts-1);

            }
        };
        mapSelection.addItemListener(mapChangeListener);
        this.setBackground(Color.DARK_GRAY);

        //TODO: fixme
        this.setLayout(null);

        editorStarten.setBounds(600, 420, 150, 20);
        editorStarten.setBackground(new Color(73, 0, 52, 255));
        editorStarten.setForeground(new Color (0, 16, 121));
        editorStarten.addActionListener(editorListener);

        startButton.setBounds(450, 150, 200, 150);
        startButton.setBackground(Color.GREEN);
        startButton.addActionListener(startListener);


        mapText.setBounds(20, 100, 300, 30);
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
        this.add(editorStarten);
    }

    private void SetupChoices(){
        for (Map map: gui.mapController.getAvailableMaps()) {
            mapSelection.add(map.name);
        }
        mapSelection.setBounds(20, 140, 300, 30);
        mapSelection.setBackground(Color.GRAY);


        //TODO: add max_ghosts to map
        for (int i = 1; i <= 36; i++) {
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


