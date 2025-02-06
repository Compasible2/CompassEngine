package com.compasible.particle;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ParticleText extends ParticleElement{

    public Color color;
    public String string;

    public ParticleText() {}

    @Override
    public void draw(Graphics2D g2, int baseX, int baseY) {
        AffineTransform afx = new AffineTransform();
        afx.rotate(Math.toRadians(rotation), x, y);

        g2.setTransform(afx);

        g2.setColor(color);
        g2.drawString(string, baseX + x, baseY + y);
    }

    public void setValues(Particle owner, String string, int x, int y, int velocityX, int velocityY, int lifetime, Color color) {
        this.owner = owner;

        this.string = string;

        this.x = x;
        this.y = y;

        this.velocityX = velocityX;
        this.velocityY = velocityY;

        this.lifetime = lifetime;

        this.color = color;
    }

    public void setOwner(Particle owner) {
        this.owner = owner;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
