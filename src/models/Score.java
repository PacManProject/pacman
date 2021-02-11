//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/Iman859

package src.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

//Model class for .../resources/data/scores/*
public class Score {
    public int highscore;
    public ArrayList<Integer> scores;

    public String playername;

    public Score(String playername, int[] scores, int highscore){
        this.playername = playername;
        this.highscore = highscore;
        this.scores = (ArrayList<Integer>) Arrays.stream(scores).boxed().collect(Collectors.toList());
    }
}
