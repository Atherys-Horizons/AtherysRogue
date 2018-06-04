package com.atherys.game.graphics.drawable;

import com.atherys.game.utils.ArrayUtils;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Table extends Box {

    public static final char NORTH_TRIPOINT = '╦';
    public static final char SOUTH_TRIPOINT = '╩';
    public static final char WEST_TRIPOINT = '╠';
    public static final char EAST_TRIPOINT = '╣';
    public static final char QUADPOINT = '╬';

    private int cellWidth;
    private int cellHeight;

    private int rows;
    private int columns;

    public Table(int x, int y, int cellWidth, int cellHeight, int rows, int columns) {
        super(x, y, cellWidth * columns, cellHeight * rows);
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void apply(TextGraphics surface) {
        ArrayUtils.iterate(x, x + w + 1, cellWidth, y, y + h + 1, 1, (i, j) -> {

            if (i == x && j == y) {
                surface.setCharacter(i, j, Box.NW_CORNER);
                return;
            } else if (i == x && j == y + h) {
                surface.setCharacter(i, j, Box.SW_CORNER);
                return;
            } else if (i == x + w && j == y + h) {
                surface.setCharacter(i, j, Box.SE_CORNER);
                return;
            } else if (i == x + w && j == y) {
                surface.setCharacter(i, j, Box.NE_CORNER);
                return;
            } else if (j == y && (j - y) % cellHeight == 0) {
                surface.setCharacter(i, j, NORTH_TRIPOINT);
                return;
            } else if (j == y + h && (j - y) % cellHeight == 0) {
                surface.setCharacter(i, j, SOUTH_TRIPOINT);
                return;
            } else if (i == x && (j - y) % cellHeight == 0) {
                surface.setCharacter(i, j, WEST_TRIPOINT);
                return;
            } else if (i == x + w && (j - y) % cellHeight == 0) {
                surface.setCharacter(i, j, EAST_TRIPOINT);
                return;
            } else if ((j - y) % cellHeight != 0) {
                surface.setCharacter(i, j, Box.VERTICAL_BORDER);
                return;
            }

            surface.setCharacter(i, j, QUADPOINT);
        });

        ArrayUtils.iterate(x, x + w + 1, 1, y, y + h + 1, cellHeight, (i, j) -> {
            if ((i - x) % cellWidth != 0) {
                surface.setCharacter(i, j, Box.HORIZONTAL_BORDER);
            }
        });
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
