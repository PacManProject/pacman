package src.util;

import src.models.Pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyControl implements KeyListener {

    Pacman pacman;

    public KeyControl(Pacman p) {
        pacman = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                pacman.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                pacman.moveRight();
                break;
            case KeyEvent.VK_UP:
                pacman.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                pacman.moveDown();
                break;
            default :
                System.out.println("Pressed key: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}