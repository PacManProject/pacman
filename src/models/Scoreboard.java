package src.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        save();

        System.out.println("Added score " + this.currentScore);
        System.out.println("Scores:" + this.scores);

    }

    void save(){
        Path workingDir = Paths.get(System.getProperty("user.dir"));

        //FIXME remove hardcoded filename with playername
        Path filePath = Paths.get(workingDir.toString(), "resources", "data", "scores", playername + ".json");

        try (Writer writer = new FileWriter(String.valueOf(filePath))) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(this, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void addToCurrentScore(int scoreToAdd){
        this.currentScore =+ scoreToAdd;
    }
}
