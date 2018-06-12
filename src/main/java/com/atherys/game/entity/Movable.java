package com.atherys.game.entity;

import com.atherys.game.cave.Cell;

import java.util.Optional;

public interface Movable extends Entity {

    default void setLocation(Location location) {
        move(location.getX() - getLocation().getX(), location.getY() - getLocation().getY());
    }

    default void move(int deltaX, int deltaY) {
        Cell moveTo = getLocation().getCave().getCell(getLocation().getX() + deltaX, getLocation().getY() + deltaY);
        if (moveTo != null && moveTo.isPassable()) {
            Optional<Entity> entity = getLocation().getCave().pollForEntity(moveTo.getLocation());
            if (entity.isPresent()) {
                entity.get().interact(this);
            } else {
                getLocation().translate(deltaX, deltaY);
            }
        }
    }

    default void moveWest() {
        move(-1, 0);
    }

    default void moveEast() {
        move(+1, 0);
    }

    default void moveNorth() {
        move(0, -1);
    }

    default void moveSouth() {
        move(0, +1);
    }

    default void moveNorthWest() {
        move(-1, -1);
    }

    default void moveNorthEast() {
        move(+1, -1);
    }

    default void moveSouthWest() {
        move(-1, +1);
    }

    default void moveSouthEast() {
        move(+1, +1);
    }

}
