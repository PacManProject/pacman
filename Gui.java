import javax.swing.*;
import java.awt.*;

public class Gui extends Thread {
    public synchronized void start() {
        World w = new World(1,3); // xx/yy position von Pacman

        JFrame jf = new JFrame("Pacman"); // name des Fensters
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p00 = new JPanel();
        JPanel p01 = new JPanel();
        JPanel p02 = new JPanel();
        JPanel p03 = new JPanel();
        JPanel p10 = new JPanel();
        JPanel p11 = new JPanel();
        JPanel p12 = new JPanel();
        JPanel p13 = new JPanel();
        JPanel p20 = new JPanel();
        JPanel p21 = new JPanel();
        JPanel p22 = new JPanel();
        JPanel p23 = new JPanel();
        JPanel p30 = new JPanel();
        JPanel p31 = new JPanel();
        JPanel p32 = new JPanel();
        JPanel p33 = new JPanel();
        JPanel[][] jPanels = {{p00,p01,p02,p03},{p10,p11,p12,p13},{p20,p21,p22,p23},{p30,p31,p32,p33}};
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (!w.getXyWorld()[x][y]) {
                    jPanels[x][y].setBackground(Color.blue);
                } else if (w.getX() == y && w.getY() == x){
                    jPanels[x][y].setBackground(Color.yellow);
                } else {
                    jPanels[x][y].setBackground(Color.green);
                }
            }
        }
        jf.setIconImage(new ImageIcon("C:\\tmp/23-237769_pac-man-pixel-art-pac-man.png").getImage()); //img als icon
        jf.setLayout(new GridLayout(4,4));
        jf.add(p00);
        jf.add(p01);
        jf.add(p02);
        jf.add(p03);
        jf.add(p10);
        jf.add(p11);
        jf.add(p12);
        jf.add(p13);
        jf.add(p20);
        jf.add(p21);
        jf.add(p22);
        jf.add(p23);
        jf.add(p30);
        jf.add(p31);
        jf.add(p32);
        jf.add(p33);
        jf.setBounds(0,0,400,400);
        jf.setVisible(true);
    }
}
