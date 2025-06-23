import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainScene extends JPanel {
    Image Heart;
    Random rand = new Random();
    private final Player Human;
    ArrayList<Player> computer;
    public boolean collision;
    private int GameSpeed;
    private boolean GameOver;
    private int Score;
    private int life;


    public MainScene(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        this.setBackground(Color.cyan);
        this.LoadHeart();
        this.life = 3;
        this.ScoreScale();
        this.GameSpeed = 20;
        this.Score = 0;
        this.GameOver = false;
        this.Human = new Player(50, 400, 50, 70);
        this.computer = new ArrayList<>();
        this.AddComputer();
        this.addKeyListener(new Movement(this,this.Human));

        Sounds.playLoop("/Resource/sounds/background.wav");
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.Human.getDino() != null) {
            g.drawImage(this.Human.getDino(), this.Human.getX(), this.Human.getY(), this);
        }
        for (int i = 0; i < this.computer.size(); i++) {
            g.drawImage(computer.get(i).getCactus(),this.computer.get(i).getX() ,this.computer.get(i).getY() , this);
        }

        g.drawImage(this.Heart,25 ,25 , this);

        g.setFont(new Font("arial", Font.BOLD, 45));
        g.drawString(String.valueOf(this.life), 85, 60);

        g.drawString("score: " + this.Score , 25, 100);
    }
    private boolean CheckCollision (Player enemy) {
        Rectangle computerRectangle = new Rectangle(
                enemy.getX(),
                enemy.getY(),
                enemy.getWidth(),
                enemy.getHeight()
        );
        Rectangle humanRectangle = new Rectangle(
                this.Human.getX(),
                this.Human.getY(),
                this.Human.getWidth(),
                this.Human.getHeight()
        );
        if (computerRectangle.intersects(humanRectangle)) {
            return true;
        }
        else {return false;}
    }
    private void NpcMovement(Player enemy) {
        new Thread(() -> {
            while (enemy.getX() + enemy.getWidth() >= 0) {
                if (CheckCollision(enemy) && enemy.CanCollide()) {
                    enemy.NoCollision();
                    LifeLoss();
                    repaint();
                }
                if (life == 0 || this.GameOver) {
                    this.GameOver = true;
                    Sounds.playSound("/Resource/sounds/gameover.wav");
                    if (life == 0 || this.GameOver) {
                        this.GameOver = true;
                        SwingUtilities.invokeLater(() -> {
                            JFrame endFrame = new JFrame("Game Over");
                            endFrame.setSize(800, 600);
                            endFrame.setLocationRelativeTo(null);
                            endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            endFrame.setContentPane(new EndWindow());
                            endFrame.setVisible(true);
                        });
                    }

                    break;
                }
                enemy.moveLeft();
                repaint();
                if (enemy.getX() + enemy.getWidth() < 0) {
                    SwingUtilities.invokeLater(() -> {
                        computer.remove(enemy);
                        repaint();
                    });
                }
                try {
                    Thread.sleep(GameSpeed - 13);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public int getGameSpeed() {return GameSpeed;}
    public int[] getRandomWidthAndHeight() {
        int[] widthAndHeight = new int[2];
        int[] WidthRange = new int[] {70, 45, 30};
        int[] HeightRange = new int[] {70, 45};
        int randomWidth = rand.nextInt(3);
        widthAndHeight[0] = WidthRange[randomWidth];
        if (randomWidth != 2) {
            widthAndHeight[1] = HeightRange[1];
            return widthAndHeight;
        }
        widthAndHeight[1] = HeightRange[0];
        return widthAndHeight;

    }
    private void AddComputer() {
        AtomicInteger SpeedCheck = new AtomicInteger();
        new Thread(() -> {
            while (!this.GameOver) {
                int[] widthAndHeight = getRandomWidthAndHeight();
                Player enemy = new Player(800, 470 - widthAndHeight[1], widthAndHeight[0], widthAndHeight[1]);
                computer.add(enemy);
                NpcMovement(computer.getLast());
                if (SpeedCheck.get() == 10 && GameSpeed - 15 > 0) {
                    GameSpeed--;
                    SpeedCheck.set(0);
                }
                SpeedCheck.incrementAndGet();

                try {
                    Thread.sleep(GameSpeed * 150L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void ScoreScale() {
        new Thread(() -> {
            while (!this.GameOver) {
                this.Score++;
                repaint();
                try {
                    Thread.sleep(GameSpeed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void LifeLoss() {
        this.life--;
    }

    private void LoadHeart() {
        ImageIcon heart = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/heart.png")));
        Image NewHeart = heart.getImage();
        Heart = NewHeart.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
    }



}