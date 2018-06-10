package com.atherys.game.event.entity;

import com.atherys.game.entity.Living;
import com.atherys.game.event.Event;

public class EntityHealthUpdateEvent implements Event {

    private Living entity;
    private double delta;

    public EntityHealthUpdateEvent(Living entity, double delta) {
        this.entity = entity;
        this.delta = delta;
    }

    public Living getEntity() {
        return entity;
    }

    public double getDelta() {
        return delta;
    }
}
