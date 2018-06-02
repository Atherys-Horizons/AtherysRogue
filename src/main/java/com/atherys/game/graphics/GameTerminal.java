package com.atherys.game.graphics;

import com.atherys.game.Config;
import com.atherys.game.graphics.drawable.Drawable;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.SimpleTerminalResizeListener;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;

import java.io.Closeable;
import java.io.IOException;

public class GameTerminal implements Closeable {

    private static GameTerminal instance = new GameTerminal();

    private Terminal terminal;

    public GameTerminal() {
        try {
            DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

            defaultTerminalFactory.setTerminalEmulatorFrameAutoCloseTrigger(TerminalEmulatorAutoCloseTrigger.CloseOnEscape);
            defaultTerminalFactory.setInitialTerminalSize(Config.getInstance().getTerminalSize());
            defaultTerminalFactory.setTerminalEmulatorTitle("A'therys Adventures: Syrthavon Cavern " + Config.getInstance().getVersion());

            terminal = defaultTerminalFactory.createTerminal();
            terminal.addResizeListener(new SimpleTerminalResizeListener(Config.getInstance().getTerminalSize()));
            terminal.setCursorVisible(false);
            //terminal.enterPrivateMode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameTerminal getInstance() {
        return instance;
    }

    public void draw(Drawable drawable) {
        exec(t -> {
            drawable.apply(t.newTextGraphics());
            t.flush();
        });
    }

    public void exec(TerminalConsumer consumer) {
        if (terminal != null) {
            try {
                consumer.accept(terminal);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Terminal getTerminal() {
        return terminal;
    }

    @Override
    public void close() {
        try {
            //terminal.exitPrivateMode();
            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
