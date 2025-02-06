package com.compasible.particle;

import java.awt.*;

public class ParticleElement {

    public int x, y;

    public int rotation;
    public int velocityX, velocityY;
    public int lifetime;

    public Particle owner;

    public void draw(Graphics2D g2, int baseX, int baseY) {}

    int timer = 0;
    public void update() {
        x += velocityX;
        y += velocityY;

        if (timer < lifetime) {
            timer += 1;
        }
        else {
            owner.removeParticleElement(this);
        }
    }
}