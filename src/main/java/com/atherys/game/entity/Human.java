package com.atherys.game.entity;

import com.atherys.game.cave.material.Materials;
import com.googlecode.lanterna.TextCharacter;

public class Human extends AbstractLiving {

    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('@', null, Materials.FLOOR_COLOR);

    public Human(Location location, double maxHealth) {
        super(location, maxHealth);
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }
}
