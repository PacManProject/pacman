//Initial file creator: https://github.com/dadope

package src.util;

import src.models.Ghost;
import src.models.Pacman.directions;

import java.util.Random;
import java.util.ArrayList;

public class GhostController extends Thread {
    Ghost gh;
    directions lastMovement;
    ArrayList<directions> availableDirections;


    private final Random randomGenerator;

    public GhostController(Ghost ghost) {
        gh = ghost;
        randomGenerator = new Random();
    }

    public void run() {
        while (true) {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            availableDirections = gh.getAvailableDirections();

            if (lastMovement == null){
                lastMovement = availableDirections.get(randomGenerator.nextInt(availableDirections.size()));
            }
            else {
                if(availableDirections.size() == 1){
                    lastMovement = availableDirections.get(0);
                }
                if (availableDirections.size() == 2 && !availableDirections.contains(directions.switch_dir(lastMovement))){
                    availableDirections.removeIf(dir -> dir.equals(lastMovement));
                    availableDirections.removeIf(dir -> dir.equals(directions.switch_dir(lastMovement)));
                    lastMovement = availableDirections.get(randomGenerator.nextInt(availableDirections.size()));
                }
                else{
                    availableDirections.removeIf(dir -> dir.equals(directions.switch_dir(lastMovement)));
                    lastMovement = availableDirections.get(randomGenerator.nextInt(availableDirections.size()));
                }
            }
            gh.move(lastMovement);
        }
    }
}
