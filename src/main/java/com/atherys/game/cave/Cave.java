package com.atherys.game.cave;

import com.atherys.game.cave.material.FloorMaterial;
import com.atherys.game.cave.material.Material;
import com.atherys.game.cave.material.Materials;
import com.atherys.game.cave.material.WallMaterial;
import com.atherys.game.entity.Entity;
import com.atherys.game.math.RandomUtils;
import com.atherys.game.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class Cave {

    private static int ALIVE_THRESHHOLD = 5;
    private static int SURVIVAL_THRESHHOLD = 4;

    private Cell[][] map;

    private List<Entity> entities = new ArrayList<>();

    public Cave(int sizeX, int sizeY, double wallPercentage, int iterations) {
        this.map = new Cell[sizeY][sizeX];
        ArrayUtils.forEach(map, (row, column, cell) -> {
            Material material;
            if (column == 0 || column == sizeY - 1 || row == 0 || row == sizeX - 1) material = Materials.STONE_WALL;
            else material = getRandomMaterial(wallPercentage);
            map[column][row] = new Cell(material);
        });

        generate(iterations);
    }

    private void generate(int iteration) {
        if (iteration == 0) return;

        ArrayUtils.forEachNonNull(map, (row, column, cell) -> {
            if (column == 0 || column == map.length - 1 || row == 0 || row == map[0].length - 1) return;

            if (cell.getMaterial().equals(Materials.WATER) && getAdjacentWalls(row, column, Materials.WATER) >= ALIVE_THRESHHOLD)
                cell.setMaterial(Materials.WATER);
            else if (cell.getMaterial() instanceof FloorMaterial && getAdjacentWalls(row, column, Materials.STONE_WALL) >= ALIVE_THRESHHOLD)
                cell.setMaterial(Materials.STONE_WALL);
            else if (cell.getMaterial() instanceof WallMaterial && getAdjacentWalls(row, column, Materials.STONE_WALL) < SURVIVAL_THRESHHOLD)
                cell.setMaterial(Materials.STONE_FLOOR);
        });

        generate(iteration - 1);
    }

    private int getAdjacentWalls(int x, int y, Material material) {
        int counter = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    Cell cell = getCell(x + j, y + i);
                    if (cell != null && cell.getMaterial().equals(material)) counter++;
                }
            }
        }

        return counter;
    }

    private Material getRandomMaterial(double percentStone) {
        if (percentStone >= Math.random()) return Materials.STONE_WALL;
        return Materials.getFloorMaterials()[RandomUtils.between(0, Materials.getFloorMaterials().length)];
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

    public List<Entity> getEntites() {
        return entities;
    }

    public void spawnEntity(Entity entity) {
        this.entities.add(entity);
    }
}
