package com.atherys.game.math;

public interface Vector2<T> {

    T getX();

    T getY();

    Vector2<T> add(T number);

    Vector2<T> add(Vector2<T> other);

    Vector2<T> rem(T number);

    Vector2<T> rem(Vector2<T> other);

    Vector2<T> mul(T number);

    Vector2<T> mul(Vector2<T> other);

    Vector2<T> div(T number);

    Vector2<T> div(Vector2<T> other);

}
