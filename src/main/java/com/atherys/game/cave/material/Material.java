package com.atherys.game.cave.material;

import com.atherys.game.graphics.CharRepresentable;

public interface Material extends CharRepresentable {

    String getId();

    boolean isPassable();

}
