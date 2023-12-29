package main.models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank {
    private Point corner;
    private float angle;
    private BufferedImage baseImage;
    private BufferedImage rotatedImage;

    private int speed = 2;
    private int friction;

    public Tank(Point corner, float angle) {
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

    public Point getCorner() {
        return corner;
    }

    public void setCorner(Point corner) {
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

    public int getSpeed() {
        return speed;
    }
}
