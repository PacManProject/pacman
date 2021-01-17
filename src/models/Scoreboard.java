package src.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Scoreboard {

    int highscore;
    int currentScore;

    String playername;
    ArrayList<Integer> scores;

    public Scoreboard(String playername, int[] scores, int highscore) {
        this.currentScore = 0;

        this.highscore = highscore;
        this.playername = playername;
        this.scores = (ArrayList<Integer>) Arrays.stream(scores).boxed().collect(Collectors.toList());
    }

    void addScore(){
        if (this.currentScore > this.highscore) this.highscore = this.currentScore;
        this.scores.add(this.currentScore);

        //TODO: add save function
        System.out.println("Added score " + this.currentScore);
        System.out.println("Scores:" + this.scores);
    }

    void addToCurrentScore(int scoreToAdd){
        this.currentScore =+ scoreToAdd;
    }
}
