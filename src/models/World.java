package src.models;

import com.google.gson.*;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Stream;

public class World {
    Gson gson = new Gson();
    final Path _workingDir = Paths.get(System.getProperty("user.dir"));
    final Path _mapsDirectory = Paths.get(_workingDir.toString(), "resources", "data", "maps");

    String name;
    Map currentMap;
    ArrayList<Map> availableMaps = _loadAvailableMaps();

    int x;
    int y;

    boolean[][] xyWorld;
    boolean[][] itemXyWorld;
    int mapScore;

    public World(String... mapName) {
        // if mapName not given, use default_map.json
        String name = (mapName.length >= 1) ? mapName[0] : "default_map";
        currentMap = availableMaps.stream().filter(map_to_find -> name.equals(map_to_find.name)).findFirst().orElse(availableMaps.get(0));

        this.x = currentMap.pos_x;
        this.y = currentMap.pos_y;
        this.name = currentMap.name;
        this.xyWorld = currentMap.data;
        this.itemXyWorld = currentMap.itemData;
        this.itemXyWorld[y][x] = false;     //Am Spawn, spawnt kein Punkt
        countPointsOnWorld();
    }

    public void update(String... mapName) {

        String name = (mapName.length >= 1) ? mapName[0] : "default_map";
        currentMap = availableMaps.stream().filter(map_to_find -> name.equals(map_to_find.name)).findFirst().orElse(availableMaps.get(0));

        this.x = currentMap.pos_x;
        this.y = currentMap.pos_y;
        this.name = currentMap.name;
        this.xyWorld = currentMap.data;
        this.itemXyWorld = currentMap.itemData;
        this.itemXyWorld[y][x] = false;     //Am Spawn, spawnt kein Punkt
        countPointsOnWorld();
    }

    private ArrayList<Map> _loadAvailableMaps(){
        availableMaps = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(_mapsDirectory)) {
            paths
                .filter(Files::isRegularFile)
                .forEach(
                    item -> {
                        try {
                            availableMaps.add(gson.fromJson(Files.readString(item, StandardCharsets.UTF_8), Map.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                );
        }  catch (IOException e){
            e.printStackTrace();
        }
        return availableMaps;
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
        mapScore = 0;
        for (boolean[] booleans : itemXyWorld) {
            for (int y = 0; y < itemXyWorld[0].length; y++) {
                if (booleans[y]) {//Wenn es ein freies Feld gibt wird der Punktezähler erhöht
                    mapScore++;
                }
            }
        }
        System.out.println("Anzahl Punkte: " + mapScore);
    }

    public int getMapScore(){
        return mapScore;
    }

}
