package main.controllers;

import main.models.Map;
import main.models.Tank;

import java.util.stream.Stream;

public class CollisionDetector {
    private Tank t1, t2;
    private Map map;

    private static int hitBoxRadius = 20;

    public CollisionDetector(Tank t1, Tank t2, Map map) {
        this.t1 = t1;
        this.t2 = t2;
        this.map = map;
    }

    public void BulletCollisionWithTank()
    {
        //√[(x₂ - x₁)² + (y₂ - y₁)²]
        //checks the distance between the target tank, and the selected ball
        //if the distance is smaller than the hitbox radius, then the tank is hit
        Stream.concat(t1.getBullets().stream(),t2.getBullets().stream()).forEach(bullet -> {
            if(Math.sqrt(Math.pow((bullet.getCenter().x - t1.getCenter().x), 2)+ Math.pow((bullet.getCenter().y - t1.getCenter().y), 2)) <= hitBoxRadius)
            {
                t1.setSpawned(false);
            }
            if(Math.sqrt(Math.pow((bullet.getCenter().x - t2.getCenter().x), 2)+ Math.pow((bullet.getCenter().y - t2.getCenter().y), 2)) <= hitBoxRadius)
            {
                t2.setSpawned(false);
            }
        });

    }

    public void BulletCollisionWithWalls() {
        Stream.concat(t1.getBullets().stream(),t2.getBullets().stream()).forEach(b -> {
            map.getWalls().stream().forEach(w -> {
                if (w.isHorizontal())
                {
                    //check if bullet can collide with wall's side
                    if (b.getCenter().x >= w.getStart().x && b.getCenter().x <= w.getEnd().x)
                    {
                        //collision with the wall above
                        if (b.getCenter().y >= w.getStart().y && b.getCenter().y + b.getvY() <= w.getStart().y)
                        {
                            b.setvY(-b.getvY());
                        }
                        //collision with the wall below
                        if (b.getCenter().y <= w.getStart().y && b.getCenter().y + b.getvY() >= w.getStart().y)
                        {
                            b.setvY(-b.getvY());
                        }
                    }
                    //checks if bullet can collide with the walls end
                    if (b.getCenter().y - b.getRadius() <= w.getStart().y && b.getCenter().y + b.getRadius() >= w.getStart().y)
                    {
                        //collision with the right end
                        if (b.getCenter().x >= w.getEnd().x && b.getCenter().x + b.getvX() <= w.getEnd().x)
                        {
                            b.setvX(-b.getvX());
                        }
                        //collision with the left end
                        if (b.getCenter().x <= w.getStart().x && b.getCenter().x + b.getvX() >= w.getStart().x)
                        {
                            b.setvX(-b.getvX());
                        }
                    }
                }
                else if (w.isVertical())
                {
                    //check if bullet can collide with wall's side
                    if (b.getCenter().y >= w.getStart().y && b.getCenter().y <= w.getEnd().y)
                    {
                        //collision with the wall from right
                        if (b.getCenter().x >= w.getStart().x && b.getCenter().x + b.getvX() <= w.getStart().x)
                        {
                            b.setvX(-b.getvX());
                        }
                        //collision with the wall from left
                        if (b.getCenter().x <= w.getStart().x && b.getCenter().x + b.getvX() >= w.getStart().x)
                        {
                            b.setvX(-b.getvX());
                        }
                    }
                    //checks if bullet can collide with the walls end
                    if (b.getCenter().x - b.getRadius() <= w.getStart().x && b.getCenter().x + b.getRadius() >= w.getStart().x)
                    {
                        //collision with the bottom end
                        if (b.getCenter().y >= w.getEnd().y && b.getCenter().y + b.getvY() <= w.getEnd().y)
                        {
                            b.setvY(-b.getvY());
                        }
                        //collision with top end
                        if (b.getCenter().y <= w.getStart().y && b.getCenter().y + b.getvY() >= w.getStart().y)
                        {
                            b.setvY(-b.getvY());
                        }
                    }
                }
            });
        });
    }

}
