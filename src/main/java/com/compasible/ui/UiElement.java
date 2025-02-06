package com.compasible.ui;

import java.awt.*;

public class UiElement {

    // Main
    public int x, y;
    public int rotation;
    public Color color;

    public void draw(Graphics2D g2, int mainX, int mainY) {}

    public void update(){}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
