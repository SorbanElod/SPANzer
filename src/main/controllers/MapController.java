package main.controllers;

import main.models.Brick;
import main.models.Map;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapController {
    private Map map;

    public MapController(Map map) {
        this.map = map;
    }

    public void generate() {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Brick brick;
                brick = new Brick(
                        i * map.getBrickSize(), j * map.getBrickSize(),
                        (i + 1) * map.getBrickSize(), j * map.getBrickSize()
                );
                map.setHorizontal(i, j, brick);
                brick = new Brick(
                        i * map.getBrickSize(), j * map.getBrickSize(),
                        i * map.getBrickSize(), (j + 1) * map.getBrickSize()
                );
                map.setVertical(i, j, brick);
            }
        }

        this.trim();
        this.randomize();
        this.addFrame();
    }

    private void addFrame() {
        for (int i = 0; i < map.getHeight() - 1; i++) {
            map.setVertical(i, 0, new Brick(
                    0, i * map.getBrickSize(),
                    0, (i + 1) * map.getBrickSize()
            ));
            map.setVertical(i, map.getHeight() - 1, new Brick(
                    (map.getWidth() - 1) * map.getBrickSize(), i * map.getBrickSize(),
                    (map.getWidth() - 1) * map.getBrickSize(), (i + 1) * map.getBrickSize()
            ));
        }
        for (int i = 0; i < map.getWidth() - 1; i++) {
            map.setHorizontal(i, 0, new Brick(
                    i * map.getBrickSize(), 0,
                    (i + 1) * map.getBrickSize(), 0
            ));

            map.setHorizontal(i, map.getHeight() - 1, new Brick(
                    i * map.getBrickSize(), (map.getHeight() - 1) * map.getBrickSize(),
                    (i + 1) * map.getBrickSize(), (map.getHeight() - 1) * map.getBrickSize()
            ));
        }
    }

    private void trim() {
        for (int i = 0; i < map.getWidth(); i++) {
            map.setVertical(i, map.getHeight() - 1, null);
        }
        for (int i = 0; i < map.getHeight(); i++) {
            map.setHorizontal(map.getWidth() - 1, i, null);
        }
    }

    private List<Brick> allWalls() {
        return Stream.concat(
                        map.getHorizontals().stream()
                                .flatMap(List::stream)
                                .filter(Objects::nonNull),
                        map.getVerticals().stream()
                                .flatMap(List::stream)
                                .filter(Objects::nonNull)
                )
                .collect(Collectors.toList());
    }

    private void removeRandomWall(List<Brick> tmp) {
        Random random = new Random();
        if (!tmp.isEmpty()) {
            int index = random.nextInt(tmp.size());
            Brick br = tmp.get(index);
            setToNullInOriginalPlace(map.getHorizontals(), br);
            setToNullInOriginalPlace(map.getVerticals(), br);

            tmp.remove(br);
        }
    }

    private static void setToNullInOriginalPlace(List<List<Brick>> lists, Brick brick) {
        lists.stream()
                .filter(list -> list.contains(brick))
                .findFirst()
                .ifPresent(list -> list.set(list.indexOf(brick), null));
    }

    private void randomize() {
        List<Brick> wallList = allWalls();
        final int demolish = 50;
        for (int i = 0; i < demolish; i++) {
            removeRandomWall(wallList);
        }
    }
}



