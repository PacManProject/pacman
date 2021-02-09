//Initial file creator: https://github.com/TobiwanSUMB
//Other contributors:
// https://github.com/dadope
// https://github.com/SomeOtherGod
// https://github.com/Moritz-MT
// https://github.com/Iman859

package src.models;

import src.util.Scoreboard;

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

    //direction the ghost is currently facing
    directions direction = directions.Left;
    directions directionNew = direction;
    directions directionNext = direction;

    World currentWorld;
    Scoreboard scoreboard;

    public Pacman(String... filename) {
        this.scoreboard = new Scoreboard(filename);
    }

    public void updateCurrentWorld(World currentWorld) {
        this.currentWorld = currentWorld;

        this.pos_x = currentWorld.currentMap.pos_x;
        this.pos_y = currentWorld.currentMap.pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
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
                    testLength = currentWorld.currentMap.mapData.length-1;
                }
                if(currentWorld.currentMap.mapData[testLength][this.pos_x]){
                    if ((this.pos_y - 1) < 0) {
                        this.pos_y = testLength;
                    } else {
                        this.pos_y--;
                    }
                    increasePoints();
                    currentWorld.itemData[this.pos_y][this.pos_x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();//falls Pacman nicht in die Richtung gehen kann
                }

                break;
            case Down:
                if(currentWorld.currentMap.mapData[(this.pos_y+1)% currentWorld.currentMap.mapData.length][this.pos_x]){
                    if ((this.pos_y + 1) == currentWorld.currentMap.mapData.length) {
                        this.pos_y = 0;
                    } else {
                        this.pos_y++;
                    }
                    increasePoints();
                    currentWorld.itemData[this.pos_y][this.pos_x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Left:
                testLength = this.pos_x-1;
                if (testLength < 0) {
                    testLength = currentWorld.currentMap.mapData[0].length-1;
                }
                if(currentWorld.currentMap.mapData[this.pos_y][testLength]){
                    if ((this.pos_x - 1) < 0) {
                        this.pos_x = testLength;
                    } else {
                        this.pos_x--;
                    }
                    increasePoints();
                    currentWorld.itemData[this.pos_y][this.pos_x] = false;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Right:
                if(currentWorld.currentMap.mapData[this.pos_y][(this.pos_x+1)% currentWorld.currentMap.mapData[0].length]){
                    if ((this.pos_x + 1) == currentWorld.currentMap.mapData[0].length) {
                        this.pos_x = 0;
                    } else {
                        this.pos_x++;
                    }
                    increasePoints();
                    currentWorld.itemData[this.pos_y][this.pos_x] = false;
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
        if (currentWorld.itemData[this.pos_y][this.pos_x]){
            scoreboard.addToCurrentScore();
        }
    }


    public void impossibleDirection(){
        directionNext = directionNew;
        directionNew = direction;//Richtung wird zurückgesetzt
        switch (direction) {// erneute Abfrage mit der ursprünglichen Richtung
            case Up:
                if (this.pos_y - 1 >= 0 && currentWorld.currentMap.mapData[this.pos_y - 1 % currentWorld.currentMap.mapData.length][this.pos_x]) {
                    move();
                }
                break;
            case Down:
                if (this.pos_y + 1 < currentWorld.currentMap.mapData.length && currentWorld.currentMap.mapData[this.pos_y + 1 % currentWorld.currentMap.mapData.length][this.pos_x]) {
                    move();
                }
                break;
            case Left:
                if (this.pos_x - 1 >= 0 && currentWorld.currentMap.mapData[this.pos_y][this.pos_x - 1 % currentWorld.currentMap.mapData[0].length]) {
                    move();
                }
                break;
            case Right:
                if (this.pos_x + 1 < currentWorld.currentMap.mapData[0].length && currentWorld.currentMap.mapData[this.pos_y][this.pos_x + 1 % currentWorld.currentMap.mapData[0].length]) {
                    move();
                }
                break;
        }
        directionNew = directionNext;
    }

    //FIXME only save the score when the player dies
    public void noPointsLeft(){
        if (scoreboard.currentMapScore == currentWorld.getMapScore()){
            scoreboard.saveScore();
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
}