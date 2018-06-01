package com.atherys.game;

import com.atherys.game.state.StartScreenState;
import com.atherys.game.state.State;

public class AtherysRogue {

    private static AtherysRogue instance = new AtherysRogue();

    private State state;

    private boolean closed;

    private AtherysRogue() {
        this.state = new StartScreenState();
    }

    public static AtherysRogue getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        AtherysRogue.getInstance().start();
    }

    public void start() {
        state.start();
        while (!isClosed()) {
            state.update();
        }
        stop();
    }

    public void stop() {
        state.stop();
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
