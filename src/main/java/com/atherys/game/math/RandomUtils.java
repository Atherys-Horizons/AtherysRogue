package com.atherys.game.math;

public final class RandomUtils {

    /**
     * Generates a random integer in the range of [min,max).
     *
     * @param min The minimum integer
     * @param max The maximum integer
     * @return The random result between the two
     */
    public static Integer between(Integer min, Integer max) {
        double result = Math.random() * (max - min) + min;
        return (int) result;
    }

}
