package com.atherys.game.entity;

import com.atherys.game.base.CharRepresentable;
import com.atherys.game.base.Movable;

import java.util.UUID;

public interface Entity extends CharRepresentable, Movable {

    UUID getUniqueId();

    Location getLocation();

}
