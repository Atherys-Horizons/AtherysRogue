package com.atherys.game.control;

import com.googlecode.lanterna.input.KeyStroke;

import java.util.function.Consumer;

public class KeyMapping {

    private KeyStroke keyStroke;
    private Consumer<KeyStroke> action;

    public KeyMapping (KeyStroke keyStroke, Consumer<KeyStroke> action) {
        this.keyStroke = keyStroke;
        this.action = action;
    }

    public static KeyMapping of(KeyStroke keyStroke, Consumer<KeyStroke> action) {
        return new KeyMapping(keyStroke, action);
    }

    public KeyStroke getKeyStroke() {
        return keyStroke;
    }

    public Consumer<KeyStroke> getAction() {
        return action;
    }
}
