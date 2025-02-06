package com.compasible.misc;

import java.awt.*;
import java.awt.geom.Path2D;

public class ShapeUtils {
    public static Path2D rectangleToPath(Rectangle rect) {
        Path2D path = new Path2D.Double();
        path.moveTo(rect.x, rect.y);
        path.lineTo(rect.x + rect.width, rect.y);
        path.lineTo(rect.x + rect.width, rect.y + rect.height);
        path.lineTo(rect.x, rect.y + rect.height);
        path.closePath();
        return path;
    }
}
