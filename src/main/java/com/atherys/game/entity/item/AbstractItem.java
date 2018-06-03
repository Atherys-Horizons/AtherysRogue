package com.atherys.game.entity.item;

import com.googlecode.lanterna.TextCharacter;

public abstract class AbstractItem implements Item {

    private String id;
    private String name;
    private String description;

    private TextCharacter representation;

    public AbstractItem(String id, String name, String description, TextCharacter representation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.representation = representation;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public TextCharacter getChar() {
        return representation;
    }
}
