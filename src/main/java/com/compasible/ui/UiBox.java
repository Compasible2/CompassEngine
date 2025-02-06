package com.compasible.ui;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class UiBox extends UiElement{

    public int width, height;

    public UiBox() {}

    @Override
    public void draw(Graphics2D g2, int mainX, int mainY) {
        AffineTransform afx = new AffineTransform();
        afx.rotate(Math.toRadians(rotation), x + (double) width /2, y + (double) height /2);

        g2.setTransform(afx);

        g2.setColor(color);
        g2.fillRect(x + mainX, y + mainY, width, height);
    }


    public void setValues(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.color = color;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
