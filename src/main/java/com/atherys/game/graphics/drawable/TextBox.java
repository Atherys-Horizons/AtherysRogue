package com.atherys.game.graphics.drawable;

import com.atherys.game.utils.StringUtils;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class TextBox extends TitleBox {

    private List<String> contents;

    public TextBox(int x, int y, int w, int h, String title, String contents) {
        super(title, x, y, w, h);
        this.contents = StringUtils.wordWrapLines(contents, w - 2);
    }

    public TextBox(int x, int y, int w, int h, String title, List<String> contents) {
        super(title, x, y, w, h);
        this.contents = contents;
    }

    @Override
    public void apply(TextGraphics surface) {
        super.apply(surface);
        int j = 0;
        for (String line : contents) {
            if (line.length() > w - 2) {
                List<String> sublines = StringUtils.wordWrapLines(line, w - 2 - ( w % 2 == 0 ? 0 : 1 ));

                for (String subline : sublines) {
                    surface.putString(x + 2, y + 2 + j, subline);
                    j++;
                }
            } else {
                surface.putString(x + 2, y + 2 + j, line);
            }

            j++;
        }
    }
}
