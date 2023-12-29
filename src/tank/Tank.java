package tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    private Point center;
    private Point corner;
    private Point turret;
    private float angle;
    private BufferedImage baseImage;
    private int speed;
    private int friction;

    public Tank(Point center, float angle) {
        this.center = center;
        String imagePath = "res/img/greenTank.png";
        try {
            baseImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (baseImage != null) {
            System.out.println("Image loaded successfully.");

        } else {
            System.out.println("Failed to load the image.");
        }
        this.corner = new Point(center.x - (baseImage.getWidth() / 2), center.y - (baseImage.getHeight() / 2));
    }

    private BufferedImage rotateImage(BufferedImage originalImage, double angle) {
        // Calculate the new size for the rotated image
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newWidth = (int) Math.ceil(Math.abs(Math.cos(angle)) * width + Math.abs(Math.sin(angle)) * height);
        int newHeight = (int) Math.ceil(Math.abs(Math.sin(angle)) * width + Math.abs(Math.cos(angle)) * height);

        // Create a new BufferedImage with transparency
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // Set the rotation angle in radians
        double rotationAngle = Math.toRadians(angle);

        // Set the rotation point to the center of the image
        AffineTransform at = AffineTransform.getRotateInstance(rotationAngle, newWidth / 2.0, newHeight / 2.0);

        // Apply the transformation
        at.translate((newWidth - width) / 2.0, (newHeight - height) / 2.0);

        // Enable anti-aliasing for smoother edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rotated image onto the new BufferedImage
        g2d.drawImage(originalImage, at, null);

        // Dispose of the graphics context to free up resources
        g2d.dispose();

        return rotatedImage;
    }

    public void draw(Graphics g) {
        BufferedImage rotatedImage;

        rotatedImage = rotateImage(baseImage, angle);
        // Draw the rotated image
        g.drawImage(rotatedImage, 100, 100, null);
    }

    public BufferedImage getBaseImage() {
        return baseImage;
    }
}
