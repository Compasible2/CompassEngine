package com.compasible.managers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

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
