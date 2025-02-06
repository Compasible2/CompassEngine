package com.compasible.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyManager implements KeyListener {

    public HashMap<Integer, Boolean> keysPressed = new HashMap<>();

    public KeyManager() {
        // Initialize all possible key codes with false
        for (int i = KeyEvent.VK_0; i <= KeyEvent.VK_Z; i++) {
            keysPressed.put(i, false);
        }

        // Add other key codes (e.g., arrow keys, function keys, etc.)
        int[] otherKeys = {
                KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                KeyEvent.VK_SHIFT, KeyEvent.VK_CONTROL, KeyEvent.VK_ALT,
                KeyEvent.VK_SPACE, KeyEvent.VK_ENTER, KeyEvent.VK_ESCAPE
        };

        for (int key : otherKeys) {
            keysPressed.put(key, false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        keysPressed.put(code, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        keysPressed.put(code, false);
    }
}
