package com.compasible.ui;

import com.compasible.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class UiTextButton extends UiElement{

    public int width, height;
    public String id;
    public String string;
    public Font font;
    public Color baseColor;
    public Color textColor;
    public Color holdColor;
    public Color clickColor;

    public int originX;
    public int originY;

    public GamePanel gamePanel;

    private ClickAction clickAction;
    private HoldAction holdAction;

    @FunctionalInterface
    public static interface ClickAction {
        void execute();
    }

    @FunctionalInterface
    public static interface HoldAction {
        void execute();
    }

    public UiTextButton(String id, GamePanel gamePanel) {
        this.id = id;
        this.gamePanel = gamePanel;
        gamePanel.getMouseManager().addClickArea(id, new Rectangle(x + originX, y + originY, width, height));
        gamePanel.getMouseManager().addHoverArea(id, new Rectangle(x + originX, y + originY, width, height));

    }

    @Override
    public void draw(Graphics2D g2, int mainX, int mainY) {
        this.originX = mainX;
        this.originY = mainY;

        AffineTransform afx = new AffineTransform();
        afx.rotate(Math.toRadians(rotation), x + (double) width /2, y + (double) height /2);

        g2.setTransform(afx);

        g2.setColor(color);
        g2.fillRect(x + mainX, y + mainY, width, height);

        g2.setColor(textColor);

        if (font != null) {
            g2.setFont(font);
        }

        if (string != null) {
            g2.drawString(string, x + width/5 - string.length()*2 + mainX, y + height/2 + 6 + mainY);
        }
    }

    @Override
    public void update() {

        gamePanel.getMouseManager().updateClickArea(id, new Rectangle(x + originX, y + originY, width, height));
        gamePanel.getMouseManager().updateHoverArea(id, new Rectangle(x + originX, y + originY, width, height));

        if (gamePanel.getMouseManager().checkClicked(id)) {
            this.color = clickColor;
            click();
        }
        else if (gamePanel.getMouseManager().checkHovered(id)) {
            this.color = holdColor;
            hold();
        }
        else {
            this.color = baseColor;
        }
    }

    public void click() {
        if (clickAction != null) {
            clickAction.execute();
        }
    }

    public void hold() {
        if (holdAction != null) {
            holdAction.execute();
        }
    }

    public void setValues(int x, int y, int width, int height, String string, Color baseColor, Color textColor, Color holdColor, Color clickColor, Font font) {

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.string = string;
        this.font = font;

        this.baseColor = baseColor;
        this.textColor = textColor;
        this.holdColor = holdColor;
        this.clickColor = clickColor;
    }

    public void setClickAction(ClickAction clickAction) {
        this.clickAction = clickAction;
    }
    public void setHoldAction(HoldAction holdAction) {
        this.holdAction = holdAction;
    }
}
