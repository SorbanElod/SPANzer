package main.views;

import main.models.Tank;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;
    private TankView tv1;
    private TankView tv2;
    private Tank t1;
    private Tank t2;

    public GameFrame(GamePanel gamePanel, StatPanel sp) {
        this(gamePanel, sp, 1280, 720);
    }

    // Parameterized constructor
    public GameFrame(GamePanel gamePanel, StatPanel sp, int width, int height) {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.gamePanel = gamePanel;

        JPanel mapPanel = new JPanel();
        mapPanel.add(gamePanel);

        this.add(mapPanel, BorderLayout.CENTER);
        this.add(sp, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    // Resize method
    public void resizeFrame(int newWidth, int newHeight) {
        this.setSize(newWidth, newHeight);
        this.setLocationRelativeTo(null);
    }
}
