package com.compasible.misc;

import java.awt.*;

public class ColorUtils {
    public static Color darkenColor(Color color, float factor) {
        int red = (int) (color.getRed() * factor);
        int green = (int) (color.getGreen() * factor);
        int blue = (int) (color.getBlue() * factor);

        // Ensure the RGB values are within the 0-255 range
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        return new Color(red, green, blue);
    }
}
