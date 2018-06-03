package com.atherys.game.graphics.surface;

import com.atherys.game.graphics.drawable.Drawable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SimpleSurface implements Surface {

    private Set<Drawable> drawableList = new HashSet<>();

    public SimpleSurface() {
    }

    @Override
    public Collection<Drawable> getDrawables() {
        return drawableList;
    }

    @Override
    public void add(Drawable drawable) {
        drawableList.add(drawable);
    }

    @Override
    public void remove(Drawable drawable) {
        drawableList.remove(drawable);
    }
}
