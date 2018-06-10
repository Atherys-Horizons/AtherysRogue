package com.atherys.game.entity;

import com.atherys.game.event.EventManager;
import com.atherys.game.event.entity.EntityHealthUpdateEvent;

import java.util.UUID;

public abstract class AbstractLiving implements Living {

    protected UUID uuid;
    protected Location location;
    protected double health;
    protected double maxHealth;

    public AbstractLiving(Location location, double maxHealth) {
        this.uuid = UUID.randomUUID();
        this.location = location;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public <T extends Entity> void interact(T entity) {
        if ( entity instanceof Living ) ((Living) entity).removeHealth(1.0d);
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double amount) {
        double originalHealth = this.health;
        this.health = amount < 0.0d ? 0.0d : ( amount > getMaxHealth() ? getMaxHealth() : amount );
        EventManager.getInstance().post(new EntityHealthUpdateEvent(this, originalHealth - this.health));
    }

    @Override
    public double getMaxHealth() {
        return maxHealth;
    }
}
