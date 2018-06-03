package com.atherys.game.graphics.surface;

import com.atherys.game.graphics.drawable.Drawable;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.*;

public class LayeredSurface implements Surface {

    //private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Map<Integer,Set<Drawable>> drawableLayers = new HashMap<>();

    public LayeredSurface() {
    }

    @Override
    public Collection<Drawable> getDrawables() {
        List<Drawable> all = new ArrayList<>();
        drawableLayers.forEach((k,v) -> all.addAll(v));
        return all;
    }

    @Override
    public void add(Drawable drawable) {
        add(0, drawable);
    }

    @Override
    public void remove(Drawable drawable) {
        drawableLayers.forEach((k,v) -> v.remove(drawable));
    }

    public void add(Integer layer, Drawable drawable) {
        if ( !drawableLayers.containsKey(layer) ) drawableLayers.put(layer, new HashSet<>());
        drawableLayers.get(layer).add(drawable);
    }

    @Override
    public void apply(TextGraphics graphics) {
        drawableLayers.forEach((k,v) -> v.forEach(drawable -> drawable.apply(graphics)));
        //executor.submit(() -> {
        //    drawableLayers.forEach((k,v) -> v.forEach(drawable -> drawable.apply(graphics)));
        //});
    }
}
