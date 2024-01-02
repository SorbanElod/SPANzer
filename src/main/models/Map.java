package main.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map {
    private List<List<Brick>> horizontals;
    private List<List<Brick>> verticals;

    int brickSize;
    int width;
    int height;

    public Map(int width, int height, int brickSize) {
        this.brickSize = brickSize;
        this.width = width + 1;
        this.height = height + 1;
        horizontals = new ArrayList<>(this.width);
        verticals = new ArrayList<>(this.width);

        for (int i = 0; i < this.width; i++) {
            horizontals.add(new ArrayList<>(Collections.nCopies(this.height, null)));
            verticals.add(new ArrayList<>(Collections.nCopies(this.height, null)));
        }
    }

    public Brick getHorizontal(int row, int col) {
        return horizontals.get(row).get(col);
    }

    public void setHorizontal(int row, int col, Brick brick) {
        horizontals.get(row).set(col, brick);
    }

    public Brick getVertical(int row, int col) {
        return verticals.get(row).get(col);
    }

    public void setVertical(int row, int col, Brick brick) {
        verticals.get(row).set(col, brick);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBrickSize() { return brickSize; }

    public List<List<Brick>> getHorizontals() {
        return horizontals;
    }

    public void setHorizontals(List<List<Brick>> horizontals) {
        this.horizontals = horizontals;
    }

    public List<List<Brick>> getVerticals() {
        return verticals;
    }

    public void setVerticals(List<List<Brick>> verticals) {
        this.verticals = verticals;
    }
}
