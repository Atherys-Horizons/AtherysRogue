package com.atherys.game.math;

public class Vector2i extends Vector2<Integer> {
    public Vector2i(Integer x, Integer y) {
        super(x, y);
    }

    public Vector2i(Vector2<Integer> position) {
        super(position);
    }

    public static Vector2i of(Integer x, Integer y) {
        return new Vector2i(x, y);
    }

    @Override
    public Vector2<Integer> add(Integer number) {
        return of(this.x + number, this.y + number);
    }

    @Override
    public Vector2<Integer> add(Vector2<Integer> other) {
        return of(this.x + other.x, this.y + other.y);
    }

    @Override
    public Vector2<Integer> rem(Integer number) {
        return of(this.x - number, this.y - number);
    }

    @Override
    public Vector2<Integer> rem(Vector2<Integer> other) {
        return of(this.x - other.x, this.y - other.y);
    }

    @Override
    public Vector2<Integer> mul(Integer number) {
        return of(this.x * number, this.y * number);
    }

    @Override
    public Vector2<Integer> mul(Vector2<Integer> other) {
        return of(this.x * other.x, this.y * other.y);
    }

    @Override
    public Vector2<Integer> div(Integer number) {
        return of(this.x / number, this.y / number);
    }

    @Override
    public Vector2<Integer> div(Vector2<Integer> other) {
        return of(this.x / other.x, this.y / other.y);
    }
}
