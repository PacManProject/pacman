//Initial file creator: https://github.com/SomeOtherGod
//Other contributors:
// https://github.com/dadope

package src.models;

import src.util.GhostController;
import src.models.Pacman.directions;

import java.util.ArrayList;

public class Ghost{
    directions direction = directions.Left;

    World currentWorld;
    
    int pos_x;
    int pos_y;

    GhostController ghostController = new GhostController(this);

    public void start(World currentWorld) {
        this.currentWorld = currentWorld;

        this.setLocation();
        this.ghostController.start();
    }

    public int getPos_y() {
        return pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setLocation() {
        while (true) {
            int i = (int)(Math.random()* currentWorld.currentMap.mapData.length), j = (int)(Math.random()* currentWorld.currentMap.mapData[0].length);
            if (currentWorld.currentMap.mapData[i][j]) {
                this.pos_x = j;
                this.pos_y = i;

                break;
            }
        }
    }

    public void move(directions dir){
        switch (dir){
            case Up:
                if(this.pos_y-1 >= 0 && currentWorld.currentMap.mapData[this.pos_y-1% currentWorld.currentMap.mapData.length][this.pos_x]){
                    this.pos_y--;
                    direction = dir;
                }
                break;
            case Down:
                if(this.pos_y+1 < currentWorld.currentMap.mapData.length && currentWorld.currentMap.mapData[this.pos_y+1% currentWorld.currentMap.mapData.length][this.pos_x]){
                    this.pos_y++;
                    direction = dir;
                }
                break;
            case Left:
                if(this.pos_x-1 >= 0 && currentWorld.currentMap.mapData[this.pos_y][this.pos_x-1% currentWorld.currentMap.mapData[0].length]){
                    this.pos_x--;
                    direction = dir;
                }
                break;
            case Right:
                if(this.pos_x+1 < currentWorld.currentMap.mapData[0].length && currentWorld.currentMap.mapData[this.pos_y][this.pos_x+1% currentWorld.currentMap.mapData[0].length]){
                    this.pos_x++;
                    direction = dir;
                }
                break;
            default:
                break;
        }
    }

    public ArrayList<Pacman.directions> getAvailableDirections(){
        ArrayList<directions> availableDirections = new ArrayList<>();

        if(this.pos_x+1 < currentWorld.currentMap.mapData[0].length && currentWorld.currentMap.mapData[this.pos_y][this.pos_x+1% currentWorld.currentMap.mapData[0].length]){
            availableDirections.add(directions.Right);
        }if(this.pos_x-1 >= 0 && currentWorld.currentMap.mapData[this.pos_y][this.pos_x-1% currentWorld.currentMap.mapData[0].length]){
            availableDirections.add(directions.Left);
        }if(this.pos_y-1 >= 0 && currentWorld.currentMap.mapData[this.pos_y-1% currentWorld.currentMap.mapData.length][this.pos_x]){
            availableDirections.add(directions.Up);
        }if(this.pos_y+1 < currentWorld.currentMap.mapData.length && currentWorld.currentMap.mapData[this.pos_y+1% currentWorld.currentMap.mapData.length][this.pos_x]){
            availableDirections.add(directions.Down);
        }

        return availableDirections;
    }
}