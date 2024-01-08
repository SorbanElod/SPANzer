package main.views;

import javax.swing.*;
import java.awt.*;

public class StatPanel extends JPanel {
    private final JLabel p1Label;
    private final JLabel p2Label;

    public StatPanel() {
        // displays player scores
        int fontSize = 32; // Adjust the size as needed
        Font labelFont = new Font("Arial", Font.PLAIN, fontSize);
        p1Label = new JLabel("0");
        p1Label.setFont(labelFont);
        JLabel separatorLabel = new JLabel(" : ");
        separatorLabel.setFont(labelFont);
        p2Label = new JLabel("0");
        p2Label.setFont(labelFont);
        this.add(p2Label);
        this.add(separatorLabel);
        this.add(p1Label);
    }

    public int getP1Score() {
        return Integer.parseInt(p1Label.getText());
    }

    public void setP1Score(int score) {
        p1Label.setText(String.valueOf(score));
    }

    public int getP2Score() {
        return Integer.parseInt(p2Label.getText());
    }

    public void setP2Score(int score) {
        p2Label.setText(String.valueOf(score));
    }
}
