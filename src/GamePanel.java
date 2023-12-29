import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import tank.Tank;

public class GamePanel extends JPanel {

    private Tank t;

    GamePanel() {
        t = new Tank(new Point(300, 100), 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        t.draw(g);
    }
}
