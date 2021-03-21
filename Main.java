//Initial file creator: https://github.com/SomeOtherGod
//Other contributors: https://github.com/dadope

import src.gui.Gui;
import src.models.Ghost;
import src.models.Pacman;
import src.util.MapController;

import java.util.ArrayList;

public class Main {

    static final Pacman p = new Pacman();

    static final ArrayList<Ghost> ghosts = new ArrayList<>();

    static final MapController w = new MapController(p, ghosts, "map3");
    static Gui g = new Gui(w, p, ghosts);

    public static void main(String[] args) {
        p.start();
    }
}
