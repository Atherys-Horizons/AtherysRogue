package com.atherys.game.math;

import java.util.function.BiConsumer;

public class Ray {

    private int startX, startY;
    private int endX, endY;

    public Ray(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public static void of(int originX, int originY, int endX, int endY, BiConsumer<Integer, Integer> consumer) {
        new Ray(originX, originY, endX, endY).forEach(consumer);
    }

    public void forEach(BiConsumer<Integer, Integer> consumer) {
        int x2 = endX;
        int x1 = startX;
        int y2 = endY;
        int y1 = startY;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int err = dx - dy;

        while (true) {
            consumer.accept(x1, y1);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * err;

            if (e2 > -dy) {
                err = err - dy;
                x1 = x1 + sx;
            }

            if (e2 < dx) {
                err = err + dx;
                y1 = y1 + sy;
            }
        }
    }
}
