package com.atherys.game.cave.material;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

/*
ASCII:

     !"#$%&'()*+,-./0123456789:;<=>?@
     ABCDEFGHIJKLMNOPQRSTUVWXYZ
     [\]^_`
     abcdefghijklmnopqrstuvwxyz
     {|}~
     DÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑ
     ªº¿®¬½¼¡«»░▒▓│┤ÁÂÀ©╣║╗╝¢¥┐└┴┬├─┼ãÃ╚╔╩╦╠═╬¤ðÐÊËÈıÍÎÏ
     ┘┌█▄¦Ì▀ÓßÔÒõÕµþÞÚÛÙýÝ¯´¬±‗¾¶§÷¸°¨•¹³²■
 */
public final class Materials {

    public static final TextColor.RGB FLOOR_COLOR =  new TextColor.RGB(48, 48, 48);

    public static final TextColor.RGB WALL_COLOR =  new TextColor.RGB(168, 168, 168);

    public static final Material STONE_WALL = new WallMaterial("stone_wall", new TextCharacter(' ', WALL_COLOR, WALL_COLOR));

    public static final Material GRASS_FLOOR = new FloorMaterial("grass_floor", true, new TextCharacter(',', TextColor.ANSI.GREEN, FLOOR_COLOR, SGR.ITALIC));

    public static final Material STONE_FLOOR = new FloorMaterial(
            "stone_floor",
            true,
            new TextCharacter(
                    ' ',
                    new TextColor.RGB(FLOOR_COLOR.getRed() + 16, FLOOR_COLOR.getGreen() + 16, FLOOR_COLOR.getBlue() + 16),
                    FLOOR_COLOR
        )
    );

    public static final Material WATER = new FloorMaterial("water", false, new TextCharacter('~', new TextColor.RGB(0, 127, 255), TextColor.ANSI.BLACK, SGR.ITALIC));

}
