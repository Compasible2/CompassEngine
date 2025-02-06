package com.compasible.Examples.entities;

import com.compasible.GamePanel;
import com.compasible.entity.Entity;

import java.awt.*;

public class ExampleEntity extends Entity {


    public ExampleEntity(GamePanel gamePanel) {
        super(gamePanel);

        setDefaultValues();
    }

    public void  setDefaultValues() {
        setX(11);
        setY(10);

        setWidth(20);
        setHeight(20);

        setRotation(0);

        setColor(Color.GREEN);

        setCollisionBox(new Rectangle(x, y, width, height));

        setDrawPriority(0);
    }

    @Override
    public void update() {
        super.update();
    }
}
