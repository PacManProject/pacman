//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/SomeOtherGod

package src.util;

import src.gui.Gui;
import src.models.Pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener {

    final Pacman pacman;
    final Gui gui;

    public KeyController(Pacman p, Gui g) {
        pacman = p;
        gui = g;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                pacman.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                pacman.moveRight();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                pacman.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                pacman.moveDown();
                break;
            case KeyEvent.VK_ESCAPE:
                gui.updateSettingsGraphics();
                gui.pause();
                break;
            default :
                System.out.println("Pressed key: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}