//Initial file creator: https://github.com/SomeOtherGod
//Other contributors:
// https://github.com/dadope
// https://github.com/LeandroSchadock

package src.gui;

import src.models.Ghost;
import src.models.Items;
import src.models.Pacman;
import src.util.MapController;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class GamePanel extends JPanel {
    Random randomGenerator = new Random();
    Pacman pacman;
    MapController mapController;
    Gui gui;

    GameSubPanel gameSubPanel = new GameSubPanel();
    Lifedisplay lifedisplay = new Lifedisplay();
    JPanel gameInfoPanel = new JPanel();
    JLabel scoreLabel = new JLabel();

    public GamePanel(MapController w, Gui g, Pacman p) {
        gui = g;
        mapController = w;
        pacman = p;

        gameSubPanel.setSize(g.scale* g.mapController.getMapData()[0].length, g.scale* g.mapController.getMapData().length);
        gameInfoPanel.setSize(g.scale* g.mapController.getMapData()[0].length, 30);
        gameInfoPanel.setLayout(new GridLayout(1,2));
        gameInfoPanel.add(lifedisplay);
        gameInfoPanel.add(scoreLabel);

        this.setLayout(new BorderLayout());
        this.add(gameSubPanel, BorderLayout.CENTER);
        this.add(gameInfoPanel, BorderLayout.SOUTH);
        this.setDoubleBuffered(true);
    }

    //zeichnet die Karte
    public void update() {
        gameSubPanel.repaint();
        scoreLabel.setText(String.valueOf(gui.getPacman().getScoreboard().currentMapScore));
    }

    public void loseHealth(){
        pacman.loseHp();
        lifedisplay.repaint();
    }
    class GameSubPanel extends JPanel{
        @Override
        //zeichnet die Karte
        public void paint(Graphics g) {
            //zeichnet die Map
            for (int x = 0; x < mapController.getMapData().length; x++) {
                for (int y = 0; y < mapController.getMapData()[0].length; y++) {
                    if (!mapController.getMapData()[x][y]) {
                        g.setColor(Color.blue);
                        g.drawRect(gui.scale * y, gui.scale * x, gui.scale * y + gui.scale, gui.scale * x + gui.scale);
                        g.fillRect(gui.scale * y, gui.scale * x, gui.scale * y + gui.scale, gui.scale * x + gui.scale);
                    } else {
                        g.setColor(Color.black);
                        g.drawRect(gui.scale * y, gui.scale * x, gui.scale * y + gui.scale, gui.scale * x + gui.scale);
                        g.fillRect(gui.scale * y, gui.scale * x, gui.scale * y + gui.scale, gui.scale * x + gui.scale);
                    }
                }
            }

            //TODO: draw the item image
            for (int x = 0; x < mapController.getItemData().length; x++) {
                for (int y = 0; y < mapController.getItemData()[0].length; y++) {
                    Items currentItem = mapController.getItemData()[x][y];


                    int itemSize = (int) (gui.scale / currentItem.scale);
                    int offset = (gui.scale - itemSize) / 2;

                    g.drawImage(currentItem.image, y * gui.scale + offset, x * gui.scale + offset, itemSize, itemSize, null);
                }
            }

            //draws the pacman
            g.drawImage(gui.currentImage, gui.pacman.getPos_x() * gui.scale, gui.pacman.getPos_y() * gui.scale, gui.scale, gui.scale, null);

            //draws every ghost to its corresponding x and y axes
            for (Ghost ghost : gui.ghosts) {
                if (pacman.getActiveItems().contains(Items.cherry))
                    g.drawImage(ghost.getChasedImg().get(randomGenerator.nextInt(ghost.getChasedImg().size())), ghost.getPos_x() * gui.scale, ghost.getPos_y() * gui.scale, gui.scale, gui.scale, null);
                else
                    g.drawImage(ghost.getGhostImg().get(randomGenerator.nextInt(ghost.getGhostImg().size())), ghost.getPos_x() * gui.scale, ghost.getPos_y() * gui.scale, gui.scale, gui.scale, null);
            }
        }
    }

    private class Lifedisplay extends JPanel {
        Color defaultcolor;
        int lastHp;

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            defaultcolor = this.getBackground();

            if (pacman.getHp() <= 0){
                gui.soundController.pacmanDied();
                gui.stop();
                pacman.die();
                System.exit(0);
            }

            if (pacman.getHp() < lastHp) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gui.soundController.pacmanLosesHealth();
                gui.pause();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gui.unpause();
            }

            int x_axis = 0;
            g.setColor(defaultcolor);

            for (int x = 0; x < pacman.getHp();x++ ) {
                g.drawImage(gui.pacmanLeft1, x_axis, 0, 15, 15, null);
                x_axis +=16;
            }

            lastHp = pacman.getHp();
        }
    }
}
