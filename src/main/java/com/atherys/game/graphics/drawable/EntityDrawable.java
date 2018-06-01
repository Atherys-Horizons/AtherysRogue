package com.atherys.game.graphics.drawable;

import com.atherys.game.base.Movable;
import com.atherys.game.entity.Entity;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class EntityDrawable implements Drawable, Movable {

    private Entity entity;

    private TerminalPosition lastPosition;
    private TerminalPosition currentPosition;

    public EntityDrawable(Entity entity, TerminalPosition position) {
        this.entity = entity;
        this.lastPosition = position;
        this.currentPosition = position;
    }

    public void move(int deltaX, int deltaY) {
        currentPosition = currentPosition.withRelative(deltaX, deltaY);
    }

    @Override
    public void apply(TextGraphics surface) {
        if (surface.getCharacter(lastPosition).equals(entity.getChar())) {
            int x = entity.getLocation().getX();
            int y = entity.getLocation().getY();
            surface.setCharacter(x, y, entity.getLocation().getCave().getCell(x, y).getMaterial().getChar());
        }
        surface.setCharacter(currentPosition, entity.getChar());
    }
}
