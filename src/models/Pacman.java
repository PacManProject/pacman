//Initial file creator: https://github.com/TobiwanSUMB
//Other contributors:
// https://github.com/dadope
// https://github.com/SomeOtherGod
// https://github.com/Moritz-MT
// https://github.com/Iman859
// https://github.com/LeandroSchadock

package src.models;

import src.util.MapController;
import src.util.ScoreController;

public class Pacman extends Thread {
    //All the possible directions the pacman can go to
    public enum directions {
        Up,
        Down,
        Left,
        Right;

        //Inverts any given direction
        public static directions invertDir(directions directions){
            switch (directions){
                case Up: return Down;
                case Down: return Up;
                case Left: return Right;
                case Right: return Left;
            }
            return null;
        }
    }

    //x axis position
    int pos_x;
    //y axis position
    int pos_y;

    int hp;

    //direction the ghost is currently facing
    directions direction = directions.Left;
    directions directionNew = direction;
    directions directionNext = direction;

    MapController currentMapController;
    ScoreController scoreController;

    public Pacman(String... filename) {
        this.hp = 3;
        this.scoreController = new ScoreController(filename);
    }

    public void updateCurrentWorld(MapController currentMapController) {
        this.currentMapController = currentMapController;

        this.pos_x = currentMapController.currentMap.pos_x;
        this.pos_y = currentMapController.currentMap.pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public ScoreController getScoreboard() {
        return scoreController;
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
                testLength = this.pos_y-1;
                if (testLength < 0) {
                    testLength = currentMapController.currentMap.mapData.length-1;
                }
                if(currentMapController.currentMap.mapData[testLength][this.pos_x]){
                    if ((this.pos_y - 1) < 0) {
                        this.pos_y = testLength;
                    } else {
                        this.pos_y--;
                    }
                    increasePoints();
                    currentMapController.itemData[this.pos_y][this.pos_x] = Items.none;
                    direction = directionNew;
                }else {
                    impossibleDirection();//falls Pacman nicht in die Richtung gehen kann
                }

                break;
            case Down:
                if(currentMapController.currentMap.mapData[(this.pos_y+1)% currentMapController.currentMap.mapData.length][this.pos_x]){
                    if ((this.pos_y + 1) == currentMapController.currentMap.mapData.length) {
                        this.pos_y = 0;
                    } else {
                        this.pos_y++;
                    }
                    increasePoints();
                    currentMapController.itemData[this.pos_y][this.pos_x] = Items.none;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Left:
                testLength = this.pos_x-1;
                if (testLength < 0) {
                    testLength = currentMapController.currentMap.mapData[0].length-1;
                }
                if(currentMapController.currentMap.mapData[this.pos_y][testLength]){
                    if ((this.pos_x - 1) < 0) {
                        this.pos_x = testLength;
                    } else {
                        this.pos_x--;
                    }
                    increasePoints();
                    currentMapController.itemData[this.pos_y][this.pos_x] = Items.none;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Right:
                if(currentMapController.currentMap.mapData[this.pos_y][(this.pos_x+1)% currentMapController.currentMap.mapData[0].length]){
                    if ((this.pos_x + 1) == currentMapController.currentMap.mapData[0].length) {
                        this.pos_x = 0;
                    } else {
                        this.pos_x++;
                    }
                    increasePoints();
                    currentMapController.itemData[this.pos_y][this.pos_x] = Items.none;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            default:
                break;
        }
    }

    //Adds a point to the score if the pacman currently stands in a point
    public void increasePoints(){
        if (currentMapController.itemData[this.pos_y][this.pos_x] != Items.none){
            scoreController.addToCurrentScore();
        }
    }


    public void impossibleDirection(){
        directionNext = directionNew;
        directionNew = direction;//Richtung wird zurückgesetzt
        switch (direction) {// erneute Abfrage mit der ursprünglichen Richtung
            case Up:
                if (this.pos_y - 1 >= 0 && currentMapController.currentMap.mapData[this.pos_y - 1 % currentMapController.currentMap.mapData.length][this.pos_x]) {
                    move();
                }
                break;
            case Down:
                if (this.pos_y + 1 < currentMapController.currentMap.mapData.length && currentMapController.currentMap.mapData[this.pos_y + 1 % currentMapController.currentMap.mapData.length][this.pos_x]) {
                    move();
                }
                break;
            case Left:
                if (this.pos_x - 1 >= 0 && currentMapController.currentMap.mapData[this.pos_y][this.pos_x - 1 % currentMapController.currentMap.mapData[0].length]) {
                    move();
                }
                break;
            case Right:
                if (this.pos_x + 1 < currentMapController.currentMap.mapData[0].length && currentMapController.currentMap.mapData[this.pos_y][this.pos_x + 1 % currentMapController.currentMap.mapData[0].length]) {
                    move();
                }
                break;
        }
        directionNew = directionNext;
    }

    //FIXME only save the score when the player dies
    public void noPointsLeft(){
        if (scoreController.currentMapScore == currentMapController.getMapScore()){
            scoreController.saveScore();
        }
    }

    public int getHp() {
        return hp;
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

    public void loseHp(int... hpToLose){
        this.hp -= (hpToLose.length >=1)? hpToLose[0] : 1;
    }

    public void resetPosition(){
        this.pos_x = currentMapController.currentMap.pos_x;
        this.pos_y = currentMapController.currentMap.pos_y;
    }

    public void die(){
        scoreController.saveScore();
        System.out.println("Died");
        System.exit(0);
    }
}