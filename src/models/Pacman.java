package src.models;

public class Pacman {

    public enum directions {
        Up,
        Down,
        Left,
        Right
    };

    directions direction = directions.Left;
    directions directionNew = direction;

    World world1;

    public Pacman(World w) {
        world1 = w;
    }

    public directions getDirection() {
        return direction;
    }

    public void move(){
        switch (directionNew){
            case Up:
                if(world1.y-1 >= 0 && world1.xyWorld[world1.y-1%world1.xyWorld.length][world1.x]){
                    world1.y--;
                    direction = directionNew;
                }else {
                    impossibleDirection();//falls Pacman nicht in die Richtung gehen kann
                }

                break;
            case Down:
                if(world1.y+1 < world1.xyWorld.length && world1.xyWorld[world1.y+1%world1.xyWorld.length][world1.x]){
                    world1.y++;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Left:
                if(world1.x-1 >= 0 && world1.xyWorld[world1.y][world1.x-1%world1.xyWorld[0].length]){
                    world1.x--;
                    direction = directionNew;
                }else {
                    impossibleDirection();
                }
                break;
            case Right:
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
            case Up:
                if (world1.y - 1 >= 0 && world1.xyWorld[world1.y - 1 % world1.xyWorld.length][world1.x]) {
                    move();
                }
                break;
            case Down:
                if (world1.y + 1 < world1.xyWorld.length && world1.xyWorld[world1.y + 1 % world1.xyWorld.length][world1.x]) {
                    move();
                }
                break;
            case Left:
                if (world1.x - 1 >= 0 && world1.xyWorld[world1.y][world1.x - 1 % world1.xyWorld[0].length]) {
                    move();
                }
                break;
            case Right:
                if (world1.x + 1 < world1.xyWorld[0].length && world1.xyWorld[world1.y][world1.x + 1 % world1.xyWorld[0].length]) {
                    move();
                }
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