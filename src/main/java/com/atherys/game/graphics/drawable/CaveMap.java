package com.atherys.game.graphics.drawable;

import com.atherys.game.cave.Cave;
import com.atherys.game.utils.ArrayUtils;
import com.googlecode.lanterna.graphics.TextGraphics;

public class CaveMap implements Drawable {

    private int x;
    private int y;

    private Cave cave;

    public CaveMap(int x, int y, Cave cave) {
        this.x = x;
        this.y = y;
        this.cave = cave;
    }

    @Override
    public void apply(TextGraphics surface) {
        ArrayUtils.forEachNonNull(cave.getMap(), (row, column, cell) -> {
            surface.setCharacter(x + row, y + column, cell.getMaterial().getChar());
        });

        cave.getEntites().forEach(entity -> entity.getDrawable().apply(surface));
    }
}
