package com.atherys.game.entity;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Player extends Human {
    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('@', TextColor.ANSI.YELLOW, TextColor.ANSI.BLACK, SGR.BOLD);

    public Player(Location location) {
        super(location);
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }
}
