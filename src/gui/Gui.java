package src.gui;

import src.models.Pacman;
import src.models.World;
import src.util.KeyControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gui extends Thread {
    int frameSize = 30;
    int scale = 100;
    int frameCounter = 0;

    Path workingDir = Paths.get(System.getProperty("user.dir"));
    Path iconPath = Paths.get(workingDir.toString(), "resources", "img", "icon.png");
    Path spritePath = Paths.get(workingDir.toString(), "resources", "img", "General Sprites.png");

    BufferedImage sprite;
    BufferedImage pacman0;
    BufferedImage pacmanUp1;
    BufferedImage pacmanUp2;
    BufferedImage pacmanDown1;
    BufferedImage pacmanDown2;
    BufferedImage pacmanLeft1;
    BufferedImage pacmanLeft2;
    BufferedImage pacmanRight1;
    BufferedImage pacmanRight2;

    BufferedImage currentImage;

    World w;

    JFrame jf = new JFrame("Pacman");// name des Fensters
    Pacman p;
    GuiPanel gf;

    public Gui (World wor, Pacman pac) {
        w = wor;
        p = pac;
        gf = new GuiPanel(w);
        try {
            sprite = ImageIO.read(new File(spritePath.toString()));
        } catch (IOException ignored) {
        }
        pacman0 = sprite.getSubimage(32, 0, 13, 13);
        pacmanUp1 = sprite.getSubimage(16, 32, 13, 13);
        pacmanUp2 = sprite.getSubimage(0, 32, 13, 13);
        pacmanDown1 = sprite.getSubimage(16, 48, 13, 13);
        pacmanDown2 = sprite.getSubimage(0, 48, 13, 13);
        pacmanLeft1 = sprite.getSubimage(16, 16, 13, 13);
        pacmanLeft2 = sprite.getSubimage(0, 16, 13, 13);
        pacmanRight1 = sprite.getSubimage(16, 0, 13, 13);
        pacmanRight2 = sprite.getSubimage(0, 0, 13, 13);
        currentImage = pacman0;
    }

    public synchronized void paint() {
        switch (p.getDirection()) {
            case "u":
                switch (frameCounter) {
                    case 0:
                        currentImage = pacman0;
                        break;
                    case 1:

                    case 4:
                        currentImage = pacmanUp1;
                        break;
                    case 2:
                        currentImage = pacmanUp2;
                        break;
                }
                break;
            case "d":
                switch (frameCounter){
                    case 0:
                        currentImage = pacman0;
                        break;
                    case 1:

                    case 4:
                        currentImage = pacmanDown1;
                        break;
                    case 2:
                        currentImage = pacmanDown2;
                        break;
                }
                break;
            case "l":
                switch (frameCounter){
                    case 0:
                        currentImage = pacman0;
                        break;
                    case 1:

                    case 4:
                        currentImage = pacmanLeft1;
                        break;
                    case 2:
                        currentImage = pacmanLeft2;
                        break;
                }
                break;
            case "r":
                switch (frameCounter){
                    case 0:
                        currentImage = pacman0;
                        break;
                    case 1:

                    case 4:
                        currentImage = pacmanRight1;
                        break;
                    case 2:
                        currentImage = pacmanRight2;
                        break;
                }
                break;
            default :
                currentImage = pacman0;
                break;
        }
        jf.repaint();
        try {
            wait(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frameCounter = (frameCounter+1)%5;

    }
    public synchronized void run() {

        jf.add(gf);
        gf.setDoubleBuffered(true);

        jf.setResizable(false);
        jf.setSize(scale*w.getXyWorld()[0].length, scale*w.getXyWorld().length + frameSize);
        jf.setLocationRelativeTo(null);
        jf.addKeyListener(new KeyControl(p));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setIconImage(new ImageIcon(iconPath.toString()).getImage()); //img als

        jf.setVisible(true);

        while (true) {
            this.paint();
        }
    }


    class GuiPanel extends JPanel{
        World w;

        public GuiPanel(World world) {
            w = world;
        }

        @Override
        public void paint(Graphics g) {

            for (int x = 0; x < w.getXyWorld().length; x++) {
                for (int y = 0; y < w.getXyWorld()[0].length; y++) {

                    if (!w.getXyWorld()[x][y]) {
                        g.setColor(Color.blue);
                        g.drawRect(scale*y, scale*x,scale*y + scale, scale*x+scale);
                        g.fillRect(scale*y, scale*x,scale*y + scale, scale*x+scale);
                    } else if (w.getX() == y && w.getY() == x){
                        g.drawImage(currentImage, y*scale, x*scale, scale, scale, null);
                    } else {
                        g.setColor(Color.black);
                        g.drawRect(scale*y, scale*x,scale*y + scale, scale*x+scale);
                        g.fillRect(scale*y, scale*x,scale*y + scale, scale*x+scale);
                    }
                }
            }
        }
    }
}
