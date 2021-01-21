//Initial file creator: https://github.com/SomeOtherGod

package src.util;

import src.models.Ghost;

public class GhostKi extends Thread {
    Ghost gh;

    public GhostKi(Ghost ghost) {
        gh = ghost;
    }

    public void run() {
        while (true) {
            //random movement
            int i = (int) (Math.random() * 4);
            switch (i) {
                case 0:
                    gh.moveUp();
                    break;
                case 1:
                    gh.moveDown();
                    break;
                case 2:
                    gh.moveLeft();
                    break;
                case 3:
                    gh.moveRight();
                    break;
                default:
                    System.out.println("leandro suk cok");
                    break;
            }
        }
    }
}
