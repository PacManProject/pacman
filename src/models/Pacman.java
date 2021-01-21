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
        Right
    }

    directions direction = directions.Left;
    directions directionNew = direction;
    directions directionNext = direction;

    World world1;
    Scoreboard scoreboard;

    Gson gson = new Gson();
    Path workingDir = Paths.get(System.getProperty("user.dir"));

    public Pacman(World w, String... filename) {
        world1 = w;

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
        switch (directionNew){
            case Up:
                if(world1.y-1 >= 0 && world1.currentMap.mapData[world1.y-1%world1.currentMap.mapData.length][world1.x]){
                    world1.y--;
                    increasePoints();
                    world1.itemData[world1.y][world1.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();//falls Pacman nicht in die Richtung gehen kann
                }

                break;
            case Down:
                if(world1.y+1 < world1.currentMap.mapData.length && world1.currentMap.mapData[world1.y+1%world1.currentMap.mapData.length][world1.x]){
                    world1.y++;
                    increasePoints();
                    world1.itemData[world1.y][world1.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Left:
                if(world1.x-1 >= 0 && world1.currentMap.mapData[world1.y][world1.x-1%world1.currentMap.mapData[0].length]){
                    world1.x--;
                    increasePoints();
                    world1.itemData[world1.y][world1.x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Right:
                if(world1.x+1 < world1.currentMap.mapData[0].length && world1.currentMap.mapData[world1.y][world1.x+1%world1.currentMap.mapData[0].length]){
                    world1.x++;
                    increasePoints();
                    world1.itemData[world1.y][world1.x] = false;
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
        if (world1.itemData[world1.y][world1.x]){
            score++;
            System.out.println("Score: " + score);
        }
    }


    public void impossibleDirection(){
        directionNext = directionNew;
        directionNew = direction;//Richtung wird zurückgesetzt
        switch (direction) {// erneute Abfrage mit der ursprünglichen Richtung
            case Up:
                if (world1.y - 1 >= 0 && world1.currentMap.mapData[world1.y - 1 % world1.currentMap.mapData.length][world1.x]) {
                    move();
                }
                break;
            case Down:
                if (world1.y + 1 < world1.currentMap.mapData.length && world1.currentMap.mapData[world1.y + 1 % world1.currentMap.mapData.length][world1.x]) {
                    move();
                }
                break;
            case Left:
                if (world1.x - 1 >= 0 && world1.currentMap.mapData[world1.y][world1.x - 1 % world1.currentMap.mapData[0].length]) {
                    move();
                }
                break;
            case Right:
                if (world1.x + 1 < world1.currentMap.mapData[0].length && world1.currentMap.mapData[world1.y][world1.x + 1 % world1.currentMap.mapData[0].length]) {
                    move();
                }
                break;
        }
        directionNew = directionNext;
    }

    public void noPointsLeft(){//Noah deine Methode wenn alle Punkte gegessen sind
        if (score == world1.getMapScore()){
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