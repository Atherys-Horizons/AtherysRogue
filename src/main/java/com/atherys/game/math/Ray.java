package com.atherys.game.math;

import java.util.function.BiConsumer;

public class Ray {

    private Vector2i start;
    private Vector2i stop;

    public Ray (Vector2i start, Vector2i stop) {
        this.start = start;
        this.stop = stop;
    }

    public Vector2i getStop() {
        return stop;
    }

    public Vector2i getStart() {
        return start;
    }

    public void forEach(BiConsumer<Integer,Integer> consumer) {
        //TODO
    }
}
