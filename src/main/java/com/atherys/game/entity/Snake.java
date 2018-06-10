package com.atherys.game.entity;

import com.atherys.game.cave.material.Materials;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Snake extends AbstractLiving {

    private static final double DEFAULT_HEALTH = 25.0d;
    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('s', TextColor.ANSI.GREEN, Materials.FLOOR_COLOR);

    public Snake(Location location) {
        super(location, DEFAULT_HEALTH);
    }

    public Snake(Location location, double maxHealth) {
        super(location, maxHealth);
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }
}
