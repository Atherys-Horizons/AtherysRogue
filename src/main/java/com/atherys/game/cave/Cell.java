package com.atherys.game.cave;

import com.atherys.game.cave.material.Material;
import com.atherys.game.cave.material.Materials;

public class Cell {

    private Material material;

    public Cell() {
        this.material = Materials.STONE_WALL;
    }

    public Cell(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public boolean isPassable() {
        return material.isPassable();
    }
}
