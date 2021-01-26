//Initial file creator: https://github.com/dadope

package src.util;

import com.google.gson.Gson;

import src.models.Score;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Scoreboard {
    Gson gson = new Gson();

    final Path _workingDir = Paths.get(System.getProperty("user.dir"));
    final Path _scoresDirectory = Paths.get(_workingDir.toString(), "resources", "data", "scores");

    Score score;
    ArrayList<Score> availableScores = _loadAvailableScores();

    int currentScore;

    public Scoreboard(String ...playername) {
        this.currentScore = 0;

        String name = (playername.length >= 1) ? playername[0] : "default";
        score = availableScores.stream().filter(score_to_find -> name.equals(score_to_find.playername)).findFirst().orElse(availableScores.get(0));
    }

    public ArrayList<Score> _loadAvailableScores(){
        availableScores = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(_scoresDirectory)) {
            paths
                .filter(Files::isRegularFile)
                .forEach(
                    item -> {
                        try {
                            availableScores.add(gson.fromJson(Files.readString(item, StandardCharsets.UTF_8), Score.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                );
        }  catch (IOException e){
            e.printStackTrace();
        }
        return availableScores;
    }


    public void addToCurrentScore(int scoreToAdd){
        this.currentScore =+ scoreToAdd;
    }
}
