package main.views;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    TankView tankView1;
    TankView tankView2;
    MapView mapView;

    public GamePanel(TankView tankView1, TankView tankView2, MapView mapView) {
        this.tankView1 = tankView1;
        this.tankView2 = tankView2;
        this.mapView = mapView;
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(6 * 100 + 10, 4 * 100 + 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tankView1.draw(g);
        tankView2.draw(g);
        mapView.draw(g);
    }
}
