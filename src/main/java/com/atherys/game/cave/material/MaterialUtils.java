package com.atherys.game.cave.material;

import static com.atherys.game.cave.material.Materials.*;

public final class MaterialUtils {

    private static final Material[] FLOOR_MATERIALS = new Material[]{
            GRASS_FLOOR,
            STONE_FLOOR
            //WATER
    };
    private static final Material[] WALL_MATERIALS = new Material[]{
            STONE_WALL
    };

    public static Material[] getFloorMaterials() {
        return FLOOR_MATERIALS;
    }

    public static Material[] getWallMaterials() {
        return WALL_MATERIALS;
    }

}
