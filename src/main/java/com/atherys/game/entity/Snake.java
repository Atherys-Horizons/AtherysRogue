package com.atherys.game.entity;

import com.googlecode.lanterna.TextCharacter;

public class Snake extends AbstractEntity {

    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('s');

    public Snake(Location location) {
        super(location);
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }
}
