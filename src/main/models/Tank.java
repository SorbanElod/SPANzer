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

    public Tank(Point2D.Float corner, float angle, String fileName) {
        this.corner = corner;
        this.angle = angle;
        setBaseImage(fileName);
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

    public void setBaseImage(String fileName) {
        String imagePath = "src/resources/img/" + fileName;
        try {
            baseImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getAngularV() {
        return angularV;
    }

}
