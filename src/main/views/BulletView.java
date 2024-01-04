package main.views;

import main.models.Bullet;
import main.models.Tank;

import java.util.ArrayList;
import java.util.List;

public class BulletView {
    private List<Bullet> bullets;
    public BulletView(){
        this.bullets = new ArrayList<>();
    }

    private void addBullet(Bullet bullet){
        if(bullets.size() <= Tank.bulletCapacity){
            bullets.add(bullet);
        }
    }
}
