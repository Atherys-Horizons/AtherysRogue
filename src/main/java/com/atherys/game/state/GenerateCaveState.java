package com.atherys.game.state;

import com.atherys.game.AtherysRogue;
import com.atherys.game.cave.Cave;
import com.atherys.game.entity.Human;
import com.atherys.game.entity.Location;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.CaveMap;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GenerateCaveState extends GraphicalState {

    private static final int CAVE_SIZE_X = 95;
    private static final int CAVE_SIZE_Y = 35;
    private static final double CAVE_STONE = 0.46d;
    private static final int CAVE_ITERATIONS = 1;

    private static final int CAVE_POSITION_X = 0;
    private static final int CAVE_POSITION_Y = 0;

    private Cave cave;
    private CaveMap map;

    private Human player;

    @Override
    public void start() {
        super.start();
        cave = new Cave(CAVE_SIZE_X, CAVE_SIZE_Y, CAVE_STONE, CAVE_ITERATIONS);
        map = new CaveMap(CAVE_POSITION_X, CAVE_POSITION_Y, cave);

        player = new Human(Location.of(cave, 12, 13));
        cave.spawnEntity(player);
    }

    @Override
    public void update(GameTerminal terminal) throws IOException {

        KeyStroke keyStroke = terminal.getTerminal().readInput();

        if (keyStroke.getKeyType() == KeyType.Escape) {
            AtherysRogue.getInstance().setClosed(true);
            return;
        }

        if (keyStroke.getKeyType() == KeyType.ArrowRight) player.moveRight();

        if (keyStroke.getKeyType() == KeyType.ArrowLeft) player.moveLeft();

        if (keyStroke.getKeyType() == KeyType.ArrowDown) player.moveDown();

        if (keyStroke.getKeyType() == KeyType.ArrowUp) player.moveUp();

        terminal.getTerminal().clearScreen();
        terminal.draw(map);
    }

    @Override
    public String getId() {
        return "generate-dungeon-state";
    }
}
