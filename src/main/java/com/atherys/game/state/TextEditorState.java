package com.atherys.game.state;

import com.atherys.game.AtherysRogue;
import com.atherys.game.cave.Cave;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.CaveMap;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

/**
 * A testing state used for debugging
 */
public final class TextEditorState extends GraphicalState {

    private Cave cave;
    private boolean caveDrawn;

    @Override
    public void start() {
        super.start();
        terminal.exec(t -> {
            t.clearScreen();
            t.setCursorPosition(0, 0);
            t.setCursorVisible(true);
            t.flush();
        });

        this.cave = new Cave(120, 45, 0.47d, 2);
    }

    @Override
    public void update(GameTerminal terminal) throws IOException {

        if (!caveDrawn) {
            CaveMap map = new CaveMap(0, 0, cave);
            terminal.draw(map);
            caveDrawn = true;
        }

        Terminal t = terminal.getTerminal();

        KeyStroke keyStroke = t.readInput();

        if (keyStroke.getKeyType() == KeyType.Escape) {
            AtherysRogue.getInstance().setClosed(true);
            return;
        }

        if (keyStroke.getKeyType() == KeyType.ArrowRight) moveRight();

        if (keyStroke.getKeyType() == KeyType.ArrowLeft) moveLeft();

        if (keyStroke.getKeyType() == KeyType.ArrowDown) moveDown();

        if (keyStroke.getKeyType() == KeyType.ArrowUp) moveUp();

        if (keyStroke.getKeyType() == KeyType.Backspace) {
            moveLeft();
            t.putCharacter(' ');
            moveLeft();
        }

        if (keyStroke.getKeyType() == KeyType.Character) {
            t.putCharacter(keyStroke.getCharacter());
        }

        t.flush();
    }

    private void move(int horizontalDelta, int verticalDelta) throws IOException {
        terminal.getTerminal().setCursorPosition(terminal.getTerminal().getCursorPosition().withRelative(horizontalDelta, verticalDelta));
    }

    private void moveLeft() throws IOException {
        move(-1, 0);
    }

    private void moveRight() throws IOException {
        move(+1, 0);
    }

    private void moveUp() throws IOException {
        move(0, -1);
    }

    private void moveDown() throws IOException {
        move(0, +1);
    }

    @Override
    public String getId() {
        return "normal-game-state";
    }
}
