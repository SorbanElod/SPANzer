package main.controllers;

import main.models.Map;

import java.util.Random;

public class MapController {
    private final Map map;

    public MapController(Map map) {
        this.map = map;
    }

    // add all possible wall segments (aka. bricks)
    public void generate() {
        map.setGenerated(false);
        map.clear();
        MazeGenerator mg = new MazeGenerator(map);
        mg.generate();
        mg.connect();
        Random rng = new Random();
        mg.randomize(rng.nextFloat(0.09f, 0.35f));
        map.setGenerated(true);
    }
}
