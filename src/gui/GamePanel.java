//Initial file creator: https://github.com/SomeOtherGod
//Other contributors:
// https://github.com/dadope

package src.gui;

import src.models.Ghost;
import src.models.Items;
import src.models.World;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class GamePanel extends JPanel {
    Random randomGenerator = new Random();
    World world;
    Gui gui;

    GameSubPanel gameSubPanel = new GameSubPanel();
    JPanel gameInfoPanel = new JPanel();
    JLabel scoreLabel = new JLabel();

    public GamePanel(World w, Gui g) {
        gui = g;
        world = w;

        gameSubPanel.setSize(g.scale* g.currentWorld.getMapData()[0].length, g.scale* g.currentWorld.getMapData().length);
        gameInfoPanel.setSize(g.scale* g.currentWorld.getMapData()[0].length, 30);
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

    class GameSubPanel extends JPanel{
        @Override
        //zeichnet die Karte
        public void paint(Graphics g) {
            //zeichnet die Map
            for (int x = 0; x < world.getMapData().length; x++) {
                for (int y = 0; y < world.getMapData()[0].length; y++) {
                    if (!world.getMapData()[x][y]) {
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
            for (int x = 0; x < world.getItemData().length; x++) {
                for (int y = 0; y < world.getItemData()[0].length; y++) {
                    Items currentItem = world.getItemData()[x][y];


                    int itemSize = (int) (gui.scale / currentItem.scale);
                    int offset = (gui.scale - itemSize) / 2;

                    g.drawImage(currentItem.image, y * gui.scale + offset, x * gui.scale + offset, itemSize, itemSize, null);
                }
            }

            //draws the pacman
            g.drawImage(gui.currentImage, gui.pacman.getPos_x() * gui.scale, gui.pacman.getPos_y() * gui.scale, gui.scale, gui.scale, null);

            //draws every ghost to its corresponding x and y axes
            for (Ghost ghost : gui.ghosts) {
                g.drawImage(ghost.getGhostImg().get(randomGenerator.nextInt(ghost.getGhostImg().size())), ghost.getPos_x() * gui.scale, ghost.getPos_y() * gui.scale, gui.scale, gui.scale, null);
            }
        }
    }
}
