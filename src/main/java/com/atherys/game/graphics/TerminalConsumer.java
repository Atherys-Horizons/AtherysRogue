package com.atherys.game.graphics;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

@FunctionalInterface
public interface TerminalConsumer {

    void accept(Terminal terminal) throws IOException;

}
