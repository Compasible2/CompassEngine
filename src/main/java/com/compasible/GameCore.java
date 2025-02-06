package com.compasible;

public class GameCore {

    GamePanel gamePanel;
    private boolean hasGamePanel = false;

    public GameCore() {}
    public void setupGamePanel(GamePanel gamePanel) {
        if (!hasGamePanel) {
            this.gamePanel = gamePanel;
            hasGamePanel = true;
        }
    }

    public void onInitialize() {
        if (!hasGamePanel) {
            return;
        }
    }

    public void onUpdate() {
        if (!hasGamePanel) {
            return;
        }
    }

}