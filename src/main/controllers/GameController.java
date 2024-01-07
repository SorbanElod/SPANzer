package main.controllers;

import main.models.Map;
import main.models.Tank;
import main.views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class GameController {
    private Tank t1, t2;
    private TankController tc1, tc2;
    private TankView tv1, tv2;
    private GamePanel gamePanel;
    private Map map;
    private MapView mapView;
    private MapController mc;
    private StatPanel sp;
    private CollisionDetector cd;
    private final int width = 10;
    private final int height = 5;

    public GameController() {
        initGame();
        startGame();

        int delay = 16; // delay in milliseconds
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (t1.isSpawned() && t2.isSpawned()) {
                    cd.bulletCollisionWithTank();
                    cd.tankCollisionWithWalls();
                    cd.bulletCollisionWithWalls();

                    tc1.updatePosition();
                    tc2.updatePosition();
                    gamePanel.repaint();
                } else {
                    endGame();
                    startGame();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        timer.start();
    }

    private void endGame() {
        if (!t1.isSpawned()) sp.setP2Score(sp.getP2Score() + 1);
        if (!t2.isSpawned()) sp.setP1Score(sp.getP1Score() + 1);
        t1.getBullets().clear();
        t2.getBullets().clear();
    }

    private void initGame() {
        sp = new StatPanel();
        this.t1 = new Tank(new Point2D.Float(0, 0), 0, "greenTank.png");
        this.t2 = new Tank(new Point2D.Float(0, 0), 0, "pinkTank.png");

        this.tv1 = new TankView(t1);
        this.tv2 = new TankView(t2);

        this.tc1 = new TankController(t1, 1);
        this.tc2 = new TankController(t2, 2);

        this.map = new Map(width, height, 100);
        this.mapView = new MapView(map);
        this.mc = new MapController(map);

        cd = new CollisionDetector(t1, t2, map);

        this.gamePanel = new GamePanel(tv1, tv2, mapView);
        GameFrame gameFrame = new GameFrame(gamePanel, sp);

        gameFrame.addKeyListener(tc1);
        gameFrame.addKeyListener(tc2);

        gameFrame.setFocusable(true);
        gameFrame.requestFocusInWindow();
    }


    private void startGame() {
        mc.generate();
        tc1.spawn(map);
        tc2.spawn(map);
        while (t1.getCorner().distance(t2.getCorner()) < 2 * map.getBrickSize()) {
            tc2.spawn(map);
        }
    }
}


