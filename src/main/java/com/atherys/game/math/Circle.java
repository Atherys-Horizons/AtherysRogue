package com.atherys.game.math;

public class Circle {

    private Vector2i center;
    private int radius;

    public Circle(Vector2i center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Vector2i getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public boolean contains(Vector2i position) {
        return (position.getX() - center.getX()) * (position.getX() - center.getX()) + (position.getY() - center.getY()) * (position.getY() - center.getY()) < radius * radius;
    }

    public boolean borders(Vector2i position) {
        return (position.getX() - center.getX()) * (position.getX() - center.getX()) + (position.getY() - center.getY()) * (position.getY() - center.getY()) == radius * radius;
    }
}
