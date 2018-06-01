package com.atherys.game.entity;

import com.atherys.game.base.CharRepresentable;
import com.atherys.game.base.Movable;
import com.atherys.game.graphics.drawable.Drawable;

import java.util.UUID;

public interface Entity extends CharRepresentable, Movable {

    UUID getUniqueId();

    Location getLocation();

    Drawable getDrawable();

}
