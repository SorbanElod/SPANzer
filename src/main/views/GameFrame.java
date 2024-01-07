package main.views;

import main.models.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu helpMenu = new JMenu("Help");
        JMenu fileMenu = new JMenu("File");

        JMenuItem helpMenuItem = new JMenuItem("Help");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        helpMenu.add(helpMenuItem);

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);

        this.setJMenuBar(menuBar);
        this.gamePanel = gamePanel;

        JPanel mapPanel = new JPanel();
        mapPanel.add(gamePanel);

        helpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpFrame hf = new HelpFrame();
                hf.setVisible(true);
            }
        });

        this.add(mapPanel, BorderLayout.CENTER);
        this.add(sp, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Resize method
    public void resizeFrame(int newWidth, int newHeight) {
        this.setSize(newWidth, newHeight);
        this.setLocationRelativeTo(null);
    }
}
