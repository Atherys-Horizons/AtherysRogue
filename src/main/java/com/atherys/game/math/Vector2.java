package com.atherys.game.math;

public abstract class Vector2<T extends Number> {

    protected T x;
    protected T y;

    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2<T> position) {
        this.x = position.x;
        this.y = position.y;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public abstract Vector2<T> add(T number);

    public abstract Vector2<T> add(Vector2<T> other);

    public abstract Vector2<T> rem(T number);

    public abstract Vector2<T> rem(Vector2<T> other);

    public abstract Vector2<T> mul(T number);

    public abstract Vector2<T> mul(Vector2<T> other);

    public abstract Vector2<T> div(T number);

    public abstract Vector2<T> div(Vector2<T> other);
}
