import javax.swing.*;
import java.awt.*;

public class EndWindow extends JPanel {

    public EndWindow() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 64));
        gameOverLabel.setForeground(Color.BLACK);

        this.add(gameOverLabel, BorderLayout.CENTER);
    }
}
