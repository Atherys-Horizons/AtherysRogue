package com.atherys.game.graphics.drawable;

import com.googlecode.lanterna.graphics.TextGraphics;

@FunctionalInterface
public interface Drawable {

    void apply(TextGraphics surface);

}
