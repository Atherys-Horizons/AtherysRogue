package com.atherys.game.entity.item;

import com.atherys.game.graphics.CharRepresentable;
import com.atherys.game.player.Player;

public interface Item extends CharRepresentable {

    String getId();

    String getName();

    String getDescription();

    void use(Player player);

}
