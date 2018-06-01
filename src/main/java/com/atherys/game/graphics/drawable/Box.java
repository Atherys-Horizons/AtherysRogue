package com.atherys.game.graphics.drawable;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Box implements Drawable {

    public static final char HORIZONTAL_BORDER = '─';
    public static final char VERTICAL_BORDER = '│';

    public static final char NW_CORNER = '╔';
    public static final char NE_CORNER = '╗';
    public static final char SE_CORNER = '╝';
    public static final char SW_CORNER = '╚';

    protected int x;
    protected int y;
    protected int w;
    protected int h;

    public Box(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void apply(TextGraphics surface) {
        // west edge
        surface.drawLine(
                x,
                y + 1,
                x,
                y + h - 1,
                VERTICAL_BORDER
        );
        // north edge
        surface.drawLine(
                x + 1,
                y,
                x + w - 1,
                y,
                HORIZONTAL_BORDER
        );
        // east edge
        surface.drawLine(
                x + w,
                y + 1,
                x + w,
                y + h - 1,
                VERTICAL_BORDER
        );
        // south edge
        surface.drawLine(
                x + 1,
                y + h,
                x + w - 1,
                y + h,
                HORIZONTAL_BORDER
        );

        // nw corner
        surface.setCharacter(x, y, NW_CORNER);

        // ne corner
        surface.setCharacter(x + w, y, NE_CORNER);

        // sw corner
        surface.setCharacter(x, y + h, SW_CORNER);

        // se corner
        surface.setCharacter(x + w, y + h, SE_CORNER);
    }
}
