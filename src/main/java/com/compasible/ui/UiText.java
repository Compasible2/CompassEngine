package com.compasible.ui;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class UiText extends UiElement{

    public String string;

    public Font font;

    public UiText() {}

    @Override
    public void draw(Graphics2D g2, int mainX, int mainY) {
        AffineTransform afx = new AffineTransform();
        afx.rotate(Math.toRadians(rotation), x, y);

        g2.setTransform(afx);

        g2.setColor(color);

        if (font != null) {
            g2.setFont(font);
        }

        if (string != null) {
            g2.drawString(string, x + mainX, y + mainY);
        }
    }


    public void setValues(int x, int y, String string, Color color) {
        this.x = x;
        this.y = y;

        this.string = string;

        this.color = color;
    }

    public void setValues(int x, int y, String string, Color color, Font font) {
        this.x = x;
        this.y = y;

        this.string = string;

        this.color = color;

        this.font = font;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
