package src.gui;

import src.models.Pacman;
import src.models.World;
import src.util.KeyControl;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gui extends Thread {
    Path workingDir = Paths.get(System.getProperty("user.dir"));
    Path iconPath = Paths.get(workingDir.toString(), "resources", "img", "icon.png");

    World w = new World(); // xx/yy position von src.models.Pacman

    GuiFrame jf;
    Pacman p;

    public Gui (World wor, Pacman pac) {
        w = wor;
        p = pac;
        jf = new GuiFrame(w,"Pacman"); // name des Fensters
    }

    public synchronized void paint() {
        jf.repaint();
        try {
            wait(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void run() {

        jf.setResizable(false);
        jf.setSize(100*w.getXyWorld()[0].length, 100*w.getXyWorld().length);
        jf.setLocationRelativeTo(null);
        jf.addKeyListener(new KeyControl(p));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setIconImage(new ImageIcon(iconPath.toString()).getImage()); //img als

        jf.setVisible(true);

        while (true) {
            this.paint();
        }
    }

    class GuiFrame extends JFrame{
        World w;

        public GuiFrame(World world, String title) {
            super(title);
            w = world;
        }

        @Override
        public void paint(Graphics g) {
            for (int x = 0; x < w.getXyWorld().length; x++) {
                for (int y = 0; y < w.getXyWorld()[0].length; y++) {
                    if (!w.getXyWorld()[x][y]) {
                        g.setColor(Color.blue);
                    } else if (w.getX() == y && w.getY() == x){
                        g.setColor(Color.yellow);
                    } else {
                        g.setColor(Color.black);
                    }
                    g.drawRect(100*y, 100*x,100*y+100, 100*x+100);
                    g.fillRect(100*y, 100*x,100*y+100, 100*x+100);
                }
            }
        }
    }
}
