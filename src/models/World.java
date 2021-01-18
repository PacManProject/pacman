package src.models;

import com.google.gson.*;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

//TODO: merge World and Map
public class World {
    Gson gson = new Gson();
    Path workingDir = Paths.get(System.getProperty("user.dir"));

    int x;
    int y;

    String name;
    boolean[][] xyWorld;
    boolean[][] itemXyWorld;
    int counter;
    Map map;

    public World(String... mapName) {

        // if mapName not given, use default_map.json
        String name = (mapName.length >= 1) ? mapName[0] : "default_map.json";
        Path mapPath = Paths.get(workingDir.toString(), "resources", "data", "maps", name);

        String jsonString = null;
        try {
            jsonString = Files.readString(mapPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map map = gson.fromJson(jsonString, Map.class);

        this.x = map.pos_x;
        this.y = map.pos_y;
        this.name = map.name;
        this.xyWorld = map.data;
        this.itemXyWorld = map.itemData;
        this.itemXyWorld[y][x] = false;     //Am Spawn, spawnt kein Punkt
        countPointsOnWorld();
    }

    public void update(String... mapName) {

        // if mapName not given, use default_map.json
        String name = (mapName.length >= 1) ? mapName[0] : "default_map.json";
        Path mapPath = Paths.get(workingDir.toString(), "resources", "data", "maps", name);

        String jsonString = null;
        try {
            jsonString = Files.readString(mapPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        map = gson.fromJson(jsonString, Map.class);

        this.x = map.pos_x;
        this.y = map.pos_y;
        this.name = map.name;
        this.xyWorld = map.data;
        this.itemXyWorld = map.itemData;
        this.itemXyWorld[y][x] = false;     //Am Spawn, spawnt kein Punkt
        countPointsOnWorld();
    }

    public boolean[][] getXyWorld() {
        return xyWorld;
    }

    public boolean[][] getItemXyWorld() {
        return itemXyWorld;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void countPointsOnWorld(){
        counter = 0;
        for (int x = 0; x < itemXyWorld.length; x++) {
            for (int y = 0; y < itemXyWorld[0].length; y++) {
                if (itemXyWorld[x][y]){//Wenn es ein freies Feld gibt wird der Punktezähler erhöht
                    counter++;
                }
            }
        }
        System.out.println("Anzahl Punkte: " + counter);
    }

    public int getCounter(){
        return counter;
    }

}
