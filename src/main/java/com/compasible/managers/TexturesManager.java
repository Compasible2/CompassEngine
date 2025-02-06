package com.compasible.managers;

import java.awt.image.BufferedImage;

public class TexturesManager {

    BufferedImage[] textures = new BufferedImage[0];

    public TexturesManager() {
        try {

            //textures[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("assets/path/to/.png")));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTexture(int id) {
        return textures[id];
    }
}
