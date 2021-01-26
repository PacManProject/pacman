//Initial file creator: https://github.com/SomeOtherGod
//Other contributors:
// https://github.com/dadope

package src.models;

import src.util.GhostController;
import src.models.Pacman.directions;

import java.util.ArrayList;

public class Ghost{
    directions direction = directions.Left;

    World world1;

    GhostController ki = new GhostController(this);

    public Ghost(World w) {
        world1 = w;
        this.setLocation();
        ki.start();
    }

    public void setLocation() {
        while (true) {
            int i = (int)(Math.random()*world1.currentMap.mapData.length), j = (int)(Math.random()*world1.currentMap.mapData[0].length);
            if (world1.currentMap.mapData[i][j]) {
                world1.xg1 = j;
                world1.yg1 = i;
                break;
            }
        }
    }

    public void move(directions dir){
        switch (dir){
            case Up:
                if(world1.yg1-1 >= 0 && world1.currentMap.mapData[world1.yg1-1%world1.currentMap.mapData.length][world1.xg1]){
                    world1.yg1--;
                    direction = dir;
                }
                break;
            case Down:
                if(world1.yg1+1 < world1.currentMap.mapData.length && world1.currentMap.mapData[world1.yg1+1%world1.currentMap.mapData.length][world1.xg1]){
                    world1.yg1++;
                    direction = dir;
                }
                break;
            case Left:
                if(world1.xg1-1 >= 0 && world1.currentMap.mapData[world1.yg1][world1.xg1-1%world1.currentMap.mapData[0].length]){
                    world1.xg1--;
                    direction = dir;
                }
                break;
            case Right:
                if(world1.xg1+1 < world1.currentMap.mapData[0].length && world1.currentMap.mapData[world1.yg1][world1.xg1+1%world1.currentMap.mapData[0].length]){
                    world1.xg1++;
                    direction = dir;
                }
                break;
            default:
                break;
        }
    }

    public ArrayList<Pacman.directions> getAvailableDirections(){
        ArrayList<directions> availableDirections = new ArrayList<>();

        if(world1.xg1+1 < world1.currentMap.mapData[0].length && world1.currentMap.mapData[world1.yg1][world1.xg1+1%world1.currentMap.mapData[0].length]){
            availableDirections.add(directions.Right);
        }if(world1.xg1-1 >= 0 && world1.currentMap.mapData[world1.yg1][world1.xg1-1%world1.currentMap.mapData[0].length]){
            availableDirections.add(directions.Left);
        }if(world1.yg1-1 >= 0 && world1.currentMap.mapData[world1.yg1-1%world1.currentMap.mapData.length][world1.xg1]){
            availableDirections.add(directions.Up);
        }if(world1.yg1+1 < world1.currentMap.mapData.length && world1.currentMap.mapData[world1.yg1+1%world1.currentMap.mapData.length][world1.xg1]){
            availableDirections.add(directions.Down);
        }

        return availableDirections;
    }
}