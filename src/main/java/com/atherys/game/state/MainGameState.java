package com.atherys.game.state;

import com.atherys.game.AtherysRogue;
import com.atherys.game.cave.Cave;
import com.atherys.game.cave.CaveGenerator;
import com.atherys.game.cave.MaterialDistribution;
import com.atherys.game.cave.material.Materials;
import com.atherys.game.entity.Location;
import com.atherys.game.entity.Snake;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.CaveView;
import com.atherys.game.graphics.drawable.Log;
import com.atherys.game.graphics.drawable.TextBox;
import com.atherys.game.graphics.drawable.TitleBox;
import com.atherys.game.math.Vector2i;
import com.atherys.game.player.Player;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class MainGameState extends GraphicalState {

    private static final Vector2i CAVE_SIZE = Vector2i.of(254, 256);
    private static final int CAVE_ITERATIONS = 2;
    private static final int CAVE_WALL_THRESHHOLD = 5;
    private static final int CAVE_FLOOR_THRESHHOLD = 4;
    private static final MaterialDistribution CAVE_MATERIAL_DISTRIBUTION =
            MaterialDistribution
            .of(Materials.STONE_WALL, 0.49d)
            .add(Materials.STONE_FLOOR, 0.51d);

    private static final int CAVE_VIEW_POSITION_X = 1;
    private static final int CAVE_VIEW_POSITION_Y = 1;
    private static final int CAVE_VIEW_SIZE_X = 53;
    private static final int CAVE_VIEW_SIZE_Y = 31;

    private Cave cave;

    private TitleBox viewBox;
    private CaveView caveView;
    private TextBox info;
    private Log console;

    private Player player;

    @Override
    public void start() {
        super.start();

        long before = System.currentTimeMillis();

        CaveGenerator generator = new CaveGenerator(1337, CAVE_MATERIAL_DISTRIBUTION, CAVE_SIZE, CAVE_WALL_THRESHHOLD, CAVE_FLOOR_THRESHHOLD, CAVE_ITERATIONS);
        cave = generator.getCave();

        long after = System.currentTimeMillis();
        System.out.println("It took " + (after - before) + "ms to generate a cave of size " + CAVE_SIZE);

        caveView = new CaveView(CAVE_VIEW_POSITION_X, CAVE_VIEW_POSITION_Y, CAVE_VIEW_SIZE_X, CAVE_VIEW_SIZE_Y, cave);

        player = new Player(Location.of(cave, 60, 27), (CAVE_VIEW_SIZE_Y / 2));
        cave.spawnEntity(player);
        cave.spawnEntity(new Snake(Location.of(cave, 59, 26)));

        caveView.setPlayer(player);

        viewBox = new TitleBox("View", CAVE_VIEW_POSITION_X - 1, CAVE_VIEW_POSITION_Y - 1, CAVE_VIEW_SIZE_X + 1, CAVE_VIEW_SIZE_Y + 1);

        info = new TextBox(viewBox.getX() + viewBox.getWidth() + 1, viewBox.getY(), 30, viewBox.getHeight(), "Info", Arrays.asList(
                "Use [⇦] [⇨] [⇧] [⇩] to move around.",
                "",
                "Press [Esc] To Exit."
        ));

        console = new Log("Log", viewBox.getX(), viewBox.getY() + viewBox.getHeight() + 1, viewBox.getWidth() + info.getWidth() - 1, 10);

        terminal.exec(Terminal::clearScreen);
    }

    @Override
    public void update(GameTerminal terminal) throws IOException {

        KeyStroke keyStroke = terminal.getTerminal().pollInput();

        if (keyStroke != null) {
            if (keyStroke.getKeyType() == KeyType.Escape) {
                AtherysRogue.getInstance().setClosed(true);
                return;
            }

            if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                player.moveRight();
                console.push("Moved Right.");
            }

            if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                player.moveLeft();
                console.push("Moved Left.");
            }

            if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                player.moveDown();
                console.push("Moved Down.");
            }

            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                player.moveUp();
                console.push("Moved Up.");
            }
        }

        terminal.draw(viewBox);
        terminal.draw(caveView);
        terminal.draw(info);
        terminal.draw(console);
    }

    @Override
    public String getId() {
        return "generate-dungeon-state";
    }
}
