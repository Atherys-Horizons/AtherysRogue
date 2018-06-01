package com.atherys.game.entity;

import com.atherys.game.cave.material.Materials;
import com.googlecode.lanterna.TextCharacter;

public class Human extends AbstractEntity {

    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('@', null, Materials.FLOOR_COLOR);

    public Human(Location location) {
        super(location);
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }
}
