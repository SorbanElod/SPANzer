package main.controllers;

import main.models.Map;

import java.util.Random;

public class MapController {
    private Map map;

    public MapController(Map map) {
        this.map = map;
    }

    // add all possible wall segments (aka. bricks)
    public void generate() {
        map.setGenerated(false);
        MazeGenerator mg = new MazeGenerator(map);
        mg.generate();
        mg.connect();
        Random rng = new Random();
        mg.randomize(rng.nextFloat(0.0f,0.4f));
        map.setGenerated(true);
    }
}
