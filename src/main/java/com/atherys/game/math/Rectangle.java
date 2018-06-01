package com.atherys.game.math;

public class Rectangle {

    private Vector2<Integer> origin;
    private Vector2<Integer> size;

    public Rectangle(Vector2<Integer> origin, Vector2<Integer> size) {
        this.origin = origin;
        this.size = size;
    }

    public Rectangle(int x, int y, int w, int h) {
        this.origin = Vector2i.of(x, y);
        this.size = Vector2i.of(w, h);
    }

    public Vector2<Integer> getOrigin() {
        return origin;
    }

    public Vector2<Integer> getSize() {
        return size;
    }

    public boolean contains(Vector2<Integer> vec) {
        return ( vec.getX() > origin.getX() && vec.getX() < size.getX() ) && ( vec.getY() > origin.getY() && vec.getY() < size.getY() );
    }

    public Rectangle translate(Vector2<Integer> translation) {
        return new Rectangle(this.origin.add(translation), this.size);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "origin=" + origin +
                ", size=" + size +
                '}';
    }

    public Vector2<Integer> getCenter() {
        return origin.rem(size.div(2));
    }
}
