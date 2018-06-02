package com.atherys.game.cave;

import com.atherys.game.cave.material.Material;
import com.atherys.game.math.Vector2i;

public class Cell {

    private Vector2i position;
    private Material material;

    private boolean lit;

    public Cell(Vector2i position, Material material) {
        this.position = position;
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

    public boolean isBlocking() {
        return material.isBlocking();
    }

    public boolean isLit() {
        return lit;
    }

    public void setLight(boolean lit) {
        this.lit = lit;
    }

    public Vector2i getPosition() {
        return position;
    }
}
