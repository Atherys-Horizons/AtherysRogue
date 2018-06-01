package com.atherys.game.entity;

import com.googlecode.lanterna.TextCharacter;

public class Human extends AbstractEntity {

    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('@');

    public Human(Location location) {
        super(location);
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }
}
