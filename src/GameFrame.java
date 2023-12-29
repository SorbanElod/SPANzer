import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;
    public GameFrame() {
        this(1280, 720);
    }

    // Parameterized constructor
    public GameFrame(int width, int height) {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        this.add(gamePanel,BorderLayout.CENTER);

        this.setVisible(true);
    }

    // Resize method
    public void resizeFrame(int newWidth, int newHeight) {
        this.setSize(newWidth, newHeight);
        this.setLocationRelativeTo(null);
    }
}
