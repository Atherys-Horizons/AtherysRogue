package com.atherys.game.cave;

import com.atherys.game.cave.material.FloorMaterial;
import com.atherys.game.cave.material.WallMaterial;
import com.atherys.game.entity.Entity;
import com.atherys.game.entity.GroundItem;
import com.atherys.game.entity.Location;
import com.atherys.game.item.ItemGenerator;
import com.atherys.game.math.Vector2i;
import com.atherys.game.utils.ArrayUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class CaveGenerator {

    private final static double ITEM_GENERATION_CHANCE = 0.008d;

    private Cave cave;

    private Random random;
    private MaterialDistribution distribution;

    private int wallThreshhold;
    private int floorThreshhold;

    /**
     * A boolean 2D array representing where there ought and ought not to be Passable cells.
     */
    private Boolean[][] wallMap;

    private Cell[][] map;
    private Set<Entity> entities = new HashSet<>();

    public CaveGenerator(int seed, MaterialDistribution distribution, Vector2i caveSize, int wallThreshhold, int floorThreshhold, int iterations) {
        this.random = new Random(seed);
        this.map = new Cell[caveSize.getY()][caveSize.getX()];
        this.distribution = distribution;
        this.wallThreshhold = wallThreshhold;
        this.floorThreshhold = floorThreshhold;

        this.cave = new Cave();
        generateWallMap(caveSize.getX(), caveSize.getY(), iterations);
        generateCells();
        generateItems();
    }

    private void generateItems() {
        ArrayUtils.forEach(wallMap, (r, c, v) -> {
            if (v) return;

            if (Math.random() < ITEM_GENERATION_CHANCE) {
                entities.add(new GroundItem(ItemGenerator.getRandom(4), Location.of(cave, c, r)));
            }
        });
    }

    private void generateWallMap(int sizeX, int sizeY, int iterations) {
        wallMap = new Boolean[sizeX][sizeY];

        ArrayUtils.forEach(wallMap, (r, c, v) -> {
            wallMap[c][r] = random.nextBoolean();
        });

        refineWallMap(iterations);
    }

    private void refineWallMap(int iterations) {
        if (iterations == 0) return;

        ArrayUtils.forEach(wallMap, (r, c, wall) -> {
            if (isOnBorder(r, c)) {
                wallMap[c][r] = true;
                return;
            }

            if (!wall && getAdjacentWalls(c, r) >= wallThreshhold) wallMap[c][r] = true;
            else if (wall && getAdjacentWalls(c, r) < floorThreshhold) wallMap[c][r] = false;
        });

        refineWallMap(iterations - 1);
    }

    private int getAdjacentWalls(int c, int r) {
        int counter = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0) && !isOutOfBounds(r + j, c + i) && wallMap[c + i][r + j])
                    counter++;
            }
        }

        return counter;
    }

    private void forEachInWallMap(boolean walls, BiConsumer<Integer, Integer> consumer) {
        ArrayUtils.forEach(wallMap, (r, c, v) -> {
            if (v == walls) consumer.accept(r, c);
        });
    }

    private void forEachWall(BiConsumer<Integer, Integer> consumer) {
        forEachInWallMap(true, consumer);
    }

    private void forEachNonWall(BiConsumer<Integer, Integer> consumer) {
        forEachInWallMap(false, consumer);
    }

    private void generateCells() {
        forEachWall((r, c) -> {
            map[r][c] = new Cell(
                    new Location(cave, c, r),
                    distribution.next(WallMaterial.class)
            );
        });

        forEachNonWall((r, c) -> {
            map[r][c] = new Cell(
                    new Location(cave, c, r),
                    distribution.next(FloorMaterial.class)
            );
        });
    }

    private boolean isOnBorder(int r, int c) {
        return c == 0 || c == wallMap.length - 1 || r == 0 || r == wallMap[0].length - 1;
    }

    private boolean isOutOfBounds(int r, int c) {
        return c < 0 || c == wallMap.length - 1 || r < 0 || r == wallMap[0].length - 1;
    }

    public Cave getCave() {
        cave.setMap(map);
        cave.spawnEntities(entities);
        return cave;
    }

}
