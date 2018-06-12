package com.atherys.game.event.player;

import com.atherys.game.entity.Location;
import com.atherys.game.event.Event;
import com.atherys.game.player.Player;

public class PlayerRespawnEvent implements Event {

    private Location preRespawnLocation;
    private Player player;

    public PlayerRespawnEvent(Player player, Location preRespawnLocation) {
        this.preRespawnLocation = preRespawnLocation;
        this.player = player;
    }

    public Location getPreRespawnLocation() {
        return preRespawnLocation;
    }

    public Player getPlayer() {
        return player;
    }
}
