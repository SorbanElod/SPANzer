package main.models;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    private Point2D.Float corner;
    private float angle;
    private BufferedImage baseImage;
    private BufferedImage rotatedImage;

    private float moveSpeed = 3.5f;
    private int friction;
    private float angularV = 4f;

    public Tank(Point2D.Float corner, float angle) {
        this.corner = corner;
        this.angle = angle;
        String imagePath = "src/resources/img/pinkTank.png";
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
    }

    public Point2D.Float getCorner() {
        return corner;
    }

    public void setCorner(Point2D.Float corner) {
        this.corner = corner;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public BufferedImage getBaseImage() {
        return baseImage;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getAngularV() {
        return angularV;
    }

}
