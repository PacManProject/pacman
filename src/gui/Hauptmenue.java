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
            }
        };

        ActionListener namenHinzufuegen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielerAuswahl.add(namenEingabe.getText());
                System.out.println("Spieler erstellt mit namen: "+ namenEingabe.getText());
            }
        };



        startButton.setBounds(500 ,175, 200, 100 );
        startButton.setBackground(Color.GREEN);
        startButton.addActionListener(startListener);

        mapText.setBounds(20,  110, 300, 30);
        mapText.setFont(ueberschrift.getFont().deriveFont(15.0f));
        mapText.setForeground(Color.GRAY);

        mapAuswahl.add("default_map");
        mapAuswahl.add("map2");
        mapAuswahl.add("map3");
        mapAuswahl.setBounds(20, 140, 300, 30);
        mapAuswahl.setBackground(Color.GRAY);


        geisterText.setBounds(20,  190, 300, 30);
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

        namenText.setBounds(20,  270, 300, 30);
        namenText.setFont(ueberschrift.getFont().deriveFont(15.0f));
        namenText.setForeground(Color.GRAY);

        spielerAuswahl.setBounds(20, 300, 300, 30 );
        spielerAuswahl.setBackground(Color.GRAY);

        namenEingabe.setBounds(20, 385, 300, 30);
        namenEingabe.setBackground(Color.GRAY);

        ueberschrift.setBounds(20, 20, 800, 75);
        ueberschrift.setFont(ueberschrift.getFont().deriveFont(64.0f));
        ueberschrift.setForeground(Color.GRAY);



        this.add(startButton);
        this.add(ueberschrift);
        this.add(namenEingabe);
        this.add(spielerAuswahl);
        this.add(mapAuswahl);
        this.add(geisterAnzahl);
        this.add(spielerErstellen);
        this.add(geisterText);
        this.add(namenText);
        this.add(mapText);

    }







}


