package com.atherys.game.math;

public class Vector2i implements Vector2<Integer> {

    protected Integer x;
    protected Integer y;

    public Vector2i(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2<Integer> position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public static Vector2i of(Integer x, Integer y) {
        return new Vector2i(x, y);
    }

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public Vector2<Integer> add(Integer number) {
        return of(this.x + number, this.y + number);
    }

    @Override
    public Vector2<Integer> add(Vector2<Integer> other) {
        return of(this.x + other.getX(), this.y + other.getY());
    }

    @Override
    public Vector2<Integer> rem(Integer number) {
        return of(this.x - number, this.y - number);
    }

    @Override
    public Vector2<Integer> rem(Vector2<Integer> other) {
        return of(this.x - other.getX(), this.y - other.getY());
    }

    @Override
    public Vector2<Integer> mul(Integer number) {
        return of(this.x * number, this.y * number);
    }

    @Override
    public Vector2<Integer> mul(Vector2<Integer> other) {
        return of(this.x * other.getX(), this.y * other.getY());
    }

    @Override
    public Vector2<Integer> div(Integer number) {
        return of(this.x / number, this.y / number);
    }

    @Override
    public Vector2<Integer> div(Vector2<Integer> other) {
        return of(this.x / other.getX(), this.y / other.getY());
    }

    @Override
    public String toString() {
        return "Vector2i{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
