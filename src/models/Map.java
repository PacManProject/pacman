package src.models;

public class Map {
    String name;
    int pos_x;
    int pos_y;

    boolean[][] data;


    public Map(String name, int pos_x, int pos_y, boolean[][] data){
        this.name = name;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.data = data;
    }
}
