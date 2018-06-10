package com.atherys.game.entity;

public interface Living extends Movable {

    double getHealth();

    void setHealth(double amount);

    default void addHealth(double amount) {
        setHealth(getHealth() + amount);
    }

    default void removeHealth(double amount) {
        setHealth(getHealth() - amount);
    }

    double getMaxHealth();

}
