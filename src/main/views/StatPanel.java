package main.views;

import javax.swing.*;
import java.awt.*;

public class StatPanel extends JPanel {
    private JLabel p1Label;
    private JLabel p2Label;

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
        this.add(p1Label);
        this.add(separatorLabel);
        this.add(p2Label);
    }

    public void updateP1Score(int score) {
        p1Label.setText(String.valueOf(score));
    }

    public void updateP2Score(int score) {
        p2Label.setText(String.valueOf(score));
    }
}
