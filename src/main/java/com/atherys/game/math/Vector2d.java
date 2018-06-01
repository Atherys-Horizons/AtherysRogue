package com.atherys.game.math;

public class Vector2d extends Vector2<Double> {
    public Vector2d(Double x, Double y) {
        super(x, y);
    }

    public Vector2d(Vector2<Double> position) {
        super(position);
    }

    public static Vector2d of(Double x, Double y) {
        return new Vector2d(x, y);
    }

    @Override
    public Vector2<Double> add(Double number) {
        return null;
    }

    @Override
    public Vector2<Double> add(Vector2<Double> other) {
        return null;
    }

    @Override
    public Vector2<Double> rem(Double number) {
        return null;
    }

    @Override
    public Vector2<Double> rem(Vector2<Double> other) {
        return null;
    }

    @Override
    public Vector2<Double> mul(Double number) {
        return null;
    }

    @Override
    public Vector2<Double> mul(Vector2<Double> other) {
        return null;
    }

    @Override
    public Vector2<Double> div(Double number) {
        return null;
    }

    @Override
    public Vector2<Double> div(Vector2<Double> other) {
        return null;
    }
}
