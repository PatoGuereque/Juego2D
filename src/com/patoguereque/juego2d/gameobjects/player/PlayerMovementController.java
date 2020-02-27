package com.patoguereque.juego2d.gameobjects.player;

import com.patoguereque.juego2d.util.Tickable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerMovementController implements KeyListener, Tickable {

    public boolean up, down, left, right, space;

    private final boolean[] keys = new boolean[256];

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
    }
}
