package com.compasible.Examples.effects;

import com.compasible.GamePanel;
import com.compasible.effect.Effect;

public class ExampleEffect extends Effect {


    public ExampleEffect(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues();
    }

    public void setDefaultValues() {
        name = "Example Effect";
        description = "The Example Effect.";

        time = -1;
    }

    @Override
    public void update() {
    }

    @Override
    public void onGet() {
    }

    @Override
    public void onEnd() {
    }
}