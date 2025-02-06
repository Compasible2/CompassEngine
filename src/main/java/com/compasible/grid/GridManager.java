package com.compasible.grid;

import com.compasible.entity.Entity;
import com.compasible.GamePanel;

public class GridManager {

    // Main
    GamePanel gamePanel;
    public int originX;
    public int originY;

    public int paddingX;
    public int paddingY;

    // Grid
    Entity[][] grid;

    public GridManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        grid = new Entity[gamePanel.getMaxGridY()][gamePanel.getMaxGridX()];

        originX = (gamePanel.getWidth()/2 - (gamePanel.getGridSizeX() * gamePanel.getMaxGridX())/2)-20;
        originY = 20;

        paddingX = 20;
        paddingY = 20;

        setup();
    }

    public void setup() {
    }

    public int[] getGridCoordinates(int x, int y) {
        // Calculate gridX and gridY from x and y coordinates, including padding
        int gridX = (x - originX - paddingX) / gamePanel.getGridSizeX();
        int gridY = (y - originY - paddingY) / gamePanel.getGridSizeY();

        // Return the grid coordinates as a Point
        return new int[] {gridX, gridY};
    }

    public int[] getCoordinatesFromGrid(int gridX, int gridY) {
        // Calculate the x and y coordinates, including padding between grid cells
        int x = (gridX * (gamePanel.getGridSizeX() + paddingX)) + originX;
        int y = (gridY * (gamePanel.getGridSizeY() + paddingY)) + originY;

        // Return the screen coordinates as an array
        return new int[] {x, y};
    }

    public Entity getEntity(int gridX, int gridY) {
        try {
            return grid[gridY][gridX];

        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public Entity[][] getEntities() {
        return grid;
    }

    public void addEntity(Entity entity, int gridX, int gridY) {
        Entity isEmpty = this.grid[gridY][gridY];

        if (isEmpty == null) {
            this.grid[gridY][gridX] = entity;

            int[] posFromGrid = getCoordinatesFromGrid(gridX, gridY);
            entity.setY(posFromGrid[0]);
            entity.setY(posFromGrid[1]);
        }
    }

    public void snapToGrid(Entity entity, int gridX, int gridY) {
        int[] posFromGrid = getCoordinatesFromGrid(gridX, gridY);
        entity.setY(posFromGrid[0]);
        entity.setY(posFromGrid[1]);
    }
}
