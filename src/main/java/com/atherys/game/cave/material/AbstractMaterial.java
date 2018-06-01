package com.atherys.game.cave.material;

import com.googlecode.lanterna.TextCharacter;

public abstract class AbstractMaterial implements Material {

    private String id;
    private TextCharacter representation;
    private boolean passable;

    public AbstractMaterial(String id, boolean passable, char representation) {
        this.id = id;
        this.representation = new TextCharacter(representation);
        this.passable = passable;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public TextCharacter getChar() {
        return representation;
    }

    @Override
    public boolean isPassable() {
        return passable;
    }
}
