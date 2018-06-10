package com.atherys.game.graphics.drawable;

import com.atherys.game.event.EventManager;
import com.atherys.game.event.entity.EntityHealthUpdateEvent;
import com.atherys.game.player.Player;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class HealthBar extends ProgressBar {

    private static final TextCharacter HEALTH_FILLER = new TextCharacter('❤', TextColor.ANSI.RED, TextColor.ANSI.DEFAULT);

    public HealthBar(Player player, int x, int y, int w) {
        super(
                "HP",
                x,
                y,
                w,
                player.getHealth(),
                player.getMaxHealth(),
                HEALTH_FILLER
        );

        EventManager.getInstance().registerEventListener(EntityHealthUpdateEvent.class, event -> this.setProgress(event.getEntity().getHealth()));
    }
}
