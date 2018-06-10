package com.atherys.game.event;

@FunctionalInterface
public interface EventListener<T extends Event> {

    void accept(T event);

}
