import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {
    private MainScene mainScene;
    private Player human;
    private boolean CanJump = true;
    public Movement (MainScene mainScene, Player human) {
        this.mainScene = mainScene;
        this.human = human;
    }
    public void keyTyped(KeyEvent k) {
        if (k.getKeyChar() == ' ' && CanJump) {
            CanJump = false;
            new Thread(() -> {
                for (int i = 0; i < 175; i++) {
                    this.human.jump();
                    this.mainScene.repaint();
                    try {
                        Thread.sleep((mainScene.getGameSpeed()) - 15);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (int i = 0; i < 175; i++) {
                    this.human.fall();
                    this.mainScene.repaint();
                    try {
                        Thread.sleep((mainScene.getGameSpeed()) - 15);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                this.CanJump = true;

            }).start();


        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}