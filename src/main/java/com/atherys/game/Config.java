package com.atherys.game;

import com.googlecode.lanterna.TerminalSize;

public class Config {

    private static Config instance = new Config();

    public static Config getInstance() {
        return instance;
    }

    public TerminalSize getTerminalSize() {
        return new TerminalSize(120, 45);
    }

}
