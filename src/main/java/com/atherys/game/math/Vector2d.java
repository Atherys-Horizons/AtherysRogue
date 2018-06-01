package com.atherys.game.math;

public class Vector2d implements Vector2<Double> {

    protected Double x;
    protected Double y;

    public Vector2d(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2<Double> position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public static Vector2d of(Double x, Double y) {
        return new Vector2d(x, y);
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public Vector2<Double> add(Double number) {
        return of(this.x + number, this.y + number);
    }

    @Override
    public Vector2<Double> add(Vector2<Double> other) {
        return of(this.x + other.getX(), this.y + other.getY());
    }

    @Override
    public Vector2<Double> rem(Double number) {
        return of(this.x - number, this.y - number);
    }

    @Override
    public Vector2<Double> rem(Vector2<Double> other) {
        return of(this.x - other.getX(), this.y - other.getY());
    }

    @Override
    public Vector2<Double> mul(Double number) {
        return of(this.x * number, this.y * number);
    }

    @Override
    public Vector2<Double> mul(Vector2<Double> other) {
        return of(this.x * other.getX(), this.y * other.getY());
    }

    @Override
    public Vector2<Double> div(Double number) {
        return of(this.x / number, this.y / number);
    }

    @Override
    public Vector2<Double> div(Vector2<Double> other) {
        return of(this.x / other.getX(), this.y / other.getY());
    }
}
