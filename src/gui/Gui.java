// Initial file creator: https://github.com/SomeOtherGod
// Other contributors:
// https://github.com/dadope
// https://github.com/Moritz-MT
// https://github.com/LeandroSchadock

package src.gui;

import src.models.Ghost;
import src.models.Items;
import src.models.Pacman;
import src.util.KeyController;
import src.util.MapController;
import src.util.SoundController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Gui extends Thread {
    int frameHeight;
    int frameWith;
    final int scale = 40;
    int frameCounter = 0;

    boolean paused = false;

    final MainMenu welcomePanel;

    final GamePanel gamePanel;

    final SoundController soundController;

    //Game working-directory, should correspond to the project root dir
    final Path workingDir = Paths.get(System.getProperty("user.dir"));

    //Path to the game-icon => .../resources/img/icon.png
    final Path iconPath = Paths.get(workingDir.toString(), "resources", "img", "icon.png");

    //Path to the general sprite, where the game models are stored .../resources/img/General Sprites.png
    final Path spritePath = Paths.get(workingDir.toString(), "resources", "img", "General Sprites.png");

    BufferedImage sprite;
    final BufferedImage pacman0;
    final BufferedImage pacmanUp1;
    final BufferedImage pacmanUp2;
    final BufferedImage pacmanDown1;
    final BufferedImage pacmanDown2;
    final BufferedImage pacmanLeft1;
    final BufferedImage pacmanLeft2;
    final BufferedImage pacmanRight1;
    final BufferedImage pacmanRight2;

    //The pacman model that is currently being displayed
    BufferedImage currentImage;

    //World currently being displayed
    final MapController mapController;

    final JFrame jf = new JFrame("Pacman");// name des Fensters
    final ArrayList<Ghost> ghosts;
    final Pacman pacman;

    public Gui (MapController mapController, Pacman pacman, ArrayList<Ghost> ghosts) {
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.mapController = mapController;
        this.gamePanel = new GamePanel(mapController, this, pacman);
        this.welcomePanel = new MainMenu(this);

        soundController = new SoundController(pacman.settingsController);
        soundController.start();
        this.pacman.soundController = soundController;

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

        updateWelcomeGraphics();
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

        gamePanel.update();

        try {
            wait(150);          //Geschwindigkeit der Animation (Framerate)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        frameCounter = (frameCounter+1)%4;  // frameCounter wird nach 4 frames zurückgesetzt auf 0
    }

    //
    public void updateGameGraphics() {
        try {
            jf.remove(welcomePanel);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        jf.add(gamePanel);

        jf.setVisible(true);
        frameHeight = jf.getInsets().top;
        frameWith = jf.getInsets().right*2;

        jf.setSize(scale* mapController.getMapData()[0].length + frameWith, scale* mapController.getMapData().length + frameHeight + 30);
        jf.setLocationRelativeTo(null);

        jf.revalidate();
        jf.repaint();

        jf.requestFocusInWindow();
    }

    public void updateWelcomeGraphics() {
        jf.setLayout(new GridLayout());
        jf.add(welcomePanel);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(800,500);
        jf.setBackground(Color.DARK_GRAY);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    public void updateSettingsGraphics() {
        soundController.stopEffects();
        new SettingPanel(this, true);
    }

    public synchronized void run() {
        jf.addKeyListener(new KeyController(pacman, this));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setIconImage(new ImageIcon(iconPath.toString()).getImage()); //img als icon
        updateGameGraphics();
        while (true) {
            this.paint();

            //Controls that after all the points are taken, the map is changed
            if (pacman.getScoreboard().currentMapScore == mapController.getMapScore()) {
                changeScene();
            }

            //Controls that the player cant touch any of the ghosts
            ghosts.forEach(
                ghost -> {
                    if (pacman.getPos_x() == ghost.getPos_x() && pacman.getPos_y() == ghost.getPos_y()){
                        if (pacman.getActiveItems().contains(Items.antiGhostPowerUp))
                            ghost.setLocation();
                        else
                            killPacman();
                    }
                }
            );
        }
    }

    //Changes the Map currently being displayed, to any other random map that isn't the current map
    public void changeScene(){
        soundController.stageCleared();
        mapController.changeMapRandomly(mapController.getCurrentMap());
        ghosts.forEach(Ghost::setLocation);
        this.updateGameGraphics();
    }

    public void makeScene(String newName){
        mapController.updateMap(newName);
        pacman.updateCurrentWorld(mapController);
        ghosts.forEach(Ghost::setLocation);
        this.updateGameGraphics();
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setGhosts(int length) {
        for (int i = 0; i < length; i++) {
            ghosts.add(new Ghost());
        }
        ghosts.forEach(ghosts -> ghosts.start(this.mapController));
    }

    public void killPacman(){
        this.paint();
        gamePanel.loseHealth();
        pacman.resetPosition();
    }

    public void pause() {
        pacman.pause();
        ghosts.forEach(Ghost::pause);
    }

    public void unpause() {
        pacman.unpause();
        ghosts.forEach(Ghost::unpause);
    }
}
