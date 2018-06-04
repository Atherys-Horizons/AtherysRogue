package com.atherys.game.item;

import com.atherys.game.cave.material.Materials;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public final class ItemTypes {

    public static final ItemType DAGGER = new ItemType("Dggr", "Dagger", new TextCharacter('/', TextColor.ANSI.DEFAULT, Materials.FLOOR_COLOR));

    public static final ItemType SHIELD = new ItemType("Shld", "Shield", new TextCharacter('ø', TextColor.ANSI.DEFAULT, Materials.FLOOR_COLOR));

    public static final ItemType APPLE = new ItemType("Appl", "Apple", new TextCharacter('ò', TextColor.ANSI.RED, Materials.FLOOR_COLOR));

    public static final ItemType HEALTH_POTION = new ItemType("HPpt", "Health Potion", new TextCharacter('o', TextColor.ANSI.RED, Materials.FLOOR_COLOR));

}
