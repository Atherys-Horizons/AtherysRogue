package com.atherys.game.entity;

import com.atherys.game.graphics.CharRepresentable;

import java.util.UUID;

public interface Entity extends CharRepresentable, Movable {

    UUID getUniqueId();

    Location getLocation();

}
