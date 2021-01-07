package src.models;

public class Pacman {

    String direction = "l";

    World world1;

    public Pacman(World w) {
        world1 = w;
    }

    public void move(){
        switch (direction){
            case "u":
                if(world1.y-1 >= 0 && world1.xyWorld[world1.y-1%world1.xyWorld.length][world1.x]){
                    world1.y--;
                }
                break;
            case "d":
                if(world1.y+1 < world1.xyWorld.length && world1.xyWorld[world1.y+1%world1.xyWorld.length][world1.x]){
                    world1.y++;
                }
                break;
            case "l":
                if(world1.x-1 >= 0 && world1.xyWorld[world1.y][world1.x-1%world1.xyWorld[0].length]){
                    world1.x--;
                }
                break;
            case "r":
                if(world1.x+1 < world1.xyWorld[0].length && world1.xyWorld[world1.y][world1.x+1%world1.xyWorld[0].length]){
                    world1.x++;
                }
                break;
            default:
                break;
        }
    }

    //UP: Y wird kleiner
    public void moveUp(){
        direction = "u";
    }

    //DOWN: Y wird größer
    public void moveDown(){
        direction = "d";
    }

    //LEFT: X wird kleiner
    public void moveLeft(){
        direction = "l";
    }

    //RIGHT: X wird größer
    public void moveRight(){
        direction = "r";
    }
}