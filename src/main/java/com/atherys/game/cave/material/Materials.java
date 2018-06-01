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
    public static final Material GRASS_FLOOR = new FloorMaterial("grass_floor", ',');
    public static final Material STONE_FLOOR = new FloorMaterial("stone_floor", '.');
    public static final Material WATER = new FloorMaterial("water", '~');
    private static final Material[] FLOOR_MATERIALS = new Material[]{
            GRASS_FLOOR,
            STONE_FLOOR,
            WATER
    };
    private static final Material[] WALL_MATERIALS = new Material[]{
            STONE_WALL
    };

    public static Material[] getFloorMaterials() {
        return FLOOR_MATERIALS;
    }

    public Material[] getWallMaterials() {
        return WALL_MATERIALS;
    }
}
