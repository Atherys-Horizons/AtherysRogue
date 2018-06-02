package com.atherys.game.graphics.drawable;

import com.atherys.game.math.MathUtils;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ProgressBar extends TitleBox {

    protected String actualTitle;

    protected double progress;
    protected TextCharacter filler;

    protected double max;

    public ProgressBar(String title, int x, int y, int w, double initialProgress, double max, TextCharacter filler) {
        super(title, x, y, w + 1, 2);
        this.max = max;
        this.filler = filler;
        this.actualTitle = title;

        setProgress(initialProgress);
    }

    private void setProgress(double amount) {
        this.progress = MathUtils.clamp(0.0d, 1.0d, amount);
        this.title = String.format("%s[%.1f/%.1f]", actualTitle, progress*max, max);
    }

    public double getProgress() {
        return progress;
    }

    public void add(double amount) {
        setProgress(progress + amount);
    }

    public void remove(double amount) {
        setProgress(progress - amount);
    }

    @Override
    public void apply(TextGraphics surface) {
        super.apply(surface);
        for ( int i = 0; i < ( w - 1 ) * progress; i++ ) {
            surface.setCharacter(x + 1 + i, y + 1, filler);
        }
    }
}
