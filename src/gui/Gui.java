// Initial file creator: https://github.com/SomeOtherGod
// Other contributors:
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
import java.util.Random;

import javax.swing.*;
import javax.imageio.ImageIO;

public class Gui extends Thread {
    int frameSize = 60;
    int scale = 40;
    int frameCounter = 0;

    JPanel welcomePanel;

    JPanel homePanel;

    //components for the gamePanel
    JPanel gamePanel = new JPanel();
    JPanel gameInfoPanel = new JPanel();
    GamePanel gameSubPanel;
    JLabel scoreLabel = new JLabel();

    JPanel deathPanel;

    Random randomGenerator = new Random();

    //Game working-directory, should correspond to the project root dir
    Path workingDir = Paths.get(System.getProperty("user.dir"));
    //Path to the game-icon => .../resources/img/icon.png
    Path iconPath = Paths.get(workingDir.toString(), "resources", "img", "icon.png");
    //Path to the general sprite, where the game models are stored .../resources/img/General Sprites.png
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

    //The pacman model that is currently being displayed
    BufferedImage currentImage;

    //World currently being displayed
    World currentWorld;

    JFrame jf = new JFrame("Pacman");// name des Fensters
    ArrayList<Ghost> ghosts;
    Pacman pacman;

    public Gui (World wor, Pacman pac, ArrayList<Ghost> ghosts) {
        this.pacman = pac;
        this.ghosts = ghosts;
        this.currentWorld = wor;
        this.gameSubPanel = new GamePanel(currentWorld);

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

        scoreLabel.setText(String.valueOf(pacman.getScoreboard().currentMapScore));

        try {
            wait(150);          //Geschwindigkeit der Animation (Framerate)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        frameCounter = (frameCounter+1)%4;  // frameCounter wird nach 4 frames zurückgesetzt auf 0
    }

    //
    private void _updateGraphics() {
        gameSubPanel.setSize(scale* currentWorld.getMapData()[0].length, scale* currentWorld.getMapData().length);
        gameInfoPanel.setSize(scale* currentWorld.getMapData()[0].length, 30);
        gameInfoPanel.add(scoreLabel);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameSubPanel, BorderLayout.CENTER);
        gamePanel.add(gameInfoPanel, BorderLayout.SOUTH);
        gamePanel.setDoubleBuffered(true);

        jf.add(gamePanel);

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

            //Controls that after all the points are taken, the map is changed
            if (pacman.getScoreboard().currentMapScore == currentWorld.getMapScore()) {
                changeScene();
            }

            //Controls that the player cant touch any of the ghosts
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

    //Changes the Map currently being displayed, to any other random map that isn't the current map
    //TODO: add an optional parameter to change the map to a specific other map
    public void changeScene(){
        currentWorld.changeMapRandomly(currentWorld.getCurrentMap());
        ghosts.forEach(Ghost::setLocation);
        this._updateGraphics();
    }

    public Pacman getPacman() {
        return pacman;
    }

    class GamePanel extends JPanel{
        World w;

        public GamePanel(World world) {
            w = world;
        }

        @Override
        //zeichnet die Karte
        public void paint(Graphics g) {
            //zeichnet die Map
            for (int x = 0; x < w.getMapData().length; x++) {
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

            //zeichnet die Punkte
            for (int x = 0; x < w.getItemData().length; x++) {
                for (int y = 0; y < w.getItemData()[0].length; y++) {
                    if (w.getItemData()[x][y]){
                        g.setColor(Color.yellow);
                        int offset = scale/4;

                        g.fillOval(y*scale + (int)(offset*1.5), x*scale + (int)(offset*1.5), offset, offset);
                    }
                }
            }

            //draws the pacman
            g.drawImage(currentImage, pacman.getPos_x()*scale, pacman.getPos_y()*scale, scale, scale, null);

            //draws every ghost to its corresponding x and y axes
            for (Ghost ghost : ghosts) {
                g.drawImage(ghost.getGhostImg().get(randomGenerator.nextInt(ghost.getGhostImg().size())), ghost.getPos_x() * scale, ghost.getPos_y() * scale, scale, scale, null);
            }
        }
    }
}
