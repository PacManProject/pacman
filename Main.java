//Initial file creator: https://github.com/SomeOtherGod
//Other contributors: https://github.com/dadope

import src.gui.Gui;
import src.models.Ghost;
import src.models.Pacman;
import src.models.World;

public class Main {
    static World w = new World("map2");
    static Pacman p = new Pacman(w);
    static Ghost g1 = new Ghost(w);
    static Gui g = new Gui(w, p, g1);

    public static void main(String[] args) {
        p.start();
        g.start();
    }
}
