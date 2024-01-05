package main.models;

import java.awt.*;

public class Brick {
    private Point start;
    private Point end;
    private boolean isHorizontal;
    public final static int wallWidth = 5;

    public Brick(Point start, Point end) {
        this.start = start;
        this.end = end;
        isHorizontal = (this.start.y == this.end.y);
    }

    public Brick(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isVertical() {
        return !isHorizontal;
    }
}
