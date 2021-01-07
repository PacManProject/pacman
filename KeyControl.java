import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyControl implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO: implement function calls to move the character
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> System.out.println("Pressed left");
            case KeyEvent.VK_RIGHT -> System.out.println("Pressed right");
            case KeyEvent.VK_UP -> System.out.println("Pressed up");
            case KeyEvent.VK_DOWN -> System.out.println("Pressed down");
            default -> System.out.println("Pressed key: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}