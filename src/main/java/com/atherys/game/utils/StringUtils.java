package com.atherys.game.utils;

import java.util.ArrayList;
import java.util.List;

public final class StringUtils {

    public static String wordWrap(String string, int maxWidth) {
        StringBuilder builder = new StringBuilder();

        String[] words = string.split(" ");

        for (String word : words) {
            if (builder.length() + word.length() > maxWidth) {
                builder.append("\n");
            }

            builder.append(word).append(" ");
        }

        return builder.toString();
    }

    public static List<String> wordWrapLines(String string, int maxWidth) {
        String[] words = string.split(" ");

        List<String> lines = new ArrayList<>(words.length);

        StringBuilder line = new StringBuilder();
        for (String word : words) {

            if (line.length() + word.length() > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder();
            }

            if (word.endsWith("\n")) {
                lines.add(line.toString());
                line = new StringBuilder();
            }

            line.append(word).append(" ");
        }
        lines.add(line.toString());

        return lines;
    }

}
