//Initial file creator: https://github.com/TobiwanSUMB
//Other contributors:
// https://github.com/dadope
// https://github.com/SomeOtherGod
// https://github.com/Moritz-MT

package src.models;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import src.util.Scoreboard;

public class Pacman extends Thread {
    int score = 0;

    public enum directions {
        Up,
        Down,
        Left,
        Right;

        public static directions switch_dir(directions directions){
            switch (directions){
                case Up: return Down;
                case Down: return Up;
                case Left: return Right;
                case Right: return Left;
            }
            return null;
        }
    }

    int pos_x;
    int pos_y;

    directions direction = directions.Left;
    directions directionNew = direction;
    directions directionNext = direction;

    World currentWorld;
    Scoreboard scoreboard;

    Gson gson = new Gson();
    Path workingDir = Paths.get(System.getProperty("user.dir"));

    public Pacman(String... filename) {
        // if scorename not given, use default.json
        String name = (filename.length >= 1) ? filename[0] : "default.json";
        Path scorePath = Paths.get(workingDir.toString(), "resources", "data", "scores", name);

        String jsonString = null;
        try {
            jsonString = Files.readString(scorePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.scoreboard = gson.fromJson(jsonString, Scoreboard.class);
    }

    public void setCurrentWorld(World currentWorld) {
        this.currentWorld = currentWorld;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public directions getDirection() {
        return direction;
    }

    public void run(){
        setPriority(1);
        while (true){
            try{
                sleep(450);     //Geschwindigkeit von PacMan
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            this.move();
            noPointsLeft();
        }
    }

    public void move(){
        int testLength;
        switch (directionNew){
            case Up:
                testLength = currentWorld.y-1;
                if (testLength < 0) {
                    testLength = currentWorld.currentMap.mapData.length-1;
                }
                if(currentWorld.currentMap.mapData[testLength][currentWorld.x]){
                    if ((currentWorld.y - 1) < 0) {
                        currentWorld.y = testLength;
                    } else {
                        currentWorld.y--;
                    }
                    increasePoints();
                    currentWorld.itemData[currentWorld.y][currentWorld.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();//falls Pacman nicht in die Richtung gehen kann
                }

                break;
            case Down:
                if(currentWorld.currentMap.mapData[(currentWorld.y+1)% currentWorld.currentMap.mapData.length][currentWorld.x]){
                    if ((currentWorld.y + 1) == currentWorld.currentMap.mapData.length) {
                        currentWorld.y = 0;
                    } else {
                        currentWorld.y++;
                    }
                    increasePoints();
                    currentWorld.itemData[currentWorld.y][currentWorld.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Left:
                testLength = currentWorld.x-1;
                if (testLength < 0) {
                    testLength = currentWorld.currentMap.mapData[0].length-1;
                }
                if(currentWorld.currentMap.mapData[currentWorld.y][testLength]){
                    if ((currentWorld.x - 1) < 0) {
                        currentWorld.x = testLength;
                    } else {
                        currentWorld.x--;
                    }
                    increasePoints();
                    currentWorld.itemData[currentWorld.y][currentWorld.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Right:
                if(currentWorld.currentMap.mapData[currentWorld.y][(currentWorld.x+1)% currentWorld.currentMap.mapData[0].length]){
                    if ((currentWorld.x + 1) == currentWorld.currentMap.mapData[0].length) {
                        currentWorld.x = 0;
                    } else {
                        currentWorld.x++;
                    }
                    increasePoints();
                    currentWorld.itemData[currentWorld.y][currentWorld.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            default:
                break;
        }
    }

    public void increasePoints(){
        if (currentWorld.itemData[currentWorld.y][currentWorld.x]){
            score++;
            System.out.println("Score: " + score);
        }
    }


    public void impossibleDirection(){
        directionNext = directionNew;
        directionNew = direction;//Richtung wird zurückgesetzt
        switch (direction) {// erneute Abfrage mit der ursprünglichen Richtung
            case Up:
                if (currentWorld.y - 1 >= 0 && currentWorld.currentMap.mapData[currentWorld.y - 1 % currentWorld.currentMap.mapData.length][currentWorld.x]) {
                    move();
                }
                break;
            case Down:
                if (currentWorld.y + 1 < currentWorld.currentMap.mapData.length && currentWorld.currentMap.mapData[currentWorld.y + 1 % currentWorld.currentMap.mapData.length][currentWorld.x]) {
                    move();
                }
                break;
            case Left:
                if (currentWorld.x - 1 >= 0 && currentWorld.currentMap.mapData[currentWorld.y][currentWorld.x - 1 % currentWorld.currentMap.mapData[0].length]) {
                    move();
                }
                break;
            case Right:
                if (currentWorld.x + 1 < currentWorld.currentMap.mapData[0].length && currentWorld.currentMap.mapData[currentWorld.y][currentWorld.x + 1 % currentWorld.currentMap.mapData[0].length]) {
                    move();
                }
                break;
        }
        directionNew = directionNext;
    }

    public void noPointsLeft(){//Noah deine Methode wenn alle Punkte gegessen sind
        if (score == currentWorld.getMapScore()){
            this.scoreboard.addToCurrentScore(score);

            //FIXME
            //TODO: only save the score when the player dies
        }
    }

    //UP: Y wird kleiner
    public void moveUp(){
        directionNew = directions.Up;
    }

    //DOWN: Y wird größer
    public void moveDown(){
        directionNew = directions.Down;
    }

    //LEFT: X wird kleiner
    public void moveLeft(){
        directionNew = directions.Left;
    }

    //RIGHT: X wird größer
    public void moveRight(){
        directionNew = directions.Right;
    }

    public int getScore() {
        return score;
    }
}