package src.gui;

import src.models.Score;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Scoreboard extends JFrame {
    public Scoreboard(ArrayList<Score> ava){
        this.setTitle("SCOREBOARD");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
        JLabel l4 = new JLabel();
        JLabel l5 = new JLabel();
        JLabel l6 = new JLabel();
        JLabel l7 = new JLabel();
        JLabel l8 = new JLabel();
        JLabel l9 = new JLabel();
        JLabel l10 = new JLabel();

        l1.setBounds(20, 20, 100, 50);
        l2.setBounds(200, 20, 100, 50);

        l3.setBounds(20, 80, 100, 50);
        l4.setBounds(200, 80, 100, 50);

//        ava.sort(new Comparator<Score>() {
//            @Override
//            public int compare(Score o1, Score o2) {
//                return 0;
//            }
//        });

        for (Score sc: ava) {
            String s = String.valueOf(sc.highscore);
        }

        switch (ava.size()) {
            case 1:
                String erster_score = String.valueOf(ava.get(0).highscore);
                String erster_name = String.valueOf(ava.get(0).playername);
                l1.setText(erster_score);
                l1.setText(erster_name);

            case 2:
                erster_score = String.valueOf(ava.get(0).highscore);
                erster_name = String.valueOf(ava.get(0).playername);
                l1.setText(erster_score);
                l1.setText(erster_name);

                String zweiter_score = String.valueOf(ava.get(1).highscore);
                String zweiter_name = String.valueOf(ava.get(1).playername);
                l2.setText(zweiter_score);
                l2.setText(zweiter_name);

            case 3:
                erster_score = String.valueOf(ava.get(0).highscore);
                erster_name = String.valueOf(ava.get(0).playername);
                l1.setText(erster_score);
                l1.setText(erster_name);

                zweiter_score = String.valueOf(ava.get(1).highscore);
                zweiter_name = String.valueOf(ava.get(1).playername);
                l2.setText(zweiter_score);
                l2.setText(zweiter_name);

                String dritter_score = String.valueOf(ava.get(2).highscore);
                String dritter_name = String.valueOf(ava.get(2).playername);
                l3.setText(dritter_score);
                l3.setText(dritter_name);

            case 4:
                erster_score = String.valueOf(ava.get(0).highscore);
                erster_name = String.valueOf(ava.get(0).playername);
                l1.setText(erster_score);
                l1.setText(erster_name);

                zweiter_score = String.valueOf(ava.get(1).highscore);
                zweiter_name = String.valueOf(ava.get(1).playername);
                l2.setText(zweiter_score);
                l2.setText(zweiter_name);

                dritter_score = String.valueOf(ava.get(2).highscore);
                dritter_name = String.valueOf(ava.get(2).playername);
                l3.setText(dritter_score);
                l3.setText(dritter_name);

                String vierter_score = String.valueOf(ava.get(3).highscore);
                String vierter_name = String.valueOf(ava.get(3).playername);


            case 5:
                erster_score = String.valueOf(ava.get(0).highscore);
                erster_name = String.valueOf(ava.get(0).playername);
                l1.setText(erster_score);
                l1.setText(erster_name);

                zweiter_score = String.valueOf(ava.get(1).highscore);
                zweiter_name = String.valueOf(ava.get(1).playername);
                l2.setText(zweiter_score);
                l2.setText(zweiter_name);

                dritter_score = String.valueOf(ava.get(2).highscore);
                dritter_name = String.valueOf(ava.get(2).playername);
                l3.setText(dritter_score);
                l3.setText(dritter_name);

                vierter_score = String.valueOf(ava.get(3).highscore);
                vierter_name = String.valueOf(ava.get(3).playername);

                String fünfter_score = String.valueOf(ava.get(4).highscore);
                String fünfter_name = String.valueOf(ava.get(4).playername);
        }





        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
        this.add(l7);
        this.add(l8);
        this.add(l9);
        this.add(l10);

    }

}
