package com.atherys.game.cave;

import com.atherys.game.cave.material.Material;
import com.atherys.game.math.Ray;
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

    public boolean isVisible(Cave cave, Vector2i from) {
        boolean[] visible = new boolean[]{true};

        Ray.of(
                this.getPosition().getX(),
                this.getPosition().getY(),
                from.getX(),
                from.getY(),
                (x,y) -> {
                    if (x.equals(this.getPosition().getX()) && y.equals(this.getPosition().getY())) return;
                    if (!visible[0]) return;

                    Cell lineCell = cave.getCell(x, y);
                    if ( lineCell != null && lineCell.isBlocking() ) {
                        visible[0] = false;
                    }
                });

        return visible[0];
    }
}
