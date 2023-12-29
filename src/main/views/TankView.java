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
        double scaleFactor = 1.5;
        int correction = 10;
        BufferedImage rotatedBmp = new BufferedImage((int) (width * scaleFactor + correction), (int) (height * scaleFactor + correction), BufferedImage.TYPE_INT_ARGB);
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

    public void draw(Graphics g) {
        BufferedImage rotatedImage;

        rotatedImage = rotateImage();
        // Draw the rotated image
        g.drawImage(rotatedImage, tank.getCorner().x, tank.getCorner().y, null);
    }

}
