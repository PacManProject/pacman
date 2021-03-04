//Initial file creator: https://github.com/SomeOtherGod
//Other contributors:
// https://github.com/dadope

package src.models;

import src.util.GhostController;
import src.models.Pacman.directions;
import src.util.MapController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Ghost{

    MapController currentMapController;

    //Direction the ghost is currently facing
    directions direction = directions.Left;

    //x axis position of the ghost
    int pos_x;
    //y axis position of the ghost
    int pos_y;

    //Game working-directory, should correspond to the project root dir
    Path workingDir = Paths.get(System.getProperty("user.dir"));
    //Path to the general sprite, where the game models are stored .../resources/img/General Sprites.png
    Path spritePath = Paths.get(workingDir.toString(), "resources", "img", "General Sprites.png");

    BufferedImage sprite;

    ArrayList<BufferedImage> ghostImg;

    //Controls the movement of the ghost
    GhostController ghostController = new GhostController(this);

    public Ghost() {
        ghostImg = selectRandomColor();
    }

    public void start(MapController currentMapController) {
        this.currentMapController = currentMapController;

        this.setLocation();
        this.ghostController.start();

    }

    public int getPos_y() {
        return pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    //Randomly selects a possible location on the map
    public void setLocation() {
        while (true) {
            int i = (int)(Math.random()* currentMapController.currentMap.mapData.length), j = (int)(Math.random()* currentMapController.currentMap.mapData[0].length);
            if (currentMapController.currentMap.mapData[i][j]) {
                this.pos_x = j;
                this.pos_y = i;

                break;
            }
        }
    }

    //Checks if it is possible to move the ghost to "dir", and moves the ghost
    public void move(directions dir){
        switch (dir){
            case Up:
                if(this.pos_y-1 >= 0 && currentMapController.currentMap.mapData[this.pos_y-1% currentMapController.currentMap.mapData.length][this.pos_x]){
                    this.pos_y--;
                    direction = dir;
                }
                break;
            case Down:
                if(this.pos_y+1 < currentMapController.currentMap.mapData.length && currentMapController.currentMap.mapData[this.pos_y+1% currentMapController.currentMap.mapData.length][this.pos_x]){
                    this.pos_y++;
                    direction = dir;
                }
                break;
            case Left:
                if(this.pos_x-1 >= 0 && currentMapController.currentMap.mapData[this.pos_y][this.pos_x-1% currentMapController.currentMap.mapData[0].length]){
                    this.pos_x--;
                    direction = dir;
                }
                break;
            case Right:
                if(this.pos_x+1 < currentMapController.currentMap.mapData[0].length && currentMapController.currentMap.mapData[this.pos_y][this.pos_x+1% currentMapController.currentMap.mapData[0].length]){
                    this.pos_x++;
                    direction = dir;
                }
                break;
            default:
                break;
        }
    }

    //returns a list of all the possible directions the ghost can go to
    public ArrayList<Pacman.directions> getAvailableDirections(){
        ArrayList<directions> availableDirections = new ArrayList<>();

        if(this.pos_x+1 < currentMapController.currentMap.mapData[0].length && currentMapController.currentMap.mapData[this.pos_y][this.pos_x+1% currentMapController.currentMap.mapData[0].length]){
            availableDirections.add(directions.Right);
        }if(this.pos_x-1 >= 0 && currentMapController.currentMap.mapData[this.pos_y][this.pos_x-1% currentMapController.currentMap.mapData[0].length]){
            availableDirections.add(directions.Left);
        }if(this.pos_y-1 >= 0 && currentMapController.currentMap.mapData[this.pos_y-1% currentMapController.currentMap.mapData.length][this.pos_x]){
            availableDirections.add(directions.Up);
        }if(this.pos_y+1 < currentMapController.currentMap.mapData.length && currentMapController.currentMap.mapData[this.pos_y+1% currentMapController.currentMap.mapData.length][this.pos_x]){
            availableDirections.add(directions.Down);
        }

        return availableDirections;
    }

    public ArrayList<BufferedImage> getGhostImg() {
        return ghostImg;
    }

    private ArrayList<BufferedImage> selectRandomColor(){
        ghostImg = new ArrayList<>();
        Random randomGenerator = new Random();

        try {
            sprite = ImageIO.read(new File(spritePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] ghostYaxes = {64, 80, 96, 112};

        int y = ghostYaxes[randomGenerator.nextInt(ghostYaxes.length)];

        for (int x = 0; x <= 112; x+=16){
            ghostImg.add(sprite.getSubimage(x,y, 14, 14));
        }

        return ghostImg;
    }

    public void pause(){
        ghostController.paused = true;
    }

    public void unpause(){
        ghostController.paused = false;
    }
}