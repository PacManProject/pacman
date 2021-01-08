package src.models;

public class Pacman {

    String direction = "l";
    String directionNew = direction;

    World world1;

    public Pacman(World w) {
        world1 = w;
    }

    public void move(){
        switch (directionNew){
            case "u":
                if(world1.y-1 >= 0 && world1.xyWorld[world1.y-1%world1.xyWorld.length][world1.x]){
                    world1.y--;
                    direction = directionNew;
                }else {
                    impossibleDirection();//falls Pacman nicht in die Richtung gehen kann
                }

                break;
            case "d":
                if(world1.y+1 < world1.xyWorld.length && world1.xyWorld[world1.y+1%world1.xyWorld.length][world1.x]){
                    world1.y++;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case "l":
                if(world1.x-1 >= 0 && world1.xyWorld[world1.y][world1.x-1%world1.xyWorld[0].length]){
                    world1.x--;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case "r":
                if(world1.x+1 < world1.xyWorld[0].length && world1.xyWorld[world1.y][world1.x+1%world1.xyWorld[0].length]){
                    world1.x++;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            default:
                break;
        }
    }


    public void impossibleDirection(){
        directionNew = direction;//Richtung wird zurückgesetzt
        switch (direction) {// erneute Abfrage mit der ursprünglichen Richtung
            case "u":
                if (world1.y - 1 >= 0 && world1.xyWorld[world1.y - 1 % world1.xyWorld.length][world1.x]) {
                    move();
                }
                break;
            case "d":
                if (world1.y + 1 < world1.xyWorld.length && world1.xyWorld[world1.y + 1 % world1.xyWorld.length][world1.x]) {
                    move();
                }
                break;
            case "l":
                if (world1.x - 1 >= 0 && world1.xyWorld[world1.y][world1.x - 1 % world1.xyWorld[0].length]) {
                    move();
                }
                break;
            case "r":
                if (world1.x + 1 < world1.xyWorld[0].length && world1.xyWorld[world1.y][world1.x + 1 % world1.xyWorld[0].length]) {
                    move();
                }
                break;
        }
    }

    //UP: Y wird kleiner
    public void moveUp(){
        directionNew = "u";
    }

    //DOWN: Y wird größer
    public void moveDown(){
        directionNew = "d";
    }

    //LEFT: X wird kleiner
    public void moveLeft(){
        directionNew = "l";
    }

    //RIGHT: X wird größer
    public void moveRight(){
        directionNew = "r";
    }
}