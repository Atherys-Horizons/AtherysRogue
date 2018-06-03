package com.atherys.game.state;

import com.atherys.game.cave.Cave;
import com.atherys.game.cave.CaveGenerator;
import com.atherys.game.cave.MaterialDistribution;
import com.atherys.game.cave.material.Materials;
import com.atherys.game.control.Controls;
import com.atherys.game.entity.Location;
import com.atherys.game.entity.Snake;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.CaveView;
import com.atherys.game.graphics.drawable.Log;
import com.atherys.game.graphics.drawable.TextBox;
import com.atherys.game.math.Vector2i;
import com.atherys.game.player.Player;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class MainGameState extends GraphicalState {

    private static final int CAVE_SEED = 9001;

    private static final Vector2i CAVE_SIZE = Vector2i.of(254, 256);
    private static final int CAVE_ITERATIONS = 2;
    private static final int CAVE_WALL_THRESHHOLD = 5;
    private static final int CAVE_FLOOR_THRESHHOLD = 4;
    private static final MaterialDistribution CAVE_MATERIAL_DISTRIBUTION =
            MaterialDistribution
            .of(Materials.STONE_WALL, 0.49d)
            .add(Materials.STONE_FLOOR, 0.51d);

    private static final int CAVE_VIEW_POSITION_X = 0;
    private static final int CAVE_VIEW_POSITION_Y = 0;
    private static final int CAVE_VIEW_SIZE_X = 55;
    private static final int CAVE_VIEW_SIZE_Y = 33;

    private Cave cave;

    private CaveView caveView;
    private TextBox info;
    private Log console;

    //private CompactProgressBar compactHP;
    //private ProgressBar HP;

    private Player player;

    @Override
    public void start() {
        super.start();

        long before = System.currentTimeMillis();

        CaveGenerator generator = new CaveGenerator(CAVE_SEED, CAVE_MATERIAL_DISTRIBUTION, CAVE_SIZE, CAVE_WALL_THRESHHOLD, CAVE_FLOOR_THRESHHOLD, CAVE_ITERATIONS);
        cave = generator.getCave();

        long after = System.currentTimeMillis();
        System.out.println("It took " + (after - before) + "ms to generate a cave of size " + CAVE_SIZE);

        caveView = new CaveView(CAVE_VIEW_POSITION_X, CAVE_VIEW_POSITION_Y, CAVE_VIEW_SIZE_X, CAVE_VIEW_SIZE_Y, cave);

        player = new Player(Location.of(cave, 60, 27), (CAVE_VIEW_SIZE_Y / 2));
        cave.spawnEntity(player);
        cave.spawnEntity(new Snake(Location.of(cave, 59, 26)));

        caveView.setPlayer(player);

        info = new TextBox(caveView.getX() + caveView.getWidth() + 1, caveView.getY(), 30, caveView.getHeight(), "Info", Arrays.asList(
                "Use [⇦] [⇨] [⇧] [⇩] to move around.",
                "",
                "Press [Esc] To Exit."
        ));

        console = new Log("Log", caveView.getX(), caveView.getY() + caveView.getHeight() + 1, caveView.getWidth() + info.getWidth() - 1, 10);

        //compactHP = new CompactProgressBar("HP", 55, 10, 27, 1.0d, 69, new TextCharacter('♥', TextColor.ANSI.RED, TextColor.ANSI.BLACK));
        //HP = new ProgressBar("HP", 55, 12, 27, 1.0d, 69, new TextCharacter('♥', TextColor.ANSI.RED, TextColor.ANSI.BLACK));

        terminal.exec(Terminal::clearScreen);

        Controls.register(new KeyStroke(KeyType.ArrowRight), stroke -> {
            player.moveRight();
            console.push("Moved Right.");
        });

        Controls.register(new KeyStroke(KeyType.ArrowLeft), stroke -> {
            player.moveLeft();
            console.push("Moved Left.");
        });

        Controls.register(new KeyStroke(KeyType.ArrowUp), stroke -> {
            player.moveUp();
            console.push("Moved Up.");
        });

        Controls.register(new KeyStroke(KeyType.ArrowDown), stroke -> {
            player.moveDown();
            console.push("Moved Down.");
        });
    }

    @Override
    public void update(GameTerminal terminal) throws IOException {

        Controls.trigger(terminal.getTerminal().pollInput());

        terminal.draw(caveView);
        terminal.draw(info);
        terminal.draw(console);

        //terminal.draw(compactHP);
        //terminal.draw(HP);
    }

    @Override
    public String getId() {
        return "generate-dungeon-state";
    }
}
