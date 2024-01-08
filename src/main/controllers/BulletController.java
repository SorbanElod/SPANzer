package main.controllers;

import main.models.Bullet;

import java.time.LocalTime;
import java.util.List;

import static java.time.Duration.between;

public class BulletController {
    private final List<Bullet> bullets;

    public BulletController(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void update() {
        bullets.removeIf(bullet -> (between(bullet.getCreated(), LocalTime.now()).compareTo(bullet.getLifetime()) > 0));
        bullets.forEach(bullet -> {
            bullet.setCenter(
                    bullet.getCenter().x + bullet.getvX(),
                    bullet.getCenter().y + bullet.getvY()
            );
        });
    }
}
