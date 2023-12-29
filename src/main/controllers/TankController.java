package main.controllers;

import main.models.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class TankController implements KeyListener {
    private Tank tank;
    private boolean forward;
    private boolean backward;
    private boolean left;
    private boolean right;

    private int player;

    public TankController(Tank tank, int player) {
        this.tank = tank;
        forward = false;
        backward = false;
        left = false;
        right = false;
        this.player = player;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void updatePosition() {
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
        currentPos.x += vX;
        currentPos.y += vY;
        tank.setCorner(currentPos);
        tank.setAngle(angle);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
        } else {
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

}
