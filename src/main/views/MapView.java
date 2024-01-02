package main.views;

import main.models.Map;
import main.models.Brick;

import java.awt.*;

import static main.models.Brick.wallWidth;

public class MapView {
    private Map map;

    public MapView(Map map) {
        this.map = map;
    }

    public void drawBrick(Brick b, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(wallWidth));
        g2d.drawLine(
                wallWidth + b.getStartPoint().x,
                wallWidth + b.getStartPoint().y,
                wallWidth + b.getEndPoint().x,
                wallWidth + b.getEndPoint().y);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Brick brick;
                brick = map.getHorizontal(i, j);
                if (brick != null) {
                    drawBrick(brick, g);
                }

                brick = map.getVertical(i, j);
                if (brick != null) {
                    drawBrick(brick, g);
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
