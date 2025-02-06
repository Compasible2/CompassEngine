package com.compasible.managers;

import com.compasible.GamePanel;

public class CameraManager {

    public int x;
    public int y;

    public Double zoom = 1.0;
    public GamePanel gamePanel;

    public CameraManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        setX(0);
        setY(0);

        setZoom(1.0);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

}
