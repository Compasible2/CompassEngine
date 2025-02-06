package com.compasible;

import javax.swing.*;

public class CompassEngine {

    public static JFrame window;

    public static JFrame createWindow(GameCore gameCore) {
        System.setProperty("sun.java2d.opengl", "true");

        window = new JFrame();

        window.setResizable(false);
        window.setTitle("Compass Engine");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GamePanel gamePanel = new GamePanel(gameCore);
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);

        window.setVisible(true);

        gamePanel.startGameThread();

        return window;
    }
}