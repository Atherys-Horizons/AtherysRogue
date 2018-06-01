package com.atherys.game.state;

import com.atherys.game.AtherysRogue;
import com.atherys.game.cave.Cave;
import com.atherys.game.entity.Location;
import com.atherys.game.graphics.drawable.TextBox;
import com.atherys.game.player.Player;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.CaveView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class MainGameState extends GraphicalState {

    private static final int CAVE_SIZE_X = 256;
    private static final int CAVE_SIZE_Y = 256;
    private static final double CAVE_STONE = 0.46d;
    private static final int CAVE_ITERATIONS = 1;
    private static final int CAVE_WALL_THRESHHOLD = 5;
    private static final int CAVE_FLOOR_THRESHHOLD = 4;

    private static final int CAVE_VIEW_POSITION_X = 1;
    private static final int CAVE_VIEW_POSITION_Y = 1;
    private static final int CAVE_VIEW_RADIUS_X = 50;
    private static final int CAVE_VIEW_RADIUS_Y = 50;

    private Cave cave;

    private CaveView caveView;
    private TextBox info;

    private Player player;

    @Override
    public void start() {
        super.start();
        cave = new Cave(CAVE_SIZE_X, CAVE_SIZE_Y, CAVE_STONE, CAVE_ITERATIONS, CAVE_WALL_THRESHHOLD, CAVE_FLOOR_THRESHHOLD);
        caveView = new CaveView(CAVE_VIEW_POSITION_X, CAVE_VIEW_POSITION_Y, CAVE_VIEW_RADIUS_X, CAVE_VIEW_RADIUS_Y, cave);

        player = new Player(Location.of(cave, 60, 27));
        cave.spawnEntity(player);

        caveView.setPlayer(player);

        info = new TextBox(52, 1, 30, 49, "Info", Arrays.asList(
                "Use [⇦] [⇨] [⇧] [⇩] to move around.",
                "",
                "Press [Esc] To Exit."
        ));

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

            if (keyStroke.getKeyType() == KeyType.ArrowRight) player.moveRight();

            if (keyStroke.getKeyType() == KeyType.ArrowLeft) player.moveLeft();

            if (keyStroke.getKeyType() == KeyType.ArrowDown) player.moveDown();

            if (keyStroke.getKeyType() == KeyType.ArrowUp) player.moveUp();
        }

        terminal.draw(caveView);
        terminal.draw(info);
    }

    @Override
    public String getId() {
        return "generate-dungeon-state";
    }
}
