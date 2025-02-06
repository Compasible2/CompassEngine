package com.compasible.ui;

import com.compasible.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class Ui {

    // Main
    public GamePanel gamePanel;
    public int mainX = 0, mainY = 0;
    public boolean enabled;

    // Lists
    private ArrayList<UiElement> uiElements = new ArrayList<>();

    public Ui(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void drawUiElements(Graphics2D g2) {
        if (enabled) {
            for (UiElement element : uiElements) {
                element.draw(g2, mainX, mainY);
            }
        }
    }

    public void update() {
        for (UiElement element : getUiElements()) {
            element.update();
        }
    }

    public void addUiElement(UiElement uiElement) {
        uiElements.add(uiElement);
    }

    public ArrayList<UiElement> getUiElements() {
        return uiElements;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setX(int mainX) {
        this.mainX = mainX;
    }

    public void setY(int mainY) {
        this.mainY = mainY;
    }
}
