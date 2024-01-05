package main.controllers;

import main.models.Brick;
import main.models.Bullet;
import main.models.Map;
import main.models.Tank;

public class CollisionDetector {
    private final Tank tank1, tank2;
    private final Map map;

    private static final int hitBoxRadius = 20;

    public CollisionDetector(Tank tank1, Tank tank2, Map map) {
        this.tank1 = tank1;
        this.tank2 = tank2;
        this.map = map;
    }

    public void bulletCollisionWithTank() {
        checkBulletCollisionWithTank(tank1);
        checkBulletCollisionWithTank(tank2);
    }

    private void checkBulletCollisionWithTank(Tank tank) {
        tank.getBullets().forEach(bullet -> {
            if (calculateDistance(bullet, tank) <= hitBoxRadius) {
                tank.setSpawned(false);
            }
        });
    }

    public void bulletCollisionWithWalls() {
        tank1.getBullets().forEach(this::checkBulletCollisionWithWalls);
        tank2.getBullets().forEach(this::checkBulletCollisionWithWalls);
    }

    private void checkBulletCollisionWithWalls(Bullet bullet) {
        map.getWalls().stream()
                .filter(brick -> (brick.getStart().distance(bullet.getCenter()) < 2 * map.getBrickSize()))
                .forEach(brick -> legacyBulletWithWallCollisionChecker(brick, bullet));
    }

    private void legacyBulletWithWallCollisionChecker(Brick brick, Bullet bullet) {
        if (brick.isHorizontal()) {
            //check if bullet can collide with wall's side
            if (bullet.getCenter().x >= brick.getStart().x && bullet.getCenter().x <= brick.getEnd().x) {
                //collision with the wall above
                if (bullet.getCenter().y >= brick.getStart().y && bullet.getCenter().y + bullet.getvY() <= brick.getStart().y) {
                    bullet.invetY();
                }
                //collision with the wall below
                if (bullet.getCenter().y <= brick.getStart().y && bullet.getCenter().y + bullet.getvY() >= brick.getStart().y) {
                    bullet.invetY();
                }
            }
            //checks if bullet can collide with the walls end
            if (bullet.getCenter().y - bullet.getRadius() <= brick.getStart().y && bullet.getCenter().y + bullet.getRadius() >= brick.getStart().y) {
                //collision with the right end
                if (bullet.getCenter().x >= brick.getEnd().x && bullet.getCenter().x + bullet.getvX() <= brick.getEnd().x) {
                    bullet.invetX();
                }
                //collision with the left end
                if (bullet.getCenter().x <= brick.getStart().x && bullet.getCenter().x + bullet.getvX() >= brick.getStart().x) {
                    bullet.invetX();
                }
            }
        } else if (brick.isVertical()) {
            //check if bullet can collide with wall's side
            if (bullet.getCenter().y >= brick.getStart().y && bullet.getCenter().y <= brick.getEnd().y) {
                //collision with the wall from right
                if (bullet.getCenter().x >= brick.getStart().x && bullet.getCenter().x + bullet.getvX() <= brick.getStart().x) {
                    bullet.invetX();
                }
                //collision with the wall from left
                if (bullet.getCenter().x <= brick.getStart().x && bullet.getCenter().x + bullet.getvX() >= brick.getStart().x) {
                    bullet.invetX();
                }
            }
            //checks if bullet can collide with the walls end
            if (bullet.getCenter().x - bullet.getRadius() <= brick.getStart().x && bullet.getCenter().x + bullet.getRadius() >= brick.getStart().x) {
                //collision with the bottom end
                if (bullet.getCenter().y >= brick.getEnd().y && bullet.getCenter().y + bullet.getvY() <= brick.getEnd().y) {
                    bullet.invetY();
                }
                //collision with top end
                if (bullet.getCenter().y <= brick.getStart().y && bullet.getCenter().y + bullet.getvY() >= brick.getStart().y) {
                    bullet.invetY();
                }
            }
        }

    }

    private double calculateDistance(Bullet bullet, Tank tank) {
        return Math.sqrt(Math.pow((bullet.getCenter().x - tank.getCenter().x), 2) +
                Math.pow((bullet.getCenter().y - tank.getCenter().y), 2));
    }
}
