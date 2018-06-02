package com.atherys.game.cave.material;

import com.googlecode.lanterna.TextCharacter;

public abstract class AbstractMaterial implements Material {

    private String id;
    private TextCharacter representation;

    private boolean passable;
    private boolean blocking;

    public AbstractMaterial(String id, boolean passable, boolean blocking, TextCharacter representation) {
        this.id = id;
        this.representation = representation;
        this.passable = passable;
        this.blocking = blocking;
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

    @Override
    public boolean isBlocking() {
        return blocking;
    }
}
