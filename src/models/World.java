//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/SomeOtherGod
// https://github.com/Moritz-MT

package src.models;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;//Other contributors:
import java.nio.charset.StandardCharsets;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

public class World {
    //Gson object used for json conversion
    Gson gson = new Gson();

    //Game working-directory, should correspond to the project root dir
    final Path _workingDir = Paths.get(System.getProperty("user.dir"));
    ////Path to the maps => .../resources/data/maps/
    final Path _mapsDirectory = Paths.get(_workingDir.toString(), "resources", "data", "maps");
    ////Path to the game soundtracks => .../resources/sound
    final Path _soundDirectory = Paths.get(_workingDir.toString(), "resources", "sound");

    //Map currently being displayed
    Map currentMap;
    //All available maps
    ArrayList<Map> availableMaps = _loadAvailableMaps();

    Pacman pacman;
    ArrayList<Ghost> ghosts;

    //A count of all the scores on the map
    int mapScore = 0;

    //A list of all the score points on the map
    boolean[][] itemData;

    public World(Pacman p, ArrayList<Ghost> ghosts, String... mapName) {
        this.pacman = p;
        this.ghosts = ghosts;

        //Loads the map
        this.updateMap(mapName);

        //Calls the start function of every ghost individually
        ghosts.forEach(
            ghost -> ghost.start(this)
        );

        //Loads the music
        //TODO: add a SoundController class
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_soundDirectory.toString(), "background.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //changes the current map to a randomly selected one, excludes mapToExclude
    public void changeMapRandomly(Map... mapToExclude){

        Random randomGenerator = new Random();
        ArrayList<Map> mapsToChooseFrom = availableMaps;

        if (mapToExclude.length >= 1){
            for (Map map: mapToExclude){
                mapsToChooseFrom.remove(map);
            }
        }

        updateMap(mapsToChooseFrom.get(randomGenerator.nextInt(mapsToChooseFrom.size())));
    }

    //Loads any given map, if no mapName is given, loads the "default_map"
    public void updateMap(String... mapName) {
        String name = (mapName.length >= 1) ? mapName[0] : "default_map";
        //tries to find the given map, if not found takes the map at the indice 0
        currentMap = availableMaps.stream().filter(map_to_find -> name.equals(map_to_find.name)).findFirst().orElse(availableMaps.get(0));

        pacman.updateCurrentWorld(this);
        this.itemData = currentMap.itemData;
        this.itemData[pacman.getPos_y()][pacman.getPos_x()] = false;     //Am Spawn, spawnt kein Punkt
        countPointsOnWorld();
    }

    //Loads the given map
    public void updateMap(Map map) {
        this.currentMap = map;

        pacman.updateCurrentWorld(this);
        this.itemData = currentMap.itemData;
        this.itemData[pacman.getPos_y()][pacman.getPos_x()] = false;     //Am Spawn, spawnt kein Punkt
        countPointsOnWorld();
    }

    //Loads all maps stored in "_mapsDirectory" and returns an array of them,
    //.../src/models/Map works as a model
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

    public boolean[][] getMapData() {
        return currentMap.mapData;
    }

    public boolean[][] getItemData() {
        return itemData;
    }

    //takes the total score and adds all the available points on the current map
    public void countPointsOnWorld(){
        for (boolean[] booleans : itemData) {
            for (int y = 0; y < itemData[0].length; y++) {
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

    public Map getCurrentMap() {
        return currentMap;
    }
}
