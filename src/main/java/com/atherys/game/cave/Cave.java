package com.atherys.game.cave;

import com.atherys.game.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Cave {

    private Cell[][] map;

    private List<Entity> entities = new ArrayList<>();

    protected Cave() {}

    public Cave(Cell[][] map) {
        this.map = map;
    }

    public boolean isValidCell(int x, int y) {
        return (x >= 0 && y >= 0) && y < map.length && x < map[0].length;
    }

    public Cell getCell(int x, int y) {
        return isValidCell(x, y) ? map[y][x] : null;
    }

    public Cell[][] getMap() {
        return map;
    }

    protected void setMap(Cell[][] map) {
        this.map = map;
    }

    public List<Entity> getEntites() {
        return entities;
    }

    public void spawnEntity(Entity entity) {
        this.entities.add(entity);
    }
}
