package main.controllers;

import main.models.Brick;
import main.models.Bullet;
import main.models.Map;
import main.models.Tank;

import java.util.stream.Stream;

public class CollisionDetector {
    private final Tank tank1, tank2;
    private final Map map;
    private final float friction = 0.44f;
    private static final int hitBoxRadius = 25;

    public CollisionDetector(Tank tank1, Tank tank2, Map map) {
        this.tank1 = tank1;
        this.tank2 = tank2;
        this.map = map;
    }


    public void bulletCollisionWithTank() {
        //√[(x₂ - x₁)² + (y₂ - y₁)²]
        //checks the distance between the target tank, and the selected ball
        //if the distance is smaller than the hitbox radius, then the tank is hit
        Stream.concat(tank1.getBullets().stream(), tank2.getBullets().stream()).forEach(bullet -> {
            if (Math.sqrt(Math.pow((bullet.getCenter().x - tank1.getCenter().x), 2) + Math.pow((bullet.getCenter().y - tank1.getCenter().y), 2)) <= hitBoxRadius) {
                tank1.setSpawned(false);
            }
            if (Math.sqrt(Math.pow((bullet.getCenter().x - tank2.getCenter().x), 2) + Math.pow((bullet.getCenter().y - tank2.getCenter().y), 2)) <= hitBoxRadius) {
                tank2.setSpawned(false);
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

    /*
    TODO
    improve this detector its really really buggy
    the problems are mainly with the wall ends
     */
    private void legacyBulletWithWallCollisionChecker(Brick brick, Bullet bullet) {
        final int offset = 5;
        if (brick.isHorizontal()) {
            //check if bullet can collide with wall's side
            if (bullet.getCenter().x >= brick.getStart().x && bullet.getCenter().x <= brick.getEnd().x) {
                //collision with the wall above
                if (bullet.getCenter().y >= brick.getStart().y && bullet.getCenter().y + bullet.getvY() - offset <= brick.getStart().y) {
                    bullet.invertY();
                }
                //collision with the wall below
                if (bullet.getCenter().y <= brick.getStart().y && bullet.getCenter().y + bullet.getvY() >= brick.getStart().y) {
                    bullet.invertY();
                }
            }
            //checks if bullet can collide with the walls end
            if (bullet.getCenter().y - bullet.getRadius() <= brick.getStart().y && bullet.getCenter().y + bullet.getRadius() >= brick.getStart().y) {
                //collision with the right end
                if (bullet.getCenter().x >= brick.getEnd().x && bullet.getCenter().x + bullet.getvX() <= brick.getEnd().x) {
                    bullet.invertX();
                }
                //collision with the left end
                if (bullet.getCenter().x <= brick.getStart().x && bullet.getCenter().x + bullet.getvX() >= brick.getStart().x) {
                    bullet.invertX();
                }
            }
        } else if (brick.isVertical()) {
            //check if bullet can collide with wall's side
            if (bullet.getCenter().y >= brick.getStart().y && bullet.getCenter().y <= brick.getEnd().y) {
                //collision with the wall from right
                if (bullet.getCenter().x >= brick.getStart().x && bullet.getCenter().x + bullet.getvX() - offset <= brick.getStart().x) {
                    bullet.invertX();
                }
                //collision with the wall from left
                if (bullet.getCenter().x <= brick.getStart().x && bullet.getCenter().x + bullet.getvX() >= brick.getStart().x) {
                    bullet.invertX();
                }
            }
            //checks if bullet can collide with the walls end
            if (bullet.getCenter().x - bullet.getRadius() <= brick.getStart().x && bullet.getCenter().x + bullet.getRadius() >= brick.getStart().x) {
                //collision with the bottom end
                if (bullet.getCenter().y >= brick.getEnd().y && bullet.getCenter().y + bullet.getvY() <= brick.getEnd().y) {
                    bullet.invertY();
                }
                //collision with top end
                if (bullet.getCenter().y <= brick.getStart().y && bullet.getCenter().y + bullet.getvY() >= brick.getStart().y) {
                    bullet.invertY();
                }
            }
        }
    }

    public void tankCollisionWithWalls() {
        tankCollisionWithWalls(this.tank1);
        tankCollisionWithWalls(this.tank2);
    }

    private void tankCollisionWithWalls(Tank tank) {
        map.getWalls().forEach(brick ->
        {
            float Vx = 1;
            float Vy = 1;
            if (brick.isHorizontal()) {
                //check if tank can collide with wall's side
                if (tank.getCorner().x + tank.getImgSize() >= brick.getStart().x && tank.getCorner().x <= brick.getEnd().x) {
                    //collision with the wall above
                    if (tank.getCorner().y > brick.getStart().y && tank.getCorner().y + tank.getvY() < brick.getStart().y) {
                        Vy = 0;
                        Vx *= friction;
                    }
                    //collision with the wall below
                    if (tank.getCorner().y + tank.getImgSize() < brick.getStart().y && tank.getCorner().y + tank.getImgSize() + tank.getvY() > brick.getStart().y) {
                        Vy = 0;
                        Vx *= friction;
                    }
                }
                //checks if tank can collide with the walls end
                if (tank.getCorner().y < brick.getStart().y && tank.getCorner().y + tank.getImgSize() > brick.getStart().y) {
                    //collision with the right end
                    if (tank.getCorner().x > brick.getEnd().x && tank.getCorner().x + tank.getvX() < brick.getEnd().x) {
                        Vx = 0;
                        Vy *= friction;
                    }
                    //collision with the left end
                    if (tank.getCorner().x + tank.getImgSize() < brick.getStart().x && tank.getCorner().x + tank.getImgSize() + tank.getvX() > brick.getStart().x) {
                        Vx = 0;
                        Vy *= friction;
                    }
                }
            } else if (brick.isVertical()) {
                //check if tank can collide with wall's side
                if (tank.getCorner().y + tank.getImgSize() >= brick.getStart().y && tank.getCorner().y <= brick.getEnd().y) {
                    //collision with the wall from right
                    if (tank.getCorner().x > brick.getStart().x && tank.getCorner().x + tank.getvX() < brick.getStart().x) {
                        Vx = 0;
                        Vy *= friction;
                    }
                    //collision with the wall from left
                    if (tank.getCorner().x + tank.getImgSize() < brick.getStart().x && tank.getCorner().x + tank.getImgSize() + tank.getvX() > brick.getStart().x) {
                        Vx = 0;
                        Vy *= friction;
                    }
                }
                //checks if tank can collide with the walls end
                if (tank.getCorner().x < brick.getStart().x && tank.getCorner().x + tank.getImgSize() > brick.getStart().x) {
                    //collision with the bottom end
                    if (tank.getCorner().y > brick.getEnd().y && tank.getCorner().y + tank.getvY() < brick.getEnd().y) {
                        Vy = 0;
                        Vx *= friction;
                    }
                    //collision with top end
                    if (tank.getCorner().y + tank.getImgSize() < brick.getStart().y && tank.getCorner().y + tank.getImgSize() + tank.getvY() > brick.getStart().y) {
                        Vy = 0;
                        Vx *= friction;
                    }
                }
            }
            tank.setvXWeight(Vx);
            tank.setvYWeight(Vy);
        });
    }
}
