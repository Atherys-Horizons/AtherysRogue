package com.atherys.game.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private static final EventManager instance = new EventManager();

    private Map<Class<? extends Event>, List<EventListener>> listeners = new HashMap<>();

    private EventManager() {
    }

    public static EventManager getInstance() {
        return instance;
    }

    public <T extends Event> void post(T event) {
        if (listeners.containsKey(event.getClass())) {
            listeners.get(event.getClass()).forEach(eventListener -> eventListener.accept(event));
        }
    }

    public <T extends Event> void registerEventListener(Class<T> event, EventListener<T> listener) {
        if (listeners.containsKey(event)) listeners.get(event).add(listener);
        else listeners.put(event, Arrays.asList(listener));
    }

    public <T extends Event> boolean removeEventListener(Class<T> event, EventListener<T> listener) {
        return listeners.containsKey(event) && listeners.get(event).remove(listener);
    }
}
