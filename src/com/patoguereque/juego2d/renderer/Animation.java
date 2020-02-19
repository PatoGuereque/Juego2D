package com.patoguereque.juego2d.renderer;

import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private final BufferedImage[] frames;
    private int frame = 0;

    public Animation(BufferedImage spriteSheet, int x, int y, int w, int h, int frameCount) {
        frames = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            frames[i] = spriteSheet.getSubimage((x + i) * w, y * h, w, h);
        }
    }

    public BufferedImage getFrame() {
        return frames[frame % frames.length];
    }

    public void nextFrame() {
        frame++;
    }

    public void resetFrames() {
        frame = 0;
    }
}
