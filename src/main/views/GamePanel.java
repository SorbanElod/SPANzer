package main.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends JPanel {

    private TankView tankView1;
    private TankView tankView2;
    private MapView mapView;
    private final int offset = 10;

    public GamePanel(TankView tankView1, TankView tankView2, MapView mapView) {
        this.tankView1 = tankView1;
        this.tankView2 = tankView2;
        this.mapView = mapView;
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(mapView.getMapDimension());
        Border verticalOffset = BorderFactory.createEmptyBorder(50, 0, 0, 0);
        this.setBorder(verticalOffset);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mapView.draw(g);
        tankView1.draw(g);
        tankView2.draw(g);
    }
}
