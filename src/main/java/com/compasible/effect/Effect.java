package com.compasible.effect;

import com.compasible.GamePanel;
import com.compasible.entity.Entity;

public class Effect {
    public GamePanel gamePanel;
    public String name;
    public String description;
    public int time = -1;

    public Entity owner;

    public boolean got = false;

    public Effect(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void update() {
        if (owner == null) {
            return;
        }

        if (!got) {
            onGet();
        }

        if (time != 0) {
            time -= 1;
        }

        if (time == 0) {
            onEnd();
        }
    }

    public void onGet() {
        got = true;
    }

    public void onEnd() {
        owner.removeEffect(this);
    }
}
