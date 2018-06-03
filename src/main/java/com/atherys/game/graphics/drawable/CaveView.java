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

public class CaveView extends TitleBox {

    private Cave cave;
    private Player player;

    public CaveView(int x, int y, int w, int h, Cave cave) {
        super("View", x, y, w, h);
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
        super.apply(surface);

        long before = System.currentTimeMillis();

        ArrayUtils.forEach(getCellsAroundPlayer(), (row, column, cell) -> {
            if ( cell == null ) {
                surface.setCharacter(x + 1 + row, y + 1 + column, Materials.SHADOW_CHARACTER);
                return;
            }
            surface.setCharacter(x + 1 + row, y + 1 + column, cell.getMaterial().getChar());
        });

        ArrayUtils.forEachNonNull(getEntitiesAroundPlayer(), (row, column, entity) -> {
            surface.setCharacter(x + 1 + row, y + 1 + column, entity.getChar());
        });

        long after = System.currentTimeMillis();

        System.out.println("Drawing cave took " + (after-before) + "ms.");

    }

    private Cell[][] getCellsAroundPlayer() {
        Cell playerCell = cave.getCell(player.getLocation().getX(), player.getLocation().getY());

        return getCellsWithin(
                player.getLocation().getX() - w / 2,
                player.getLocation().getY() - h / 2,
                player.getLocation().getX() + w / 2,
                player.getLocation().getY() + h / 2,
                cell -> {
                    if ( !player.getFov().contains(cell.getPosition()) ) return false;
                    return cell.isVisibleFrom(cave, playerCell);
                }
        );
    }

    private Entity[][] getEntitiesAroundPlayer() {
        return getEntitiesWithin(
                player.getLocation().getX() - w / 2,
                player.getLocation().getY() - h / 2,
                player.getLocation().getX() + w / 2,
                player.getLocation().getY() + h / 2,
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
