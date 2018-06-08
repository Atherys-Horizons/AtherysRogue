package com.atherys.game.entity;

import com.atherys.game.Log;
import com.atherys.game.item.ItemStack;
import com.atherys.game.player.Player;
import com.googlecode.lanterna.TextCharacter;

import java.util.UUID;

public class GroundItem implements Entity {

    private UUID uuid;
    private Location location;
    private ItemStack item;

    public GroundItem(ItemStack item, Location location) {
        this.uuid = UUID.randomUUID();
        this.item = item;
        this.location = location;
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

    public ItemStack getItem() {
        return item;
    }

    @Override
    public TextCharacter getChar() {
        return item.getType().getChar();
    }

    @Override
    public <T extends Entity> void interact(T entity) {
        if ( entity instanceof Player ) {
            location.getCave().removeEntity(this);
            if ( !((Player) entity).getInventory().add(this.getItem()) ) {
                Log.warn("Failed to pick up %s[%02d]", item.getName(), item.getAmount());
            } else {
                Log.info("Picked up %s[%02d]", item.getName(), item.getAmount());
            }
        }
    }

}
