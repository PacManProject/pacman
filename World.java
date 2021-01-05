public class World {
    boolean[][] xyWorld = {{true,true,true,true},{false,true,false,false},{false,true,true,true},{false,true,false,false}}; //false sind nicht begehbare felder
    int x = 1, y = 3; // ist position von pacman

    public World(){
    }

    public boolean[][] getXyWorld() {
        return xyWorld;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
