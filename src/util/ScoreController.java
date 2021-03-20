//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/Iman859

package src.util;

import com.google.gson.Gson;
import src.models.Score;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ScoreController {
    final Gson gson = new Gson();

    final Path _workingDir = Paths.get(System.getProperty("user.dir"));
    final Path _scoresDirectory = Paths.get(_workingDir.toString(), "resources", "data", "scores");

    ArrayList<Score> availableScores = _loadAvailableScores();

    int currentGameScore;
    public int currentMapScore;

    Score score;

    public void setScore(Score score) {
        this.score = score;
    }

    public ScoreController(Score sc){
        score = sc;
        availableScores.add(sc);
    }

    //if playername is given load the Players Score
    public ScoreController(String ...playername) {
        currentMapScore = 0;
        currentGameScore = 0;

        String name = (playername.length >= 1) ? playername[0] : "default";
        score = availableScores.stream().filter(score_to_find -> name.equals(score_to_find.playername)).findFirst().orElse(availableScores.get(0));
    }

    private ArrayList<Score> _loadAvailableScores(){
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


    public void addToCurrentScore(int... scoreToAdd){
        int score = (scoreToAdd.length >= 1) ? scoreToAdd[0] : 1;
        this.currentMapScore += score;
        this.currentGameScore += score;
    }

    // saves the Score into a json file withe the playername as title
    public void saveScore(){
        if (score.highscore < currentGameScore) score.highscore = currentGameScore;

        score.scores.add(currentGameScore);

        try (Writer writer = new FileWriter(String.valueOf(Paths.get(String.valueOf(_scoresDirectory), score.playername + ".json")))) {
            gson.toJson(score, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentGameScore() {
        return currentGameScore;
    }

    public ArrayList<Score> getAvailableScores() {
        return availableScores;
    }
}
