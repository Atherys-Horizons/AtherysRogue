package com.atherys.game.state;

import com.atherys.game.cave.Cave;
import com.atherys.game.cave.CaveGenerator;
import com.atherys.game.cave.MaterialDistribution;
import com.atherys.game.cave.material.FloorMaterial;
import com.atherys.game.cave.material.Materials;
import com.atherys.game.cave.material.WallMaterial;
import com.atherys.game.control.Controls;
import com.atherys.game.entity.GroundItem;
import com.atherys.game.entity.Location;
import com.atherys.game.entity.Snake;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.CaveView;
import com.atherys.game.graphics.drawable.InventoryView;
import com.atherys.game.graphics.drawable.LogView;
import com.atherys.game.graphics.drawable.TextBox;
import com.atherys.game.graphics.surface.LayeredSurface;
import com.atherys.game.item.ItemStack;
import com.atherys.game.item.ItemTypes;
import com.atherys.game.math.Vector2i;
import com.atherys.game.player.Player;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class MainGameState extends GraphicalState {

    private static final int CAVE_SEED = 1334;

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
    private LogView log;

    private LayeredSurface surface = new LayeredSurface();

    @Override
    public void start() {
        super.start();

        long before = System.currentTimeMillis();

        CaveGenerator generator = new CaveGenerator(CAVE_SEED, CAVE_MATERIAL_DISTRIBUTION, CAVE_SIZE, CAVE_WALL_THRESHHOLD, CAVE_FLOOR_THRESHHOLD, CAVE_ITERATIONS);
        cave = generator.getCave();

        long after = System.currentTimeMillis();
        System.out.println("It took " + (after - before) + "ms to generate a cave of size " + CAVE_SIZE);

        CaveView caveView = new CaveView(CAVE_VIEW_POSITION_X, CAVE_VIEW_POSITION_Y, CAVE_VIEW_SIZE_X, CAVE_VIEW_SIZE_Y, cave);

        player = new Player(Location.of(cave, 60, 27), (CAVE_VIEW_SIZE_Y / 2));
        cave.spawnEntity(player);
        cave.spawnEntity(new Snake(Location.of(cave, 59, 26)));
        cave.spawnEntity(
                new GroundItem(
                        new ItemStack(
                                ItemTypes.DAGGER,
                                "An dagger",
                                "This is an dagger",
                                1
                        ),
                        Location.of(cave, 61, 28)
                )
        );

        caveView.setPlayer(player);

        TextBox info = new TextBox(caveView.getX() + caveView.getWidth() + 1, caveView.getY(), 29, caveView.getHeight(), "Info", Arrays.asList(
                "Use [⇦] [⇨] [⇧] [⇩] to move around.",
                "",
                "Press [Esc] To Exit."
        ));

        log = new LogView("Log", caveView.getX(), caveView.getY() + caveView.getHeight() + 1, caveView.getWidth() + info.getWidth() - 1, 9);

        //compactHP = new CompactProgressBar("HP", 55, 10, 27, 1.0d, 69, new TextCharacter('♥', TextColor.ANSI.RED, TextColor.ANSI.BLACK));
        //HP = new ProgressBar("HP", 55, 12, 27, 1.0d, 69, new TextCharacter('♥', TextColor.ANSI.RED, TextColor.ANSI.BLACK));

        surface.add(0, caveView);
        surface.add(0, info);
        surface.add(0, log);
        surface.add(1, new InventoryView(info.getX() + 2, info.getY() + 22, player.getInventory()));
        terminal.exec(Terminal::clearScreen);

        Controls.register(new KeyStroke(KeyType.ArrowRight), stroke -> {
            player.moveRight();
            log.push("Moved Right.");
        });

        Controls.register(new KeyStroke(KeyType.ArrowLeft), stroke -> {
            player.moveLeft();
            log.push("Moved Left.");
        });

        Controls.register(new KeyStroke(KeyType.ArrowUp), stroke -> {
            player.moveUp();
            log.push("Moved Up.");
        });

        Controls.register(new KeyStroke(KeyType.ArrowDown), stroke -> {
            player.moveDown();
            log.push("Moved Down.");
        });
    }

    @Override
    public void update(GameTerminal terminal) throws IOException {

        Controls.trigger(terminal.getTerminal().pollInput());

        terminal.draw(surface);

        //terminal.draw(caveView);
        //terminal.draw(info);
        //terminal.draw(console);

        //terminal.draw(compactHP);
        //terminal.draw(HP);
    }

    @Override
    public String getId() {
        return "generate-dungeon-state";
    }
}
