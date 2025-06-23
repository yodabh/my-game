import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class
Player extends JPanel {
    private Image dino, cactus;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private boolean CanCollide;

    public Player (int x, int y, int Width, int Height) {
        this.x = x;
        this.y = y;
        this.width = Width;
        this.height = Height;
        this.CanCollide = true;
        this.LoadImage();
        this.LoadComputerImage(Width, Height);
    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
    }

    public void moveLeft() {
        x --;
    }
    public void jump() {
        y --;
    }
    public void fall() {
        y ++;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public boolean CanCollide() {return CanCollide;}
    public void NoCollision() {this.CanCollide = false;}

    private void LoadImage() {
        ImageIcon HumanImageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/dino.png")));
        Image original = HumanImageIcon.getImage();
        dino = original.getScaledInstance(50 ,70 , Image.SCALE_SMOOTH);

    }
    private void LoadComputerImage(int width, int height) {
        ImageIcon ComputerImageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cactus.png")));
        Image previous = ComputerImageIcon.getImage();
        cactus = previous.getScaledInstance(width , height, Image.SCALE_SMOOTH);
    }

    public Image getDino() {return dino;}
    public Image getCactus() {return cactus;}



}
