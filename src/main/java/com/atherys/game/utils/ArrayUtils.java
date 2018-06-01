package com.atherys.game.utils;

import java.util.function.BiConsumer;

public final class ArrayUtils {

    public static <T> void forEach(T[] arr, BiConsumer<Integer, T> consumer) {
        for (int i = 0; i < arr.length; i++) {
            consumer.accept(i, arr[i]);
        }
    }

    public static <T> void forEachNonNull(T[] arr, BiConsumer<Integer, T> consumer) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) consumer.accept(i, arr[i]);
        }
    }

    public static <T> void forEach(T[][] arr, TriConsumer<Integer, Integer, T> consumer) {
        for (int column = 0; column < arr.length; column++) {
            for (int row = 0; row < arr[column].length; row++) {
                consumer.accept(row, column, arr[column][row]);
            }
        }
    }

    public static <T> void forEachNonNull(T[][] arr, TriConsumer<Integer, Integer, T> consumer) {
        for (int column = 0; column < arr.length; column++) {
            for (int row = 0; row < arr[column].length; row++) {
                if (arr[column][row] != null) consumer.accept(row, column, arr[column][row]);
            }
        }
    }

    public static <T> void forEach(T[] arr, int from, int to, BiConsumer<Integer, T> consumer) {
        if ((from > 0 && from < arr.length) && (to > from && to < arr.length)) {
            for (int i = from; i < to; i++) consumer.accept(i, arr[i]);
        }
    }

    public static <T> void forEach(T[][] arr, int fromRow, int toRow, int fromColumn, int toColumn, TriConsumer<Integer, Integer, T> consumer) {
        if ((fromColumn > 0 && fromColumn < arr.length) && (toColumn > fromColumn && toColumn < arr.length)) {
            if ((fromRow > 0 && fromRow < arr[fromColumn].length) && (toRow > fromRow && toRow < arr[fromColumn].length)) {
                for (int i = fromColumn; i < toColumn; i++) {
                    for (int j = fromRow; j < toRow; j++) {
                        consumer.accept(j, i, arr[i][j]);
                    }
                }
            }
        }
    }

    public static <T> void forEachNonNull(T[] arr, int from, int to, BiConsumer<Integer, T> consumer) {
        if ((from > 0 && from < arr.length) && (to > from && to < arr.length)) {
            for (int i = from; i < to; i++) if (arr[i] != null) consumer.accept(i, arr[i]);
        }
    }

    public static <T> void forEachNonNull(T[][] arr, int fromRow, int toRow, int fromColumn, int toColumn, TriConsumer<Integer, Integer, T> consumer) {
        if ((fromColumn > 0 && fromColumn < arr.length) && (toColumn > fromColumn && toColumn < arr.length)) {
            if ((fromRow > 0 && fromRow < arr[fromColumn].length) && (toRow > fromRow && toRow < arr[fromColumn].length)) {
                for (int i = fromColumn; i < toColumn; i++) {
                    for (int j = fromRow; j < toRow; j++) {
                        if (arr[i][j] != null) consumer.accept(j, i, arr[i][j]);
                    }
                }
            }
        }
    }
}
