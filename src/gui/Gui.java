//Initial file creator: https://github.com/SomeOtherGod
//Other contributors:
// https://github.com/dadope
// https://github.com/Moritz-MT

package src.gui;

import src.models.Ghost;
import src.models.World;
import src.models.Pacman;
import src.util.KeyControl;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.imageio.ImageIO;

public class Gui extends Thread {
    int frameSize = 30;
    int scale = 40;
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

    BufferedImage ghost1img;

    World currentWorld;

    JFrame jf = new JFrame("Pacman");// name des Fensters
    ArrayList<Ghost> ghosts;
    Pacman pacman;
    GuiPanel guiPanel;

    public Gui (World wor, Pacman pac, ArrayList<Ghost> ghosts) {

        this.pacman = pac;
        this.ghosts = ghosts;
        this.currentWorld = wor;
        this.guiPanel = new GuiPanel(currentWorld);

        try {
            sprite = ImageIO.read(new File(spritePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pacman0 = sprite.getSubimage(32, 0, 13, 13);      //nimmt aus der Sprite Ressource die gebrauchten Sprites
        pacmanUp1 = sprite.getSubimage(16, 32, 13, 13);
        pacmanUp2 = sprite.getSubimage(0, 32, 13, 13);
        pacmanDown1 = sprite.getSubimage(16, 48, 13, 13);
        pacmanDown2 = sprite.getSubimage(0, 48, 13, 13);
        pacmanLeft1 = sprite.getSubimage(16, 16, 13, 13);
        pacmanLeft2 = sprite.getSubimage(0, 16, 13, 13);
        pacmanRight1 = sprite.getSubimage(16, 0, 13, 13);
        pacmanRight2 = sprite.getSubimage(0, 0, 13, 13);
        currentImage = pacman0;

        ghost1img = sprite.getSubimage(0, 64, 14, 14);
    }

    public void paint() {
        switch (pacman.getDirection()) {     //Pacmans Animation wird festgelegt
            case Up:
                switch (frameCounter) {
                    case 0:
                        currentImage = pacman0;
                        break;

                    case 1:
                    case 3:
                        currentImage = pacmanUp1;
                        break;
                    case 2:
                        currentImage = pacmanUp2;
                        break;
                }
                break;
            case Down:
                switch (frameCounter){
                    case 0:
                        currentImage = pacman0;
                        break;

                    case 1:
                    case 3:
                        currentImage = pacmanDown1;
                        break;
                    case 2:
                        currentImage = pacmanDown2;
                        break;
                }
                break;
            case Left:
                switch (frameCounter){
                    case 0:
                        currentImage = pacman0;
                        break;

                    case 1:
                    case 3:
                        currentImage = pacmanLeft1;
                        break;
                    case 2:
                        currentImage = pacmanLeft2;
                        break;
                }
                break;
            case Right:
                switch (frameCounter){
                    case 0:
                        currentImage = pacman0;
                        break;

                    case 1:
                    case 3:
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
            wait(150);          //Geschwindigkeit der Animation (Framerate)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frameCounter = (frameCounter+1)%4;  // frameCounter wird nach 4 frames zurückgesetzt auf 0

    }

    private void _updateGraphics() {
        jf.add(guiPanel);
        guiPanel.setDoubleBuffered(true);

        jf.setResizable(false);
        jf.setSize(scale* currentWorld.getMapData()[0].length, scale* currentWorld.getMapData().length + frameSize);
        jf.setLocationRelativeTo(null);
        jf.addKeyListener(new KeyControl(pacman));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setIconImage(new ImageIcon(iconPath.toString()).getImage()); //img als icon

        jf.setVisible(true);
    }

    public synchronized void run() {
        _updateGraphics();
        while (true) {
            this.paint();
            if (pacman.getScore() == currentWorld.getMapScore()) {
                changeScene();
            }

            ghosts.forEach(
                ghost -> {
                    if (pacman.getPos_x() == ghost.getPos_x() && pacman.getPos_y() == ghost.getPos_y()){
                        System.out.println("tot");
                        this.paint();
                        //System.exit(0); //TODO replace with p.die() & implement health system
                    }
                }
            );
        }
    }

    public void changeScene(){
        currentWorld.changeMapRandomly(currentWorld.getCurrentMap());
        ghosts.forEach(Ghost::setLocation);
        this._updateGraphics();
    }


    class GuiPanel extends JPanel{
        World w;

        public GuiPanel(World world) {
            w = world;
        }

        @Override
        public void paint(Graphics g) {         //zeichnet die Karte

            for (int x = 0; x < w.getMapData().length; x++) {   //zeichnet die Map
                for (int y = 0; y < w.getMapData()[0].length; y++) {
                    if (!w.getMapData()[x][y]) {
                        g.setColor(Color.blue);
                        g.drawRect(scale*y, scale*x,scale*y + scale, scale*x+scale);
                        g.fillRect(scale*y, scale*x,scale*y + scale, scale*x+scale);
                    } else {
                        g.setColor(Color.black);
                        g.drawRect(scale*y, scale*x,scale*y + scale, scale*x+scale);
                        g.fillRect(scale*y, scale*x,scale*y + scale, scale*x+scale);
                    }
                }
            }
            for (int x = 0; x < w.getItemData().length; x++) {   //zeichnet die Punkte
                for (int y = 0; y < w.getItemData()[0].length; y++) {
                    if (w.getItemData()[x][y]){
                        g.setColor(Color.yellow);
                        int offset = scale/4;

                        g.fillOval(y*scale + (int)(offset*1.5), x*scale + (int)(offset*1.5), offset, offset);
                    }
                }
            }
            g.drawImage(currentImage, pacman.getPos_x()*scale, pacman.getPos_y()*scale, scale, scale, null);
            ghosts.forEach(
                    ghost -> {
                        g.drawImage(ghost1img, ghost.getPos_x()*scale, ghost.getPos_y()*scale, scale, scale, null);
                    }
            );
        }
    }
}
