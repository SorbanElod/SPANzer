package main.views;

import main.models.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
        this.setTitle("SPANzer");
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
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = "";
                content = "Player1: " + sp.getP1Score() + "\n" +
                        "Player2: " + sp.getP2Score() + "\n";
                saveToFile(content);
            }
        });

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = loadFromFile();
                if (content != null && !content.isEmpty()) {
                    // Parse the content and update checkbox states
                    String[] lines = content.split("\n");
                    for (String line : lines) {
                        String[] parts = line.split(": ");
                        if (parts.length == 2) {
                            String toppingName = parts[0].trim();
                            int score = Integer.parseInt(parts[1].trim());
                            switch (toppingName) {
                                case "Player1":
                                    sp.setP1Score(score);
                                    break;
                                case "Player2":
                                    sp.setP2Score(score);
                                    break;
                            }
                        }
                    }
                }
            }
        });
        this.add(mapPanel, BorderLayout.CENTER);
        this.add(sp, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void saveToFile(String content) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(content);
                JOptionPane.showMessageDialog(this, "File saved successfully!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private String loadFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Score From SaveFile");

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                return content.toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    // Resize method
    public void resizeFrame(int newWidth, int newHeight) {
        this.setSize(newWidth, newHeight);
        this.setLocationRelativeTo(null);
    }
}
