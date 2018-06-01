package com.atherys.game.base;

public interface Movable {

    void move(int deltaX, int deltaY);

    default void moveLeft() {
        move(-1, 0);
    }

    default void moveRight() {
        move(+1, 0);
    }

    default void moveUp() {
        move(0, -1);
    }

    default void moveDown() {
        move(0, +1);
    }

}
