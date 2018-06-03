package com.atherys.game.control;

import com.googlecode.lanterna.input.KeyStroke;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class Controls {

    private static Controls instance = new Controls();

    private Map<KeyStroke, KeyMapping> controls = new HashMap<>();

    private Controls() {
    }

    public void set(KeyStroke stroke, Consumer<KeyStroke> action) {
        if (action == null) controls.remove(stroke);
        this.controls.put(stroke, new KeyMapping(stroke, action));
    }

    public Optional<KeyMapping> get(KeyStroke stroke) {
        if (stroke == null) return Optional.empty();
        return Optional.ofNullable(controls.get(stroke));
    }

    public static void register(KeyStroke stroke, Consumer<KeyStroke> action) {
        getInstance().set(stroke, action);
    }

    public static void trigger(KeyStroke stroke) {
        getInstance().get(stroke).ifPresent(keyMapping -> keyMapping.getAction().accept(stroke));
    }

    public static void remove(KeyStroke stroke) {
        getInstance().set(stroke, null);
    }

    public static Controls getInstance() {
        return instance;
    }

}
