package main.views;

import main.models.Tank;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class TankView {
    private Tank tank;

    public TankView(Tank tank) {
        this.tank = tank;
    }

    private BufferedImage rotateImage() {
        int width = tank.getBaseImage().getWidth();
        int height = tank.getBaseImage().getHeight();
        double scaleFactor = 1.0;
        BufferedImage rotatedBmp = new BufferedImage(
                (int) (width * scaleFactor),
                (int) (height * scaleFactor),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotatedBmp.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AffineTransform transform = new AffineTransform();
        transform.scale(scaleFactor, scaleFactor);
        transform.rotate(Math.toRadians(tank.getAngle()), width / 2.0, height / 2.0);

        g.transform(transform);
        g.drawImage(tank.getBaseImage(), 0, 0, null);
        g.dispose();

        return rotatedBmp;
    }

    public void drawBullets(Graphics g){
        tank.getBullets().forEach(bullet -> g.drawOval(
                (int) (bullet.getCenter().x - bullet.getRadius()),
                (int) (bullet.getCenter().y - bullet.getRadius()),
                (int) (2 * bullet.getRadius()),
                (int) (2 *  bullet.getRadius())
        ));
    }
    public void draw(Graphics g) {
        if (tank.isSpawned()) {
            BufferedImage rotatedImage;

            rotatedImage = rotateImage();
            // Draw the rotated image
            g.drawImage(rotatedImage, (int) tank.getCorner().x, (int) tank.getCorner().y, null);
            drawBullets(g);
        } else {
            g.drawImage(tank.getTransparentImage(), (int) tank.getCorner().x, (int) tank.getCorner().y, null);
        }

    }

}
