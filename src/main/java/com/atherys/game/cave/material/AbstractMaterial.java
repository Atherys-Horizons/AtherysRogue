package com.atherys.game.cave.material;

import com.googlecode.lanterna.TextCharacter;

public abstract class AbstractMaterial implements Material {

    private String id;
    private TextCharacter representation;

    public AbstractMaterial(String id, char representation) {
        this.id = id;
        this.representation = new TextCharacter(representation);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public TextCharacter getChar() {
        return representation;
    }
}
