package com.atherys.game.math;

public final class MathUtils {

    public static double clamp(double min, double max, double value) {
        return value > min ? value < max ? value : max : min;
    }

}
