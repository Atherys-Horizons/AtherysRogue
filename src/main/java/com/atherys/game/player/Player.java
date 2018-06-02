package com.atherys.game.player;

import com.atherys.game.cave.material.Materials;
import com.atherys.game.entity.Human;
import com.atherys.game.entity.Location;
import com.atherys.game.math.Circle;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Player extends Human {
    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('@', TextColor.ANSI.YELLOW, Materials.FLOOR_COLOR, SGR.BOLD, SGR.FRAKTUR);

    private Circle fov;

    public Player(Location location, int fovRadius) {
        super(location);
        this.fov = new Circle(location, fovRadius);
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }

    public Circle getFov() {
        return fov;
    }
}
