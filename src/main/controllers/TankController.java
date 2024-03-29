package main.controllers;

import main.models.Bullet;
import main.models.Map;
import main.models.Tank;

import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class TankController implements KeyListener {
    private final Tank tank;
    private final BulletController bc;
    private final int player;
    private final Random rng;
    private final CollisionDetector cd;
    private boolean forward, backward;
    private boolean left, right;

    public TankController(Tank tank, int player, CollisionDetector cd) {
        this.tank = tank;
        this.cd = cd;
        forward = false;
        backward = false;
        left = false;
        right = false;
        this.player = player;
        rng = new Random();
        bc = new BulletController(tank.getBullets());
    }

    public void updatePosition() {
        bc.update();
        Point2D.Float currentPos = tank.getCorner();

        float angle = tank.getAngle();
        float vX = 0;
        float vY = 0;

        if (forward && backward) {
            vX = 0;
            vY = 0;
        } else if (forward) {
            vX = (float) (tank.getMoveSpeed() * Math.sin((angle * Math.PI / 180)));
            vY = -(float) (tank.getMoveSpeed() * Math.cos((angle * Math.PI / 180)));
        } else if (backward) {
            vX = -(float) (tank.getMoveSpeed() * Math.sin((angle * Math.PI / 180)));
            vY = (float) (tank.getMoveSpeed() * Math.cos((angle * Math.PI / 180)));
        }
        if (left) {
            angle -= tank.getAngularV();
        }
        if (right) {
            angle += tank.getAngularV();
        }
        angle = angle % 360;
        if (angle < 0) {
            angle = 360 - angle;
        }
        tank.setvX(vX);
        tank.setvY(vY);

        cd.tankCollisionWithWalls(tank);

        currentPos.x += tank.getvX();
        currentPos.y += tank.getvY();
        tank.setCorner(currentPos);
        tank.setAngle(angle);
        tank.setCenter(new Point2D.Float(
                tank.getCorner().x + (float) tank.getBaseImage().getWidth() / 2,
                tank.getCorner().y + (float) tank.getBaseImage().getWidth() / 2)
        );
        tank.setTurret(new Point2D.Float(
                (float) (tank.getCenter().x + (tank.getCannonLength() * Math.sin(Math.PI / 180 * tank.getAngle()))),
                (float) (tank.getCenter().y - (tank.getCannonLength() * Math.cos(Math.PI / 180 * tank.getAngle())))
        ));
    }

    public void spawn(Map map) {
        tank.setCorner(new Point2D.Float(
                rng.nextInt(0, map.getWidth() - 1) * map.getBrickSize() + 20,
                rng.nextInt(0, map.getHeight() - 1) * map.getBrickSize() + 20
        ));
        tank.setAngle(rng.nextFloat(0, 360));
        tank.getBullets().clear();
        tank.setSpawned(true);
    }

    private void fire() {
        Bullet bullet = new Bullet(tank.getTurret(), tank.getDirX(), tank.getDirY());
        if (tank.addBullet(bullet)) {
            bounce();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (player == 1 && e.getKeyCode() == KeyEvent.VK_M) {
            fire();
        } else if (player == 2 && e.getKeyCode() == KeyEvent.VK_Q) {
            fire();
        }
        handleKey(e.getKeyCode(), false);
    }

    private void handleKey(int keyCode, boolean pressed) {
        if (player == 1) {
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    forward = pressed;
                    break;
                case KeyEvent.VK_DOWN:
                    backward = pressed;
                    break;
                case KeyEvent.VK_LEFT:
                    left = pressed;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = pressed;
                    break;
            }
        } else if (player == 2) {
            switch (keyCode) {
                case KeyEvent.VK_W:
                    forward = pressed;
                    break;
                case KeyEvent.VK_S:
                    backward = pressed;
                    break;
                case KeyEvent.VK_A:
                    left = pressed;
                    break;
                case KeyEvent.VK_D:
                    right = pressed;
                    break;
            }
        }
    }

    private void bounce() {
        Thread thread = new Thread(() -> {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/sound/sfx/bounce.wav"));
                Clip bounceClip = AudioSystem.getClip();
                bounceClip.open(audioInputStream);
                bounceClip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
}
