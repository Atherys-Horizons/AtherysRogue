package com.atherys.game.item;

import java.util.Objects;

public class ItemStack implements Item {

    public static final int MAX_ITEMSTACK_SIZE = 99;

    private int amount;

    private ItemType type;
    private String name;
    private String description;

    public ItemStack(ItemType type, int amount) {
        this.type = type;
        this.amount = amount;
        this.name = type.getName();
        this.description = "";
    }

    public ItemStack(ItemType type, String name, String description, int amount) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public ItemType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String string) {
        this.name = string;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String string) {
        this.description = string;
    }

    public int getAmount() {
        return amount;
    }

    public boolean setAmount(int amount) {
        if (amount > MAX_ITEMSTACK_SIZE) return false;
        if (amount < 0) return false;
        this.amount = amount;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemStack)) return false;
        ItemStack itemStack = (ItemStack) o;
        return Objects.equals(type, itemStack.type) &&
                Objects.equals(name, itemStack.name) &&
                Objects.equals(description, itemStack.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, type, name, description);
    }
}
