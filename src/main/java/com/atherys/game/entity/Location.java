package com.atherys.game.entity;

import com.atherys.game.cave.Cave;
import com.atherys.game.math.Vector2i;
import com.googlecode.lanterna.TerminalPosition;

public class Location extends Vector2i {

    private Cave cave;

    public Location(Cave cave, Vector2i position) {
        super(position);
        this.cave = cave;
    }

    public Location(Cave cave, Integer x, Integer y) {
        super(x, y);
        this.cave = cave;
    }

    public static Location of(Cave cave, Integer x, Integer y) {
        return new Location(cave, x, y);
    }

    public void translate(Integer deltaX, Integer deltaY) {
        super.x += deltaX;
        super.y += deltaY;
    }

    public Cave getCave() {
        return cave;
    }

    public TerminalPosition asTerminalPosition() {
        return new TerminalPosition(x, y);
    }
}
