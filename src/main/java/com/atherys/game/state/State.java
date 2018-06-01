package com.atherys.game.state;

public interface State {

    String getId();

    void start();

    void update();

    void stop();
}
