package main.models;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Brick {
    public final static int wallWidth = 5;
    private final Point start;
    private final Point end;
    private final boolean isHorizontal;
    private final Rectangle2D rectangle;

    public Brick(Point start, Point end) {
        this.start = start;
        this.end = end;
        isHorizontal = (this.start.y == this.end.y);
        int width = end.x - start.x;
        int height = end.y - start.y;
        rectangle = new Rectangle2D.Float(wallWidth + start.x, wallWidth + start.y, width, height);
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

    public Rectangle2D getRectangle() {
        return rectangle;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isVertical() {
        return !isHorizontal;
    }
}
