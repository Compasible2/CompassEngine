package com.compasible.ui;

import com.compasible.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class UiTextureButton extends UiElement{

    public int width, height;
    public String id;
    public BufferedImage texture;
    public Color baseColor;
    public Color holdColor;
    public Color clickColor;

    public int originX;
    public int originY;

    public GamePanel gamePanel;

    private ClickAction clickAction;
    private HoldAction holdAction;

    @FunctionalInterface
    public interface ClickAction {
        void execute();
    }

    @FunctionalInterface
    public interface HoldAction {
        void execute();
    }

    public UiTextureButton(String id, GamePanel gamePanel) {
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

        if (texture != null) {
            g2.drawImage(texture, x + mainX, y + mainY, width, height, null);
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

    public void setValues(BufferedImage texture, int x, int y, int width, int height, Color baseColor, Color holdColor, Color clickColor) {
        this.texture = texture;

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;


        this.baseColor = baseColor;
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
