package com.atherys.game.event.entity;

import com.atherys.game.entity.Entity;
import com.atherys.game.event.Event;

public class EntityMoveEvent implements Event {

    private Entity entity;
    private int deltaX;
    private int deltaY;

}
