package com.atherys.game.entity;

import com.atherys.game.cave.Cave;
import com.atherys.game.math.Vector2i;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(cave, location.cave) && x.equals(location.x) && y.equals(location.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cave, x, y);
    }
}
