package com.compasible.particle;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ParticleBox extends ParticleElement{

    public int width, height;
    public Color color;

    public ParticleBox() {}

    @Override
    public void draw(Graphics2D g2, int baseX, int baseY) {
        AffineTransform afx = new AffineTransform();
        afx.rotate(Math.toRadians(rotation), x + (double) width /2, y + (double) height /2);

        g2.setTransform(afx);

        g2.setColor(color);
        g2.fillRect(baseX + x, baseY + y, width, height);
    }

    public void setValues(Particle owner, int x, int y, int velocityX, int velocityY, int width, int height, int lifetime, Color color) {
        this.owner = owner;

        this.x = x;
        this.y = y;

        this.velocityX = velocityX;
        this.velocityY = velocityY;

        this.width = width;
        this.height = height;

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

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
