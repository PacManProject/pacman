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
