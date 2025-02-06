package com.compasible.Examples.particles;

import com.compasible.particle.Particle;
import com.compasible.particle.ParticleBox;
import com.compasible.particle.ParticleText;

import java.awt.*;
import java.util.Random;

public class ExampleParticle extends Particle {

    public ExampleParticle() {

        Random random = new Random();

        ParticleText particleText = new ParticleText();
        particleText.setValues(this, "Particle!", -20, -20, random.nextInt(-5, 5), random.nextInt(-5, 0), random.nextInt(20), Color.WHITE);
        addParticleElement(particleText);

        for (int i = 0; i < 15; i++) {
            ParticleBox particleBox = new ParticleBox();
            particleBox.setValues(this, 0, 0, random.nextInt(-2, 2), random.nextInt(-2, 2), 3, 3, random.nextInt( 8), Color.WHITE);
            addParticleElement(particleBox);
        }

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;

        lifetime = 100;
    }
}
