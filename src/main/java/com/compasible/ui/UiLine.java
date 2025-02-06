package com.compasible.ui;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class UiLine extends UiElement{

    public int x2, y2;
    public float thickness;

    public UiLine() {}

    @Override
    public void draw(Graphics2D g2, int mainX, int mainY) {
        AffineTransform afx = new AffineTransform();
        afx.rotate(Math.toRadians(rotation), x, y);

        g2.setTransform(afx);

        Stroke oldStroke = g2.getStroke();
        Stroke newStroke = new BasicStroke(thickness);

        g2.setStroke(newStroke);

        g2.setColor(color);
        g2.drawLine(x + mainX, y + mainY, x2 + mainX, y2 + mainY);

        g2.setStroke(oldStroke);
    }

    public void setValues(int x, int y, int toX, int toY, int thickness, Color color) {
        this.x = x;
        this.y = y;

        this.x2 = toX;
        this.y2 = toY;

        this.thickness = thickness;

        this.color = color;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}
