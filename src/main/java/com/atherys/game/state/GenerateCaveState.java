package com.atherys.game.state;

import com.atherys.game.AtherysRogue;
import com.atherys.game.cave.Cave;
import com.atherys.game.entity.Location;
import com.atherys.game.entity.Player;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.CaveMap;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GenerateCaveState extends GraphicalState {

    private static final int CAVE_SIZE_X = 120;
    private static final int CAVE_SIZE_Y = 45;
    private static final double CAVE_STONE = 0.46d;
    private static final int CAVE_ITERATIONS = 1;

    private static final int CAVE_POSITION_X = 0;
    private static final int CAVE_POSITION_Y = 0;

    private Cave cave;
    private CaveMap graphics;

    private Player player;

    @Override
    public void start() {
        super.start();
        cave = new Cave(CAVE_SIZE_X, CAVE_SIZE_Y, CAVE_STONE, CAVE_ITERATIONS);
        graphics = new CaveMap(CAVE_POSITION_X, CAVE_POSITION_Y, cave);

        player = new Player(Location.of(cave, 60, 27));
        cave.spawnEntity(player);
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

        terminal.draw(graphics);
    }

    @Override
    public String getId() {
        return "generate-dungeon-state";
    }
}
