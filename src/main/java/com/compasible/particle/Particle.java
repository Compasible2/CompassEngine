package com.compasible.particle;

import com.compasible.managers.CameraManager;

import java.awt.*;
import java.util.ArrayList;

public class Particle {

    // Main
    public int x, y;
    public int lifetime;
    public boolean toDelete;

    // Lists
    public ArrayList<ParticleElement> particleElements = new ArrayList<>();
    public ArrayList<ParticleElement> particleElementsToRemove = new ArrayList<>();

    // Other
    int timer = 0;

    public Particle() {}

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {

        if (timer < lifetime && !toDelete) {
            timer += 1;
        }
        else if (!toDelete) {
            toDelete = true;
        }

        for (ParticleElement element : particleElements) {
            element.update();
        }
    }

    public void addParticleElement(ParticleElement particleElement) {
        particleElements.add(particleElement);
    }

    public void removeParticleElement(ParticleElement particleElement) {
        particleElementsToRemove.add(particleElement);
    }

    public ArrayList<ParticleElement> getParticleElements() {
        return particleElements;
    }

    public void drawParticleElements(Graphics2D g2, CameraManager cameraManager) {
        for (ParticleElement element : particleElements) {
            element.draw(g2, x + cameraManager.getX(), y + cameraManager.getY());
        }
        particleElements.removeAll(particleElementsToRemove);
        particleElementsToRemove.clear();
    }
}
