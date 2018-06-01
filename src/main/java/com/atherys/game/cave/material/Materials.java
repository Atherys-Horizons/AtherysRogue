package com.atherys.game.cave.material;

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

    public static final Material STONE_WALL = new WallMaterial("stone_wall", '█');

    public static final Material GRASS_FLOOR = new FloorMaterial("grass_floor", true, ',');

    public static final Material STONE_FLOOR = new FloorMaterial("stone_floor", true, '.');

    public static final Material WATER = new FloorMaterial("water", false, '~');

}
