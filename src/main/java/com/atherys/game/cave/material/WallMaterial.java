package com.atherys.game.cave.material;

import com.googlecode.lanterna.TextCharacter;

public class WallMaterial extends AbstractMaterial {
    WallMaterial(String id, TextCharacter representation) {
        super(id, false, representation);
    }
}
