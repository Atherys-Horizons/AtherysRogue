package com.atherys.game.player.inventory;

import com.atherys.game.item.ItemStack;
import com.atherys.game.math.Vector2i;
import com.atherys.game.utils.ArrayUtils;

import java.util.HashSet;
import java.util.Iterator;

public class AbstractInventory extends HashSet<ItemStack> implements Inventory {

    private Vector2i size;

    public AbstractInventory(int sizeX, int sizeY) {
        super(sizeX * sizeY);
        this.size = Vector2i.of(sizeX, sizeY);
    }

    @Override
    public Vector2i getSize() {
        return size;
    }

    @Override
    public ItemStack[][] asMatrix() {
        ItemStack[][] itemMatrix = new ItemStack[size.getX()][size.getY()];
        Iterator<ItemStack> itemIterator = super.iterator();
        ArrayUtils.iterate(0, size.getX(), 1, 0, size.getY(), 1, (x, y) -> {
            if ( itemIterator.hasNext() ) itemMatrix[x][y] = itemIterator.next();
        });
        return itemMatrix;
    }

    @Override
    public boolean add(ItemStack item) {
        for ( ItemStack itemStack : this ) {
            if ( itemStack.equals(item) ) return itemStack.setAmount(itemStack.getAmount() + item.getAmount());
        }
        return super.add(item);
    }

    @Override
    public boolean remove(ItemStack item) {
        return super.remove(item);
    }

    @Override
    public boolean contains(ItemStack item) {
        return super.contains(item);
    }
}
