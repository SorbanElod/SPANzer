package main.models;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tank {
    private Point2D.Float corner;
    private Point2D.Float center;
    private Point2D.Float turret;
    private float angle;
    private BufferedImage baseImage;
    private BufferedImage transparentImage;
    private float moveSpeed = 3.5f;
    private float angularV = 4f;
    private boolean isSpawned;
    private final int cannonLength = 30;
    private List<Bullet> bullets;

    public static final int bulletCapacity = 5;

    public Tank(Point2D.Float corner, float angle, String fileName) {
        this.corner = corner;
        this.angle = angle;
        setBaseImage(fileName);
        String imagePath = "src/resources/img/transparent.png";
        try {
            transparentImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.center = new Point2D.Float(
                this.corner.x + (float) baseImage.getWidth() / 2,
                this.corner.y + (float) baseImage.getWidth() / 2
        );
        this.turret = new Point2D.Float(
                (float) (center.x + (cannonLength * Math.sin(Math.PI / 180 * angle))),
                (float) (center.y - (cannonLength * Math.cos(Math.PI / 180 * angle)))
        );
        isSpawned = false;
        bullets = new ArrayList<>();
    }

    public void addBullet(Bullet bullet) {
        if (bullets.size() < bulletCapacity) {
            bullets.add(bullet);
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public Point2D.Float getCorner() {
        return corner;
    }

    public void setCorner(Point2D.Float corner) {
        this.corner = corner;
    }

    public Point2D.Float getCenter() {
        return center;
    }

    public void setCenter(Point2D.Float center) {
        this.center = center;
    }

    public Point2D.Float getTurret() {
        return turret;
    }

    public void setTurret(Point2D.Float turret) {
        this.turret = turret;
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

    public int getCannonLength() {
        return cannonLength;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getAngularV() {
        return angularV;
    }

    public boolean isSpawned() {
        return isSpawned;
    }

    public void setSpawned(boolean spawned) {
        isSpawned = spawned;
    }

    public BufferedImage getTransparentImage() {
        return transparentImage;
    }

    public float getDirX() {
        return (float) (Math.sin((angle * Math.PI / 180)));

    }

    public float getDirY() {
        return -(float) (Math.cos((angle * Math.PI / 180)));
    }

    public void disposeBullets() {
        bullets.clear();
    }
}
