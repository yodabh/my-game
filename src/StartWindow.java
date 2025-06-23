import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void showStartScreen() {
        JFrame window = new JFrame("מסך פתיחה");
        window.setSize(Main.WIDTH, Main.HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);

        JButton startButton = new JButton("START");
        startButton.setBounds(300, 200,200,50);
        window.add(startButton);
        JLabel label = new JLabel("Dino Game", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 64));
        label.setForeground(Color.BLACK);
        label.setBounds(150, 100, 500, 100);
        window.add(label);


        startButton.addActionListener(e -> {
            window.dispose();

            JFrame gameWindow = new JFrame("משחק הקקטוסים");
            gameWindow.setSize(800, 600);
            gameWindow.setLocationRelativeTo(null);
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameWindow.setLayout(null);

            MainScene mainScene = new MainScene(0, 0, 800, 600);
            gameWindow.add(mainScene);
            gameWindow.setVisible(true);
            mainScene.requestFocusInWindow();
        });

        window.setVisible(true);
    }
}
