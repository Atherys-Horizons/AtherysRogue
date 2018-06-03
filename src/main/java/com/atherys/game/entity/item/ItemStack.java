package com.atherys.game.entity.item;

public class ItemStack {

    private InventoryItem type;
    private int amount;

    public ItemStack(InventoryItem type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public InventoryItem getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
