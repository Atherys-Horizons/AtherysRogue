package com.atherys.game.state;

import com.atherys.game.cave.Cave;
import com.atherys.game.cave.CaveGenerator;
import com.atherys.game.cave.MaterialDistribution;
import com.atherys.game.cave.material.FloorMaterial;
import com.atherys.game.cave.material.Materials;
import com.atherys.game.cave.material.WallMaterial;
import com.atherys.game.control.Controls;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.*;
import com.atherys.game.graphics.surface.LayeredSurface;
import com.atherys.game.math.RandomUtils;
import com.atherys.game.math.Vector2i;
import com.atherys.game.player.Player;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class MainGameState extends GraphicalState {

    private static final int CAVE_SEED = RandomUtils.between(0, 999999);

    private static final Vector2i CAVE_SIZE = Vector2i.of(254, 256);
    private static final int CAVE_ITERATIONS = 1;
    private static final int CAVE_WALL_THRESHHOLD = 5;
    private static final int CAVE_FLOOR_THRESHHOLD = 4;
    private static final MaterialDistribution CAVE_MATERIAL_DISTRIBUTION =
            MaterialDistribution.of(CAVE_SEED)
                    .add(Materials.STONE_WALL, 0.49d)
                    .add(Materials.STALACTITE, 0.10d)
                    .add(Materials.STONE_FLOOR, 0.41d)
                    .add(Materials.ROCK, 0.10d)
                    .add(Materials.STALAGMITE, 0.04d)
                    .setDefault(WallMaterial.class, Materials.STONE_WALL)
                    .setDefault(FloorMaterial.class, Materials.STONE_FLOOR);

    private static final int CAVE_VIEW_POSITION_X = 0;
    private static final int CAVE_VIEW_POSITION_Y = 0;
    private static final int CAVE_VIEW_SIZE_X = 55;
    private static final int CAVE_VIEW_SIZE_Y = 33;

    private Cave cave;
    private Player player;

    private LayeredSurface surface = new LayeredSurface();

    @Override
    public void start() {
        super.start();

        CaveGenerator generator = new CaveGenerator(CAVE_SEED, CAVE_MATERIAL_DISTRIBUTION, CAVE_SIZE, CAVE_WALL_THRESHHOLD, CAVE_FLOOR_THRESHHOLD, CAVE_ITERATIONS);
        cave = generator.getCave();

        player = new Player(cave.getRandomSpawnPoint().getLocation(), (CAVE_VIEW_SIZE_Y / 2));
        cave.spawnEntity(player);

        CaveView caveView = new CaveView(CAVE_VIEW_POSITION_X, CAVE_VIEW_POSITION_Y, CAVE_VIEW_SIZE_X, CAVE_VIEW_SIZE_Y, cave);

        caveView.setPlayer(player);

        TextBox info = new TextBox(caveView.getX() + caveView.getWidth() + 1, caveView.getY(), 29, caveView.getHeight(), "Info", Arrays.asList(
                "Use [⇦] [⇨] [⇧] [⇩] to move around.",
                "",
                "Press [Esc] To Exit."
        ));

        surface.add(0, caveView);
        surface.add(0, info);
        surface.add(0, new LogView("Log", caveView.getX(), caveView.getY() + caveView.getHeight() + 1, caveView.getWidth() + info.getWidth() - 1, 9));
        surface.add(1, new InventoryView(info.getX() + 2, info.getY() + 22, player.getInventory()));
        surface.add(1, new HealthBar(player, info.getX() + 2, info.getY() + 19, info.getWidth() - 5));
        terminal.exec(Terminal::clearScreen);

        Controls.register(new KeyStroke('6', false, false), stroke -> {
            player.moveEast();
            player.removeHealth(1.0d);
        });
        Controls.register(new KeyStroke('4', false, false), stroke -> player.moveWest());
        Controls.register(new KeyStroke('8', false, false), stroke -> player.moveNorth());
        Controls.register(new KeyStroke('5', false, false), stroke -> player.moveSouth());

        Controls.register(new KeyStroke('7', false, false), stroke -> player.moveNorthWest());
        Controls.register(new KeyStroke('1', false, false), stroke -> player.moveSouthWest());
        Controls.register(new KeyStroke('3', false, false), stroke -> player.moveSouthEast());
        Controls.register(new KeyStroke('9', false, false), stroke -> player.moveNorthEast());
    }

    @Override
    public void update(GameTerminal terminal) throws IOException {
        terminal.draw(surface);
        Controls.trigger(terminal.getTerminal().readInput());
    }

    @Override
    public String getId() {
        return "generate-dungeon-state";
    }
}
