package src.models;

public class Score {
    int score = 0;

    public void changeScore(int i) {
        this.score = score+i;
    }

    public int getScore() {
        return score;
    }
}
