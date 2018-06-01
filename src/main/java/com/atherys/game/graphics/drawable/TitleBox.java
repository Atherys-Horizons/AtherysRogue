package com.atherys.game.graphics.drawable;

import com.googlecode.lanterna.graphics.TextGraphics;

public class TitleBox extends Box {

    protected String title;

    public TitleBox(String title, int x, int y, int w, int h) {
        super(x, y, w < title.length() ? title.length() : w, h);
        this.title = title;
    }

    @Override
    public void apply(TextGraphics surface) {
        super.apply(surface);
        surface.putString(x + 2, y, title);
    }
}
