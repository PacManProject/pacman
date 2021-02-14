//Initial file creator: https://github.com/SomeOtherGod
//Other contributors: https://github.com/dadope

import src.gui.Gui;
import src.models.Ghost;
import src.models.Pacman;
import src.models.World;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //TODO: initialize all values in GUI

    static Pacman p = new Pacman();
    static Ghost g1 = new Ghost();
    static Ghost g2 = new Ghost();
    static Ghost g3 = new Ghost();
    static Ghost g4 = new Ghost();

    static ArrayList<Ghost> ghosts = new ArrayList<Ghost>(List.of(
            g1, g2, g3 ,g4
    ));

    static World w = new World(p, ghosts, "map3");
    static Gui g = new Gui(w, p, ghosts);

    public static void main(String[] args) {
        p.start();
        g.start();
    }
}
