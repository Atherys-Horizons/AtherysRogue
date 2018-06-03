package com.atherys.game;

import com.googlecode.lanterna.TerminalSize;

public class Config {

    private static Config instance = new Config();

    public static Config getInstance() {
        return instance;
    }

    public String getVersion() {
        return "v1.0.4-alpha";
    }

    public TerminalSize getTerminalSize() {
        return new TerminalSize(87, 46);
    }

}
