package com.atherys.game.graphics.drawable;

import com.atherys.game.player.Player;
import com.atherys.game.utils.ArrayUtils;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Dialog extends TextBox {

    private boolean showing;

    private Runnable yes;
    private Runnable no;

    public Dialog(int x, int y, int w, int h, String title, String contents, Runnable yes, Runnable no) {
        super(x, y, w, h, title, contents);
        this.yes = yes;
        this.no = no;
        this.showing = false;
    }

    public void poll(Player player) {

    }

    @Override
    public void apply(TextGraphics surface) {
        if (showing) {
            ArrayUtils.iterate(x, x + w, 1, y, y + h, 1, (x, y) -> {
                surface.setCharacter(x, y, TextCharacter.DEFAULT_CHARACTER);
            });

            super.apply(surface);
        }
    }
}
