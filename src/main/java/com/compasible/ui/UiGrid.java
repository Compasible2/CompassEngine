package com.compasible.ui;

import java.awt.*;

public class UiGrid extends UiElement{

    public int maxGridX, maxGridY;
    public int paddingX, paddingY;

    public int gridSize;
    public UiElement[][] grid;

    public UiGrid() {}

    public void setValues(int x, int y, int maxX, int maxY, int padX, int padY, int gridSize) {
        this.x = x;
        this.y = y;

        this.maxGridX = maxX;
        this.maxGridY = maxY;

        this.paddingX = padX;
        this.paddingY = padY;

        this.gridSize = gridSize;

        grid = new UiElement[maxGridY][maxGridX];
    }

    @Override
    public void draw(Graphics2D g2, int mainX, int mainY) {

        int indexY = 0;
        for (UiElement[] uiElements : getUiElements()) {
            int indexX = 0;
            for (UiElement uiElement : uiElements) {

                if (uiElement != null) {
                    uiElement.draw(g2, x + mainX + indexX*gridSize + paddingX * indexX, y + mainY + indexY*gridSize + paddingY * indexY);
                }

                indexX += 1;
            }

            indexY += 1;
        }
    }

    @Override
    public void update() {
        super.update();

        int indexY = 0;
        for (UiElement[] uiElements : getUiElements()) {
            int indexX = 0;
            for (UiElement uiElement : uiElements) {

                if (uiElement != null) {
                    uiElement.update();
                }

                indexX += 1;
            }

            indexY += 1;
        }
    }

    public void addUiElement(int gridX, int gridY, UiElement uiElement) {
        grid[gridY][gridX] = uiElement;
    }

    public void removeUiElement(int gridX, int gridY) {
        grid[gridY][gridX] = null;
    }

    public UiElement[][] getUiElements() {
        return grid;
    }

    public void setMaxGridX(int maxGridX) {
        this.maxGridX = maxGridX;
    }

    public void setMaxGridY(int maxGridY) {
        this.maxGridY = maxGridY;
    }

    public void setPaddingX(int paddingX) {
        this.paddingX = paddingX;
    }

    public void setPaddingY(int paddingY) {
        this.paddingY = paddingY;
    }
}