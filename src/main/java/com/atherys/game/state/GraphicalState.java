package com.atherys.game.state;

import com.atherys.game.graphics.GameTerminal;

import java.io.IOException;

public abstract class GraphicalState implements State {

    protected GameTerminal terminal;

    @Override
    public void start() {
        terminal = GameTerminal.getInstance();
    }

    @Override
    public void update() {
        try {
            this.update(terminal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        terminal.close();
    }

    public abstract void update(GameTerminal terminal) throws IOException;
}
