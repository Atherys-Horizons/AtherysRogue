package com.atherys.game.math;

public final class MathUtils {

    public static double clamp(double min, double max, double value) {
        if ( value < min ) return min;
        if ( value > max ) return max;
        return value;
    }

    public static int clamp(int min, int max, int value) {
        if ( value < min ) return min;
        if ( value > max ) return max;
        return value;
    }

}
