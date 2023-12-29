package main.views;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    TankView tankView1;
    TankView tankView2;

    public GamePanel(TankView tankView1, TankView tankView2) {
        this.tankView1 = tankView1;
        this.tankView2 = tankView2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tankView1.draw(g);
        tankView2.draw(g);
    }
}
