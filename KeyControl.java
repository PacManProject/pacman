import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyControl implements KeyListener {

    Pacman pacman;

    public KeyControl(Pacman p) {
        pacman = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO: implement fucntion calls to move the character
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> pacman.moveLeft();
            case KeyEvent.VK_RIGHT -> pacman.moveRight();
            case KeyEvent.VK_UP -> pacman.moveUp();
            case KeyEvent.VK_DOWN -> pacman.moveDown();
            default -> System.out.println("Pressed key: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}