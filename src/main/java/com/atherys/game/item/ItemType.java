package com.atherys.game.item;

import com.atherys.game.graphics.CharRepresentable;
import com.googlecode.lanterna.TextCharacter;

public class ItemType implements CharRepresentable {

    private String id;
    private String name;
    private TextCharacter charRepresentation;

    public ItemType(String id, String name, TextCharacter charRepresentation) {
        this.id = id;
        this.name = name;
        this.charRepresentation = charRepresentation;
    }

    @Override
    public TextCharacter getChar() {
        return charRepresentation;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
