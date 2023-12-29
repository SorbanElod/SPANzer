package main.views;

import main.controllers.TankController;
import main.models.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private TankView tv1;
    private Tank t1;
    private TankController tc1;
    private TankView tv2;
    private Tank t2;
    private TankController tc2;

    GamePanel() {
        t1 = new Tank(new Point(300, 100), 10f);
        t2 = new Tank(new Point(300, 200), 10f);

        tv1 = new TankView(t1);
        tv2 = new TankView(t2);

        tc1 = new TankController(t1, 1);
        tc2 = new TankController(t2, 2);


        this.addKeyListener(tc1);
        this.addKeyListener(tc2);

        this.setFocusable(true); // Enable focus
        this.requestFocusInWindow(); // Request focus
        int delay = 16; // delay in milliseconds (adjust as needed for your desired frame rate)
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc1.update();
                tc2.update();

                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tv1.draw(g);
        tv2.draw(g);

    }
}
