package com.atherys.game.player.inventory;

import com.atherys.game.item.ItemStack;

public class PlayerInventory extends AbstractInventory {

    public final static int PLAYER_INVENTORY_SIZE_X = 5;
    public final static int PLAYER_INVENTORY_SIZE_Y = 3;

    public PlayerInventory() {
        super(PLAYER_INVENTORY_SIZE_X, PLAYER_INVENTORY_SIZE_Y);
    }

    @Override
    public boolean add(ItemStack item) {
        for (ItemStack itemStack : this) {
            if (itemStack.equals(item)) return itemStack.setAmount(itemStack.getAmount() + item.getAmount());
        }
        return super.size() + 1 <= getSize().getX() * getSize().getY() && super.add(item);
    }
}
