package com.atherys.game.state;

import com.atherys.game.AtherysRogue;
import com.atherys.game.Config;
import com.atherys.game.control.Controls;
import com.atherys.game.graphics.GameTerminal;
import com.atherys.game.graphics.drawable.Drawable;
import com.atherys.game.graphics.drawable.TextBox;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.Arrays;

public class StartScreenState extends GraphicalState {

    private Drawable startWindow;

    @Override
    public void start() {
        super.start();

        TerminalSize size = Config.getInstance().getTerminalSize();

        startWindow = new TextBox(0, 0, size.getColumns() - 1, size.getRows() - 1, "A'therys Adventures: Syrthavon Cavern", Arrays.asList(
                "In the world of A'therys, there is a place where time does not run in the predictable fashion. Cyclically, reversed, even sideways; Limnas Von has seen fit to wrest time from its forward march deep in the caves of Syrthavon. The cavern seems to draw in adventurers who pass near, beckoning them to what lies within. On any given day, what lies within the cavern may change, both in space and time...",
                "",
                "**********************************************************************************",
                "*                    /   \\              /'\\       _                              *",
                "*\\_..           /'.,/     \\_         .,'   \\     / \\_                            *",
                "*    \\         /            \\      _/       \\_  /    \\     _                     *",
                "*     \\__,.   /              \\    /           \\/.,   _|  _/ \\                    *",
                "*          \\_/                \\  /',.,''\\      \\_ \\_/  \\/    \\                   *",
                "*                           _  \\/   /    ',../',.\\    _/      \\                  *",
                "*             /           _/m\\  \\  /    |         \\  /.,/'\\   _\\                 *",
                "*           _/           /MMmm\\  \\_     |          \\/      \\_/  \\                *",
                "*          /      \\     |MMMMmm|   \\__   \\          \\_       \\   \\_              *",
                "*                  \\   /MMMMMMm|      \\   \\           \\       \\    \\             *",
                "*                   \\  |MMMMMMmm\\      \\___            \\_      \\_   \\            *",
                "*                    \\|MMMMMMMMmm|____.'  /\\_            \\       \\   \\_          *",
                "*                    /'.,___________...,,'   \\            \\   \\        \\         *",
                "*                   /       \\          |      \\    |__     \\   \\_       \\        *",
                "*                 _/        |           \\      \\_     \\     \\    \\       \\_      *",
                "*                /                               \\     \\     \\_   \\        \\     *",
                "*                                                 \\     \\      \\   \\__      \\    *",
                "*                                                  \\     \\_     \\     \\      \\   *",
                "*                                                   |      \\     \\     \\      \\  *",
                "*                                                    \\ms          |            \\ *",
                "**********************************************************************************",
                "",
                "Press [Enter] To Proceed..."
        ));

        Controls.register(new KeyStroke(KeyType.Escape), stroke -> AtherysRogue.getInstance().setClosed(true));
        Controls.register(new KeyStroke(KeyType.Enter), stroke -> {
            Controls.remove(new KeyStroke(KeyType.Enter));
            State newState = new MainGameState();
            newState.start();
            AtherysRogue.getInstance().setState(newState);
        });
    }

    @Override
    public void update(GameTerminal terminal) throws IOException {

        terminal.draw(startWindow);

        Controls.trigger(terminal.getTerminal().pollInput());

        //KeyStroke pressedKey = terminal.getTerminal().pollInput();
//
        //if (pressedKey != null && pressedKey.getKeyType() == KeyType.Enter) {
        //    State newState = new MainGameState();
        //    newState.start();
        //    AtherysRogue.getInstance().setState(newState);
        //}
    }

    @Override
    public String getId() {
        return "start-screen-state";
    }
}
