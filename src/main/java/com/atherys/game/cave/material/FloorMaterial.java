package com.atherys.game.cave.material;

import com.googlecode.lanterna.TextCharacter;

public class FloorMaterial extends AbstractMaterial {
    FloorMaterial(String id, boolean passable, TextCharacter representation) {
        super(id, passable, false, representation);
    }
}
