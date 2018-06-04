package com.atherys.game.item;

import com.atherys.game.math.MathUtils;
import com.atherys.game.math.RandomUtils;

import static com.atherys.game.item.ItemTypes.*;
public class ItemGenerator {

    private static ItemType[] types = new ItemType[] {
            DAGGER,
            APPLE,
            HEALTH_POTION,
            SHIELD
    };

    public static ItemStack getRandom(int maxAmount) {
        return new ItemStack(types[RandomUtils.between(0,types.length)], RandomUtils.between(1, MathUtils.clamp(1, ItemStack.MAX_ITEMSTACK_SIZE, maxAmount)));
    }

    public static ItemStack getRandom() {
        return new ItemStack(types[RandomUtils.between(0,types.length)], RandomUtils.between(1, ItemStack.MAX_ITEMSTACK_SIZE));
    }

}
