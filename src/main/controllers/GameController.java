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
import java.util.Random;

public class GameController {
    private Tank tank1, tank2;
    private TankController tc1, tc2;
    private GamePanel gamePanel;
    private TankView tankView1, tankView2;
    private Map map;
    private MapView mapView;
    private MapController mc;
    private int width = 10;
    private int height = 5;

    public GameController() {
        initGame();
        startGame();

        int delay = 16; // delay in milliseconds (adjust as needed for your desired frame rate)
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc1.updatePosition();
                tc2.updatePosition();
                gamePanel.repaint();
            }
        });
        timer.start();
    }

    private void initGame() {
        this.tank1 = new Tank(new Point2D.Float(0, 0), 0, "transparent.png");
        this.tank2 = new Tank(new Point2D.Float(0, 0), 0, "transparent.png");

        this.tankView1 = new TankView(tank1);
        this.tankView2 = new TankView(tank2);

        this.tc1 = new TankController(tank1, 1);
        this.tc2 = new TankController(tank2, 2);

        this.map = new Map(width, height, 100);
        this.mapView = new MapView(map);
        this.mc = new MapController(map);

        this.gamePanel = new GamePanel(tankView1, tankView2, mapView);
        GameFrame gameFrame = new GameFrame(gamePanel);

        gameFrame.addKeyListener(tc1);
        gameFrame.addKeyListener(tc2);

        gameFrame.setFocusable(true);
        gameFrame.requestFocusInWindow();
    }

    private void startGame() {
        mc.generate();
        Random rng = new Random();
        tc1.spawn(map, "greenTank.png");
        tc2.spawn(map, "pinkTank.png");

        tankView1.setTank(tank1);
        tankView2.setTank(tank2);

        tc1.setTank(tank1);
        tc2.setTank(tank2);
    }

    public TankView getTankView1() {
        return tankView1;
    }

    public TankView getTankView2() {
        return tankView2;
    }

}


