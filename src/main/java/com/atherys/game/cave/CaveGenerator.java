package com.atherys.game.cave;

import com.atherys.game.cave.material.FloorMaterial;
import com.atherys.game.cave.material.Material;
import com.atherys.game.cave.material.Materials;
import com.atherys.game.cave.material.WallMaterial;
import com.atherys.game.math.Vector2i;
import com.atherys.game.utils.ArrayUtils;

import java.util.Random;
import java.util.function.Predicate;

public class CaveGenerator {

    private Random random;
    private MaterialDistribution distribution;
    private int iterations;

    private int wallThreshhold;
    private int floorThreshhold;

    private Cell[][] map;

    public CaveGenerator(MaterialDistribution distribution, Vector2i caveSize, int wallThreshhold, int floorThreshhold, int iterations) {
        this.random = new Random();
        this.map = new Cell[caveSize.getY()][caveSize.getX()];
        this.distribution = distribution;
        this.wallThreshhold = wallThreshhold;
        this.floorThreshhold = floorThreshhold;
        this.iterations = iterations;
    }

    public CaveGenerator(int seed, MaterialDistribution distribution, Vector2i caveSize, int wallThreshhold, int floorThreshhold, int iterations) {
        this.random = new Random(seed);
        this.map = new Cell[caveSize.getY()][caveSize.getX()];
        this.distribution = distribution;
        this.wallThreshhold = wallThreshhold;
        this.floorThreshhold = floorThreshhold;
        this.iterations = iterations;
    }

    private void randomize() {
        ArrayUtils.forEach(map, (r, c, cell) -> map[c][r] = new Cell(Vector2i.of(r, c), getRandomMaterial(c, r)));
    }

    private void refine(int iteration) {
        if (iteration == 0) return;

        ArrayUtils.forEachNonNull(map, (row, column, cell) -> {
            if (column == 0 || column == map.length - 1 || row == 0 || row == map[0].length - 1) return;

            if (cell.getMaterial() instanceof FloorMaterial && getAdjacentCells(row, column, c -> !c.isPassable()) >= wallThreshhold) {
                cell.setMaterial(distribution.getRandomMaterial(material -> material instanceof WallMaterial));
            } else if (cell.getMaterial() instanceof WallMaterial && getAdjacentCells(row, column, c -> !c.isPassable()) < floorThreshhold) {
                cell.setMaterial(distribution.getRandomMaterial(material -> material instanceof FloorMaterial));
            }
        });

        refine(iteration - 1);
    }

    private int getAdjacentCells(int x, int y, Predicate<Cell> check) {
        int counter = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    Cell cell = getCell(x + j, y + i);
                    if (cell != null && check.test(cell)) counter++;
                }
            }
        }

        return counter;
    }


    private Material getRandomMaterial(int x, int y) {
        if (x == 0 || x == map.length - 1 || y == 0 || y == map[0].length - 1) return Materials.STONE_WALL;
        return distribution.getRandomMaterial(random);
    }

    private boolean isValidCell(int x, int y) {
        return (x >= 0 && y >= 0) && y < map.length && x < map[0].length;
    }

    private Cell getCell(int x, int y) {
        return isValidCell(x, y) ? map[y][x] : null;
    }

    public Cave getCave() {
        randomize();
        refine(iterations);
        return new Cave(map);
    }

}
