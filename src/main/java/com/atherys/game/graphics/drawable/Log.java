package com.atherys.game.graphics.drawable;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Log extends TitleBox {

    private String[] lines;

    public Log(String title, int x, int y, int maxWidth, int numLines) {
        super(title, x, y, maxWidth + 2, numLines + 1);
        this.lines = new String[numLines];
    }

    public void setLine(int index, String value) {
        if ( index >= 0 && index < lines.length ) lines[index] = padRight(value, w - 2 - value.length());
    }

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    public void push(String line) {
        System.arraycopy(lines, 0, lines, 1, lines.length - 1);
        setLine(0, line);
    }

    @Override
    public void apply(TextGraphics surface) {
        super.apply(surface);
        for ( int i = 0; i < lines.length; i++ ) {
            surface.putString(x + 1, y + 1 + i, (i + 1) + ": " + (lines[i] == null ? "None" : lines[i]) );
        }
    }
}
