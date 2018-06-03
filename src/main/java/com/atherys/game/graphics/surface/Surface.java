package com.atherys.game.graphics.surface;

import com.atherys.game.graphics.drawable.Drawable;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Collection;

public interface Surface extends Drawable {

    Collection<Drawable> getDrawables();

    void add(Drawable drawable);

    void remove(Drawable drawable);

    default void apply(TextGraphics graphics) {
        getDrawables().forEach(drawable -> drawable.apply(graphics));
    }

    static Surface of(Drawable... drawables) {
        SimpleSurface surface = new SimpleSurface();
        for (Drawable drawable : drawables) {
            surface.add(drawable);
        }
        return surface;
    }
}
