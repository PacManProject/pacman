package src.gui;

//Initial file creator: Tobi
//Other contributors:
// Benedikt
// Leif


import src.models.Ghost;
import src.models.Pacman;
import src.util.KeyController;
import src.util.MapController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Hauptmenue extends JPanel{

    Gui g;
    JButton startButton = new JButton("START");
    Choice mapAuswahl = new Choice();
    Choice geisterAnzahl = new Choice();
    Choice spielerAuswahl = new Choice();
    JButton spielerErstellen = new JButton("Spieler erstellen");
    JTextField namenEingabe = new JTextField("Hier namen eingeben");
    JLabel ueberschrift = new JLabel("PACMAN");
    JLabel mapText = new JLabel("Map auswählen:");
    JLabel geisterText = new JLabel("Anzahl der Geister auswählen:");
    JLabel namenText = new JLabel("Namen auswählen:");



    public Hauptmenue(Gui gui) {
        g = gui;

        ActionListener startListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.start();
                gui.setGhosts(geisterAnzahl.getSelectedIndex() + 1);
                gui.makeScene(mapAuswahl.getSelectedItem());
            }
        };

        ActionListener namenHinzufuegen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielerAuswahl.add(namenEingabe.getText());
                System.out.println("Spieler erstellt mit namen: " + namenEingabe.getText());
            }
        };
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout(0,2));

        //startButton.setSize( 200, 100);
        startButton.setBackground(Color.GREEN);
        startButton.addActionListener(startListener);



        mapText.setSize(300, 30);
        mapText.setFont(ueberschrift.getFont().deriveFont(15.0f));
        mapText.setForeground(Color.GRAY);

        mapAuswahl.add("default_map");
        mapAuswahl.add("map2");
        mapAuswahl.add("map3");
        mapAuswahl.setBounds(20, 140, 300, 30);
        mapAuswahl.setBackground(Color.GRAY);

        geisterText.setBounds(20, 190, 300, 30);
        geisterText.setFont(ueberschrift.getFont().deriveFont(15.0f));
        geisterText.setForeground(Color.GRAY);

        geisterAnzahl.add("1");
        geisterAnzahl.add("2");
        geisterAnzahl.add("3");
        geisterAnzahl.add("4");
        geisterAnzahl.add("5");
        geisterAnzahl.setBounds(20, 220, 300, 30);
        geisterAnzahl.setBackground(Color.GRAY);

        spielerErstellen.setBounds(20, 350, 200, 30);
        spielerErstellen.setBackground(Color.GRAY);
        spielerErstellen.addActionListener(namenHinzufuegen);

        namenText.setBounds(20, 270, 300, 30);
        namenText.setFont(ueberschrift.getFont().deriveFont(15.0f));
        namenText.setForeground(Color.GRAY);

        spielerAuswahl.setBounds(20, 300, 300, 30);
        spielerAuswahl.setBackground(Color.GRAY);

        namenEingabe.setBounds(20, 385, 300, 30);
        namenEingabe.setBackground(Color.GRAY);

        ueberschrift.setBounds(20, 20, 800, 75);
        ueberschrift.setFont(ueberschrift.getFont().deriveFont(64.0f));
        ueberschrift.setForeground(Color.GRAY);


        JPanel p1 = new JPanel();
        p1.setBackground(Color.DARK_GRAY);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.DARK_GRAY);
        JPanel p3 = new JPanel();
        p3.setBackground(Color.DARK_GRAY);
        JPanel p4 = new JPanel();
        p4.setBackground(Color.DARK_GRAY);
        JPanel p5 = new JPanel();
        p5.setBackground(Color.DARK_GRAY);
        JPanel p6 = new JPanel();
        p6.setBackground(Color.DARK_GRAY);

        this.add(ueberschrift);
        this.add(p1);
        this.add(startButton);
        this.add(p2);
        this.add(namenText);
        this.add(spielerAuswahl);
        this.add(namenEingabe);
        this.add(spielerErstellen);
        this.add(mapText);
        this.add(mapAuswahl);
        this.add(geisterText);
        this.add(geisterAnzahl);





    }
}


