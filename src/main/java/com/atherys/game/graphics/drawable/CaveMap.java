package com.atherys.game.graphics.drawable;

import com.atherys.game.cave.Cave;
import com.atherys.game.entity.Player;
import com.atherys.game.utils.ArrayUtils;
import com.googlecode.lanterna.graphics.TextGraphics;

public class CaveMap implements Drawable {

    private int x;
    private int y;

    private Cave cave;
    private Player player;

    public CaveMap(int x, int y, Cave cave) {
        this.x = x;
        this.y = y;
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
        ArrayUtils.forEachNonNull(cave.getMap(), (row, column, cell) -> surface.setCharacter(x + row, y + column, cell.getMaterial().getChar()));
        cave.getEntites().forEach(entity -> surface.setCharacter(entity.getLocation().asTerminalPosition(), entity.getChar()));
    }
}
