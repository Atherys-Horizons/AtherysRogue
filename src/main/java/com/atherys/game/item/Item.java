package com.atherys.game.item;

public interface Item {

    ItemType getType();

    String getName();

    void setName(String string);

    String getDescription();

    void setDescription(String string);

}
