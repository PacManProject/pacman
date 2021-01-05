public class World {
    boolean[][] xyWorld = {{true,true,true,true},{false,true,false,false},{false,true,true,true},{false,true,false,false}}; //false sind nicht begehbare felder
    int x, y; // ist position von pacman

    public World(int xx, int yy){
        x = xx;
        y = yy;
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
