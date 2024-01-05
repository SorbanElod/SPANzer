package main.controllers;

import main.models.Map;
import main.models.Tank;
import main.views.GameFrame;
import main.views.GamePanel;
import main.views.MapView;
import main.views.TankView;

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

                if(t1.isSpawned() && t2.isSpawned()){
                    cd.bulletCollisionWithTank();
                    tc1.updatePosition();
                    tc2.updatePosition();
                    cd.bulletCollisionWithWalls();
                    gamePanel.repaint();
                }
                else{
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

    private void initGame() {
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
        GameFrame gameFrame = new GameFrame(gamePanel);

        gameFrame.addKeyListener(tc1);
        gameFrame.addKeyListener(tc2);

        gameFrame.setFocusable(true);
        gameFrame.requestFocusInWindow();
    }

    private void startGame() {
        mc.generate();
        tc1.spawn(map);
        tc2.spawn(map);
        while (t1.getCorner().distance(t2.getCorner()) < map.getBrickSize()) {
            tc2.spawn(map);
        }
    }
}


