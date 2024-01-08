package main.controllers;

import main.models.Map;
import main.models.Tank;
import main.views.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public class GameController {
    private final int width = 11;
    private final int height = 6;
    private Tank t1, t2;
    private TankController tc1, tc2;
    private TankView tv1, tv2;
    private GamePanel gamePanel;
    private Map map;
    private MapView mapView;
    private MapController mc;
    private StatPanel sp;
    private CollisionDetector cd;

    public GameController() {
        initGame();
        startGame();

        int delay = 16; // delay in milliseconds
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (t1.isSpawned() && t2.isSpawned()) {
                    cd.bulletCollisionWithTank();
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
        explode();
        if (!t1.isSpawned()) sp.setP2Score(sp.getP2Score() + 1);
        if (!t2.isSpawned()) sp.setP1Score(sp.getP1Score() + 1);
        t1.getBullets().clear();
        t2.getBullets().clear();
    }

    private void initGame() {
        startMusic();
        sp = new StatPanel();
        this.t1 = new Tank(new Point2D.Float(0, 0), 0, "greenTank.png");
        this.t2 = new Tank(new Point2D.Float(0, 0), 0, "pinkTank.png");

        this.tv1 = new TankView(t1);
        this.tv2 = new TankView(t2);

        this.map = new Map(width, height, 100);
        this.mapView = new MapView(map);
        this.mc = new MapController(map);

        cd = new CollisionDetector(t1, t2, map);

        this.tc1 = new TankController(t1, 1, cd);
        this.tc2 = new TankController(t2, 2, cd);

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

    private void startMusic() {
        Clip musicClip;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/sound/music/rentedstuff.wav"));
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        Thread thread = new Thread(() -> {
            musicClip.start();
            musicClip.loop(Integer.MAX_VALUE);
        });
        thread.start();
    }

    private void explode() {
        Clip explosionClip;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/sound/sfx/explosion.wav"));
            explosionClip = AudioSystem.getClip();
            explosionClip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        Thread thread = new Thread(explosionClip::start);
        thread.start();
    }
}


