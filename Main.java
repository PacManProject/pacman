//Initial file creator: https://github.com/SomeOtherGod
//Other contributors: https://github.com/dadope

import src.gui.Gui;
import src.gui.Map_Designer;
import src.models.Ghost;
import src.models.Pacman;
import src.util.MapController;
import src.util.SoundController;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //TODO: initialize all values in GUI

    static Pacman p = new Pacman();

    static ArrayList<Ghost> ghosts = new ArrayList<Ghost>();

    static MapController w = new MapController(p, ghosts, "map3");
    static Gui g = new Gui(w, p, ghosts);

    public static void main(String[] args) {
        p.start();
    }
}
