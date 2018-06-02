package com.atherys.game.entity;

import com.atherys.game.cave.material.Materials;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Snake extends AbstractEntity {

    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('s', TextColor.ANSI.GREEN, Materials.FLOOR_COLOR);

    public Snake(Location location) {
        super(location);
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }
}
