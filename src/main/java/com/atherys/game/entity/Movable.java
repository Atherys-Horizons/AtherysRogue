package com.atherys.game.entity;

public interface Movable {

    void move(int deltaX, int deltaY);

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
