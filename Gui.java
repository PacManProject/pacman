import javax.swing.*;
import java.awt.*;

public class Gui extends Thread {
    World w = new World(); // xx/yy position von Pacman

    GuiFrame jf;

    public Gui (World wor) {
        w = wor;
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

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(0,0, 800, 800);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setIconImage(new ImageIcon("C:\\tmp/23-237769_pac-man-pixel-art-pac-man.png").getImage()); //img als icon
        jf.setLayout(new GridLayout(4,4));
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
