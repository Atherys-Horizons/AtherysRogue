package com.atherys.game.graphics;

import com.atherys.game.Config;
import com.atherys.game.graphics.drawable.Drawable;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.*;

import javax.swing.*;
import java.io.Closeable;
import java.io.IOException;

public class GameTerminal implements Closeable {

    private static GameTerminal instance = new GameTerminal();

    private Terminal terminal;

    private GameTerminal() {
        SwingTerminalFrame swingTerminalFrame = new SwingTerminalFrame(
                "A'therys Adventures: Syrthavon Cavern " + Config.getInstance().getVersion(),
                Config.getInstance().getTerminalSize(),
                TerminalEmulatorDeviceConfiguration.getDefault(),
                SwingTerminalFontConfiguration.getDefault(),
                TerminalEmulatorColorConfiguration.getDefault(),
                TerminalEmulatorAutoCloseTrigger.CloseOnEscape
        );

        swingTerminalFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        swingTerminalFrame.setResizable(false);
        swingTerminalFrame.setCursorVisible(false);
        swingTerminalFrame.setVisible(true);

        terminal = swingTerminalFrame;
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
            //screen.stopScreen();
            terminal.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
