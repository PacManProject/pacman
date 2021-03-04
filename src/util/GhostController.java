//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/SomeOtherGod

package src.util;

import src.models.Ghost;
import src.models.Pacman.directions;

import java.util.Random;
import java.util.ArrayList;

public class GhostController extends Thread {
    Ghost gh;
    directions lastMovement;
    public boolean paused = false;
    ArrayList<directions> availableDirections;

    private final Random randomGenerator;

    public GhostController(Ghost ghost) {
        gh = ghost;
        randomGenerator = new Random();
    }

    public void run() {
        while (true) {

            // movement delay
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //gets all the directions the pacman can currently go
            availableDirections = gh.getAvailableDirections();

            //if the pacman is on its starting square, randomly chooses a direction to go to
            if (lastMovement == null) {
                lastMovement = availableDirections.get(randomGenerator.nextInt(availableDirections.size()));
            } else {
                //if the pacman has only one direction to go
                if (availableDirections.size() == 1) {
                    lastMovement = availableDirections.get(0);
                }

                //if the pacman is on a t-junction, removes the direction the pacman comes from and randomly chooses
                else if (availableDirections.size() == 2 && !availableDirections.contains(directions.invertDir(lastMovement))) {
                    availableDirections.removeIf(dir -> dir.equals(lastMovement));
                    availableDirections.removeIf(dir -> dir.equals(directions.invertDir(lastMovement)));
                    lastMovement = availableDirections.get(randomGenerator.nextInt(availableDirections.size()));
                }

                //if there are 3 or more available directions, removes the direction the pacman comes from and randomly chooses
                else {
                    availableDirections.removeIf(dir -> dir.equals(directions.invertDir(lastMovement)));
                    lastMovement = availableDirections.get(randomGenerator.nextInt(availableDirections.size()));
                }
            }

            //moves the ghost
            if (!paused) {
                gh.move(lastMovement);
            }
        }
    }

}
