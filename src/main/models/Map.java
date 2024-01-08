package main.models;

import java.util.ArrayList;
import java.util.List;

public class Map {
    final int brickSize;
    final int width;
    final int height;
    private List<Brick> walls;
    private boolean isGenerated;

    public Map(int width, int height, int brickSize) {
        this.brickSize = brickSize;
        this.width = width + 1;
        this.height = height + 1;
        walls = new ArrayList<>(this.width * 2);
        isGenerated = false;
    }

    public void add(Brick brick) {
        if (!walls.contains(brick)) {
            walls.add(brick);
        }
    }

    public void remove(Brick brick) {
        walls.remove(brick);
    }

    public List<Brick> getWalls() {
        return walls;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBrickSize() {
        return brickSize;
    }

    public boolean isGenerated() {
        return isGenerated;
    }

    public void setGenerated(boolean generated) {
        isGenerated = generated;
    }

    public void clear() {
        walls = new ArrayList<>(this.width * 2);
    }
}
