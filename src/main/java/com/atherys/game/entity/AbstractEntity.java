package com.atherys.game.entity;

import com.atherys.game.cave.Cell;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractEntity implements Entity, Movable {

    protected UUID uuid;
    protected Location location;

    public AbstractEntity(Location location) {
        this.uuid = UUID.randomUUID();
        this.location = location;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void move(int deltaX, int deltaY) {
        Cell moveTo = location.getCave().getCell(location.getX() + deltaX, location.getY() + deltaY);
        if ( moveTo != null && moveTo.isPassable() ) {
            Optional<Entity> entity = location.getCave().pollForEntity(moveTo.getLocation());
            if ( entity.isPresent() ) {
                entity.get().interact(this);
            } else {
                location.translate(deltaX, deltaY);
            }
        }
    }

    @Override
    public <T extends Entity> void interact(T entity) {

    }
}
