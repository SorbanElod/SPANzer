package main.models;

import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.LocalTime;

import static java.time.Duration.ofSeconds;

public class Bullet {
    private final float radius = 2.5f;
    private final float speed = 5f;
    private float vX, vY;
    private Point2D.Float center;
    private LocalTime created;
    private final Duration lifetime = ofSeconds(5);

    public Bullet(Point2D.Float center, float dX, float dY) {
        this.center = center;
        this.created = LocalTime.now();
        this.vX = dX * speed;
        this.vY = dY * speed;
    }

    public float getRadius() {
        return radius;
    }

    public Point2D.Float getCenter() {
        return center;
    }

    public float getvX() {
        return vX;
    }

    public float getvY() {
        return vY;
    }

    public LocalTime getCreated() {
        return created;
    }

    public Duration getLifetime() {
        return lifetime;
    }

    public void setCenter(float x, float y) {
        center.x = x;
        center.y = y;
    }

    public void invertX() {
        this.vX = -this.vX;
    }

    public void invertY() {
        this.vY = -this.vY;
    }


}
