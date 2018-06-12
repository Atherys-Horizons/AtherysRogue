package com.atherys.game.player;

import com.atherys.game.cave.material.Materials;
import com.atherys.game.entity.Entity;
import com.atherys.game.entity.Human;
import com.atherys.game.entity.Location;
import com.atherys.game.math.Circle;
import com.atherys.game.player.inventory.Inventory;
import com.atherys.game.player.inventory.PlayerInventory;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Player extends Human {

    private static final double MAX_HEALTH = 100.0d;

    private static final TextCharacter CHAR_REPRESENTATION = new TextCharacter('@', TextColor.ANSI.YELLOW, Materials.FLOOR_COLOR, SGR.BOLD, SGR.FRAKTUR);

    private Circle fov;
    private PlayerInventory inventory;

    public Player(Location location, int fovRadius) {
        super(location, MAX_HEALTH);
        this.fov = new Circle(location, fovRadius);
        this.inventory = new PlayerInventory();
    }

    @Override
    public TextCharacter getChar() {
        return CHAR_REPRESENTATION;
    }

    public Circle getFov() {
        return fov;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public <T extends Entity> void interact(T entity) {
        super.interact(entity);
    }

    @Override
    public void setHealth(double hp) {
        if (hp != 0.0d) {
            super.setHealth(hp);
            return;
        }

        location.getCave().respawnPlayer(this);
    }
}
