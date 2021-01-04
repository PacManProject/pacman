import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyControl extends KeyListener{
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO: implement fucntion calls to move the character
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                System.out.println("Pressed left");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Pressed right");
                break;
            case KeyEvent.VK_UP:
                System.out.println("Pressed up");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Pressed down");
                break;
            default:
                System.out.println("Pressed key: " + e.getKeyCode());
        }
    }
}