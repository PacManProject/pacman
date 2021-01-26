//Initial file creator: https://github.com/TobiwanSUMB
//Other contributors:
// https://github.com/dadope
// https://github.com/SomeOtherGod
// https://github.com/Moritz-MT

package src.models;

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

    public void increasePoints(){
        if (currentWorld.itemData[this.pos_y][this.pos_x]){
            score++;
            System.out.println("Score: " + score);
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