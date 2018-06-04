package com.atherys.game.graphics.drawable;

import com.atherys.game.item.ItemStack;
import com.atherys.game.player.inventory.Inventory;
import com.atherys.game.utils.ArrayUtils;
import com.googlecode.lanterna.graphics.TextGraphics;

public class InventoryView extends TitleBox {

    private final static int INVENTORY_CELL_SIZE_X = 5;
    private final static int INVENTORY_CELL_SIZE_Y = 3;

    private final Inventory inventory;
    private Table table;

    public <T extends Inventory> InventoryView(int x, int y, T inventory) {
        super(
                "Inventory",
                x,
                y,
                inventory.getSize().getX() * INVENTORY_CELL_SIZE_X,
                inventory.getSize().getY() * INVENTORY_CELL_SIZE_Y + 1
        );

        this.table = new Table(
                x,
                y + 1,
                INVENTORY_CELL_SIZE_X,
                INVENTORY_CELL_SIZE_Y,
                inventory.getSize().getY(),
                inventory.getSize().getX()
        );

        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void apply(TextGraphics surface) {
        super.apply(surface);
        table.apply(surface);

        surface.setCharacter(table.getX(), table.getY(), Table.WEST_TRIPOINT);
        surface.setCharacter(table.getX() + table.getWidth(), table.getY(), Table.EAST_TRIPOINT);

        ItemStack[][] inventory = this.inventory.asMatrix();

        ArrayUtils.forEachNonNull(inventory, (r, c, i) -> {
            int x = table.getX() + table.getCellWidth() * c + 1;
            int y = table.getY() + table.getCellHeight() * r + 1;
            surface.putString(x, y, i.getType().getId());
            surface.putString(x, y + 1, String.format("[%02d]", i.getAmount()));
        });
    }
}
