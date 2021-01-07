import src.gui.Gui;
import src.models.Pacman;
import src.models.World;

public class Main {
    static World w = new World();
    static Pacman p = new Pacman(w);
    static Gui g = new Gui(w, p);

    public static void main(String[] args) {
        g.start();
    }
}
