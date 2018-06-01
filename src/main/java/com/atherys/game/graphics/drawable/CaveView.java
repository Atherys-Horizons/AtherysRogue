package com.atherys.game.graphics.drawable;

import com.atherys.game.cave.Cave;
import com.atherys.game.cave.Cell;
import com.atherys.game.entity.Entity;
import com.atherys.game.player.Player;
import com.atherys.game.utils.ArrayUtils;
import com.googlecode.lanterna.graphics.TextGraphics;

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
    }

    @Override
    public void apply(TextGraphics surface) {
        ArrayUtils.forEachNonNull(getCellsAroundPlayer(), (row, column, cell) -> {
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
                player.getLocation().getY() + sizeY / 2
        );
    }

    private Entity[][] getEntitiesAroundPlayer() {
        return getEntitiesWithin(
                player.getLocation().getX() - sizeX / 2,
                player.getLocation().getY() - sizeY / 2,
                player.getLocation().getX() + sizeX / 2,
                player.getLocation().getY() + sizeY / 2
        );
    }

    private Cell[][] getCellsWithin(int originX, int originY, int endX, int endY) {
        Cell[][] cells = new Cell[endY - originY][endX - originX];

        for (int c = 0; c < cells.length; c++) {
            for (int r = 0; r < cells[0].length; r++) {
                Cell cell = cave.getCell(originX + r, originY + c);
                if (cell != null) cells[c][r] = cell;
            }
        }

        return cells;
    }

    private Entity[][] getEntitiesWithin(int originX, int originY, int endX, int endY) {
        Entity[][] entities = new Entity[endY - originY][endX - originX];

        cave.getEntites().forEach(entity -> {
                int r = entity.getLocation().getX() - originX;
                int c = entity.getLocation().getY() - originY;

                if ( ( c > 0 && c < entities.length ) && ( r > 0 && r < entities[0].length ) ) entities[c][r] = entity;
        });

        return entities;
    }
}
