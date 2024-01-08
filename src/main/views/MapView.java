package main.views;

import main.models.Brick;
import main.models.Map;

import java.awt.*;

import static main.models.Brick.wallWidth;

public class MapView {
    private final Map map;

    public MapView(Map map) {
        this.map = map;
    }

    public void drawBrick(Brick b, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(wallWidth));
        g2d.drawLine(
                wallWidth + b.getStart().x,
                wallWidth + b.getStart().y,
                wallWidth + b.getEnd().x,
                wallWidth + b.getEnd().y);
    }

    public void draw(Graphics g) {
        if (map.isGenerated()) {
            for (Brick b : map.getWalls()) {
                if (b != null) {
                    drawBrick(b, g);
                }
            }
        }
    }

    public Dimension getMapDimension() {
        return new Dimension(
                (map.getWidth() - 1) * map.getBrickSize() + 2 * wallWidth,
                (map.getHeight() - 1) * map.getBrickSize() + 2 * wallWidth
        );
    }

}
