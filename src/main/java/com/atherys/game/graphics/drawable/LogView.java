package com.atherys.game.graphics.drawable;

import com.atherys.game.Log;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class LogView extends TitleBox {

    public LogView(String title, int x, int y, int maxWidth, int numLines) {
        super(title, x, y, maxWidth + 2, numLines + 1);
    }

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    @Override
    public void apply(TextGraphics surface) {
        super.apply(surface);
        List<Log.LoggedMessage> lines = Log.getInstance().getLast(getHeight() - 1);
        if (lines.size() == 0) return;
        for (int i = 0; i < lines.size(); i++) {
            String message = lines.get(i) == null ? "None" : lines.get(i).getMessage();
            surface.putString(x + 1, y + 1 + i, (i + 1) + ": " + padRight(message, w - 2 - message.length()));
        }

        surface.setForegroundColor(TextColor.ANSI.DEFAULT);
    }
}
