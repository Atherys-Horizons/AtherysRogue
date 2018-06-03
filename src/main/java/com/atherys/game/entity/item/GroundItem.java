package com.atherys.game.entity.item;

import com.atherys.game.entity.Entity;
import com.atherys.game.entity.Location;
import com.atherys.game.player.Player;
import com.googlecode.lanterna.TextCharacter;

import java.util.UUID;

public abstract class GroundItem extends AbstractItem implements Entity {

    private UUID uuid;
    private Location location;

    public GroundItem(String id, String name, String description, TextCharacter representation) {
        super(id, name, description, representation);
        this.uuid = UUID.randomUUID();
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void use(Player player) {}

    public abstract InventoryItem asInventoryItem();
}
