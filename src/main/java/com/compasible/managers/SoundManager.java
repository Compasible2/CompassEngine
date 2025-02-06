package com.compasible.managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundManager {

    Clip clip;
    URL[] soundURL = new URL[0];

    public SoundManager() {
        //soundURL[0] = getClass().getResource("assets/path/to/sound");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception ignored) {
        }
    }

    public void play(int i) {
        setFile(i);
        clip.start();
    }

    public void loop(int i) {
        setFile(i);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(int i) {
        setFile(i);
        clip.stop();
    }
}
