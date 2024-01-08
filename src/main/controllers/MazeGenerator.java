package main.controllers;

import main.models.Brick;
import main.models.Map;

import java.util.*;

class MazeGenerator {

    private final int rows;
    private final int cols;
    private final Map map;
    private List<Cell> cells;
    private List<Set<Cell>> sets;
    public MazeGenerator(Map map) {
        this.map = map;
        this.cols = map.getWidth() - 1;
        this.rows = map.getHeight() - 1;
    }

    // create initial map (add all possible bricks)
    public void generate() {
        for (int i = 0; i < map.getWidth() - 1; i++) {
            for (int j = 0; j < map.getHeight() - 1; j++) {

                // add horizontal bricks
                map.add(new Brick(
                        i * map.getBrickSize(), j * map.getBrickSize(),
                        (i + 1) * map.getBrickSize(), j * map.getBrickSize()
                ));

                // add vertical bricks
                map.add(new Brick(
                        i * map.getBrickSize(), j * map.getBrickSize(),
                        i * map.getBrickSize(), (j + 1) * map.getBrickSize()
                ));
            }
        }
    }

    // connect the map
    public void connect() {
        this.cells = new ArrayList<>();
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                cells.add(new Cell(x, y));
            }
        }

        this.sets = new ArrayList<>();
        for (Cell cell : cells) {
            Set<Cell> set = new HashSet<>();
            set.add(cell);
            sets.add(set);
        }
        int cellNum = rows * cols;
        Collections.shuffle(map.getWalls());

        List<Brick> bricksToRemove = new ArrayList<>();
        for (Brick b : map.getWalls()) {
            int startX = Math.min(b.getStart().x, b.getEnd().x) / map.getBrickSize();
            int startY = Math.min(b.getStart().y, b.getEnd().y) / map.getBrickSize();
            if ((startX == 0 && b.isVertical()) || (startY == 0) && b.isHorizontal()) {
                continue;
            }

            Cell baseCell = findCell(startX, startY);
            Set<Cell> baseSet = findSet(baseCell);
            Set<Cell> neighborSet;
            if (b.isVertical()) {
                neighborSet = findSet(findCell(startX - 1, startY));
                if (baseSet != neighborSet) {
                    mergeSets(baseSet, neighborSet);
                    bricksToRemove.add(b);
                    cellNum--;
                }
            }
            if (b.isHorizontal()) {
                neighborSet = findSet(new Cell(startX, startY - 1));
                if (baseSet != neighborSet) {
                    mergeSets(baseSet, neighborSet);
                    bricksToRemove.add(b);
                    cellNum--;
                }
            }

            if (cellNum == 1) {
                break;
            }
        }
        for (Brick brick : bricksToRemove) {
            map.remove(brick);
        }
    }

    private Cell findCell(int x, int y) {
        for (Cell c : cells) {
            if (c.x == x && c.y == y) {
                return c;
            }
        }
        return null;
    }

    private Set<Cell> findSet(Cell cell) {
        for (Set<Cell> set : sets) {
            for (Cell c : set) {
                if (c.x == cell.x && c.y == cell.y) {
                    return set;
                }
            }
        }
        return null;
    }

    private void mergeSets(Set<Cell> set1, Set<Cell> set2) {
        sets.remove(set1);
        sets.remove(set2);
        Set<Cell> mergedSet = new HashSet<>(set1);
        mergedSet.addAll(set2);
        sets.add(mergedSet);
    }

    // add frame (during randomization outer wall can disappear)
    private void addFrame() {
        for (int i = 0; i < map.getHeight() - 1; i++) {
            map.add(new Brick(
                    0, i * map.getBrickSize(),
                    0, (i + 1) * map.getBrickSize()
            ));
            map.add(new Brick(
                    (map.getWidth() - 1) * map.getBrickSize(), i * map.getBrickSize(),
                    (map.getWidth() - 1) * map.getBrickSize(), (i + 1) * map.getBrickSize()
            ));
        }
        for (int i = 0; i < map.getWidth() - 1; i++) {
            map.add(new Brick(
                    i * map.getBrickSize(), 0,
                    (i + 1) * map.getBrickSize(), 0
            ));

            map.add(new Brick(
                    i * map.getBrickSize(), (map.getHeight() - 1) * map.getBrickSize(),
                    (i + 1) * map.getBrickSize(), (map.getHeight() - 1) * map.getBrickSize()
            ));
        }
    }

    private Brick getRandomBrick() {
        List<Brick> walls = map.getWalls();
        Random random = new Random();
        if (!walls.isEmpty()) {
            int index = random.nextInt(walls.size());
            return walls.get(index);
        }
        return null;
    }

    //remove a random wall
    private void removeRandomWall() {
        map.remove(getRandomBrick());
    }

    // remove a percentage of walls
    public void randomize(float ratio) {
        int demolish = (int) (map.getWalls().size() * ratio);
        for (int i = 0; i < demolish; i++) {
            removeRandomWall();
        }
        addFrame();
    }

    private static class Cell {
        private final int x;
        private final int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
