package src.models;

public class World {
    boolean[][] xyWorld = {
            {true,false,true,false,true,false,true},
            {true,true,true,false,true,true,true},
            {false,true,false,false,false,true,false},
            {true,true,true,true,true,true,true},
            {true,false,false,true,false,false,true},
            {false,false,true,true,true,false,false},
            {true,false,false,true,false,false,true},
            {true,true,true,true,true,true,true}
    }; //false sind nicht begehbare felder

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
