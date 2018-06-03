package com.atherys.game.entity.item;

import com.atherys.game.player.Player;
import com.googlecode.lanterna.TextCharacter;

import java.util.function.BiConsumer;

public abstract class InventoryItem extends AbstractItem {

    public InventoryItem(String id, String name, String description, TextCharacter representation, BiConsumer<InventoryItem, Player> onUse) {
        super(id, name, description, representation);
    }

    public abstract GroundItem asGroundItem();
}
