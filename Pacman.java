public class Pacman {

    World world1;

    public Pacman(World w) {
        world1 = w;
    }

    //UP: Y wird kleiner
    public void moveUp(){
        if(world1.y-1 >= 0 && world1.xyWorld[world1.y-1%world1.xyWorld.length][world1.x]){
            world1.y--;
        }
    }

    //DOWN: Y wird größer
    public void moveDown(){
        if(world1.y+1 < world1.xyWorld.length && world1.xyWorld[world1.y+1%world1.xyWorld.length][world1.x]){
            world1.y++;
        }
    }

    //LEFT: X wird kleiner
    public void moveLeft(){
        if(world1.x-1 >= 0 && world1.xyWorld[world1.y][world1.x-1%world1.xyWorld[0].length]){
            world1.x--;
        }
    }

    //RIGHT: X wird größer
    public void moveRight(){
        if(world1.x+1 < world1.xyWorld[0].length && world1.xyWorld[world1.y][world1.x+1%world1.xyWorld[0].length]){
            world1.x++;
        }
    }
}
//kommentar zum pushen