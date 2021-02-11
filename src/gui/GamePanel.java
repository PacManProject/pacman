package src.gui;

import src.models.Ghost;
import src.models.World;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class GamePanel extends JPanel {
    Random randomGenerator = new Random();
    World w;
    Gui gui;

    GameSubPanel gameSubPanel = new GameSubPanel();
    JPanel gameInfoPanel = new JPanel();
    JLabel scoreLabel = new JLabel();

    public GamePanel(World world, Gui g) {
        w = world;
        gui = g;

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
            for (int x = 0; x < w.getMapData().length; x++) {
                for (int y = 0; y < w.getMapData()[0].length; y++) {
                    if (!w.getMapData()[x][y]) {
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

            //zeichnet die Punkte
            for (int x = 0; x < w.getItemData().length; x++) {
                for (int y = 0; y < w.getItemData()[0].length; y++) {
                    if (w.getItemData()[x][y]) {
                        g.setColor(Color.yellow);
                        int offset = gui.scale / 4;

                        g.fillOval(y * gui.scale + (int) (offset * 1.5), x * gui.scale + (int) (offset * 1.5), offset, offset);
                    }
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
