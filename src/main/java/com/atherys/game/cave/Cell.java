package com.atherys.game.cave;

import com.atherys.game.cave.material.Material;
import com.atherys.game.entity.Location;
import com.atherys.game.math.Ray;
import com.atherys.game.math.Vector2i;

public class Cell {

    private Location location;
    private Material material;

    private boolean lit;

    public Cell(Location location, Material material) {
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    public boolean isVisibleFrom(Cell cell) {
        return !CellRay.of(cell, this).first(otherCell -> !otherCell.equals(this) && otherCell.isBlocking()).isPresent();
    }

    public boolean isVisibleFrom(Vector2i from) {
        if ( isBlocking() ) return true;
        boolean[] visible = new boolean[]{true};

        Ray.of(
                this.getLocation().getX(), this.getLocation().getY(),
                from.getX(), from.getY(),
                (x,y) -> {
                    if (x.equals(this.getLocation().getX()) && y.equals(this.getLocation().getY())) return;
                    if (!visible[0]) return;

                    Cell lineCell = getLocation().getCave().getCell(x, y);
                    if ( lineCell != null && lineCell.isBlocking() ) {
                        visible[0] = false;
                    }
                });

        return visible[0];
    }
}
