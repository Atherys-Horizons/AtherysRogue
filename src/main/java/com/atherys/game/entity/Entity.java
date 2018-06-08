package com.atherys.game.entity;

import com.atherys.game.graphics.CharRepresentable;

import java.util.UUID;

public interface Entity extends CharRepresentable {

    UUID getUniqueId();

    Location getLocation();

    <T extends Entity> void interact(T entity);

}
