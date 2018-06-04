package com.atherys.game.player.inventory;

import com.atherys.game.item.ItemStack;
import com.atherys.game.math.Vector2i;

public interface Inventory {

    Vector2i getSize();

    ItemStack[][] asMatrix();

    boolean add(ItemStack item);

    boolean remove(ItemStack item);

    boolean contains(ItemStack item);

}
