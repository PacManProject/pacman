//Initial file creator: https://github.com/SomeOtherGod

package src.models;

import src.util.GhostKi;

public class Ghost extends Thread {
    public enum directions {
        Up,
        Down,
        Left,
        Right
    }

    directions direction = directions.Left;
    directions directionNew = direction;

    World world1;

    GhostKi ki = new GhostKi(this);

    public Ghost(World w) {
        world1 = w;
        this.setLocation();
    }

    public void setLocation() {
        while (true) {
            int i = (int)(Math.random()*world1.currentMap.mapData.length), j = (int)(Math.random()*world1.currentMap.mapData[0].length);
            if (world1.currentMap.mapData[i][j]) {
                world1.xg1 = j;
                world1.yg1 = i;
                break;
            }
        }
    }

    public void run(){
        ki.start();
        setPriority(1);
        while (true){
            try{
                sleep(450);     //Geschwindigkeit von Ghost
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            this.move();
        }
    }

    public void move(){
        switch (directionNew){
            case Up:
                if(world1.yg1-1 >= 0 && world1.currentMap.mapData[world1.yg1-1%world1.currentMap.mapData.length][world1.xg1]){
                    world1.yg1--;
                    direction = directionNew;
                }
                break;
            case Down:
                if(world1.yg1+1 < world1.currentMap.mapData.length && world1.currentMap.mapData[world1.yg1+1%world1.currentMap.mapData.length][world1.xg1]){
                    world1.yg1++;
                    direction = directionNew;
                }
                break;
            case Left:
                if(world1.xg1-1 >= 0 && world1.currentMap.mapData[world1.yg1][world1.xg1-1%world1.currentMap.mapData[0].length]){
                    world1.xg1--;
                    direction = directionNew;
                }
                break;
            case Right:
                if(world1.xg1+1 < world1.currentMap.mapData[0].length && world1.currentMap.mapData[world1.yg1][world1.xg1+1%world1.currentMap.mapData[0].length]){
                    world1.xg1++;
                    direction = directionNew;
                }
                break;
            default:
                break;
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