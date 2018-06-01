package com.atherys.game.entity;

import com.atherys.game.graphics.drawable.Drawable;
import com.atherys.game.graphics.drawable.EntityDrawable;
import com.googlecode.lanterna.TerminalPosition;

import java.util.UUID;

public abstract class AbstractEntity implements Entity {

    protected UUID uuid;
    protected Location location;

    private EntityDrawable drawable;

    public AbstractEntity(Location location) {
        this.uuid = UUID.randomUUID();
        this.location = location;
        this.drawable = new EntityDrawable(this, new TerminalPosition(location.getX(), location.getY()));
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public void move(int deltaX, int deltaY) {
        location.translate(deltaX, deltaY);
        drawable.move(deltaX, deltaY);
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
