package main.models;

import java.awt.*;

public class Brick {
    private Point startPoint;
    private Point endPoint;
    public final static int wallWidth = 5;

    public Brick(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }

    public Brick(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}
