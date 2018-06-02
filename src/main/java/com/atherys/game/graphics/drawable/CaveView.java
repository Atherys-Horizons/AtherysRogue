package com.atherys.game.graphics.drawable;

import com.atherys.game.cave.Cave;
import com.atherys.game.cave.Cell;
import com.atherys.game.cave.material.Materials;
import com.atherys.game.entity.Entity;
import com.atherys.game.math.Vector2i;
import com.atherys.game.player.Player;
import com.atherys.game.utils.ArrayUtils;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.function.Predicate;

public class CaveView implements Drawable {

    private int x;
    private int y;
    private int sizeX;
    private int sizeY;

    private Cave cave;
    private Player player;

    public CaveView(int x, int y, int sizeX, int sizeY, Cave cave) {
        this.x = x;
        this.y = y;
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.cave = cave;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        ArrayUtils.forEachNonNull(getCellsAroundPlayer(), (r,c,cell) -> {
            if ( player.getFov().borders(cell.getPosition()) ) System.out.println(getSlope(player.getLocation(), cell.getPosition()));
        });
    }

    @Override
    public void apply(TextGraphics surface) {
        ArrayUtils.forEach(getCellsAroundPlayer(), (row, column, cell) -> {
            if ( cell == null ) {
                surface.setCharacter(x + row, y + column, Materials.SHADOW_CHARACTER);
                return;
            }
            surface.setCharacter(x + row, y + column, cell.getMaterial().getChar());
        });

        ArrayUtils.forEachNonNull(getEntitiesAroundPlayer(), (row, column, entity) -> {
            surface.setCharacter(x + row, y + column, entity.getChar());
        });
    }

    private Cell[][] getCellsAroundPlayer() {
        return getCellsWithin(
                player.getLocation().getX() - sizeX / 2,
                player.getLocation().getY() - sizeY / 2,
                player.getLocation().getX() + sizeX / 2,
                player.getLocation().getY() + sizeY / 2,
                cell -> player.getFov().contains(cell.getPosition())
        );
    }

    private Entity[][] getEntitiesAroundPlayer() {
        return getEntitiesWithin(
                player.getLocation().getX() - sizeX / 2,
                player.getLocation().getY() - sizeY / 2,
                player.getLocation().getX() + sizeX / 2,
                player.getLocation().getY() + sizeY / 2,
                entity -> player.getFov().contains(entity.getLocation())
        );
    }

    private Cell[][] getCellsWithin(int originX, int originY, int endX, int endY, Predicate<Cell> check) {
        Cell[][] cells = new Cell[endY - originY][endX - originX];

        for (int c = 0; c < cells.length; c++) {
            for (int r = 0; r < cells[0].length; r++) {
                Cell cell = cave.getCell(originX + r, originY + c);
                if (cell != null && check.test(cell)) {
                    cells[c][r] = cell;
                }
            }
        }

        return cells;
    }

    private Entity[][] getEntitiesWithin(int originX, int originY, int endX, int endY, Predicate<Entity> check) {
        Entity[][] entities = new Entity[endY - originY][endX - originX];

        cave.getEntites().forEach(entity -> {
            int r = entity.getLocation().getX() - originX;
            int c = entity.getLocation().getY() - originY;

            if ( ( c > 0 && c < entities.length ) && ( r > 0 && r < entities[0].length ) && check.test(entity) ) entities[c][r] = entity;
        });

        return entities;
    }

    private float getSlope(Vector2i start, Vector2i stop) {
        if ( start.getY().equals( stop.getY() ) ) return 0;
        return (start.getX() - stop.getX()) / (start.getY() - stop.getY());
    }
}
