package main.controllers;

import main.models.Tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    public void update() {
        Point p = tank.getCorner();
        if (forward) {
            p.y -= tank.getSpeed();
        }
        if (backward) {
            p.y += tank.getSpeed();
        }
        if (left) {
            p.x -= tank.getSpeed();
        }
        if (right) {
            p.x += tank.getSpeed();
        }
        tank.setCorner(p);
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
        if(player == 1){
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
        }
        else{
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
