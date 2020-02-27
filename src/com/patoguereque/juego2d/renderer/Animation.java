package com.patoguereque.juego2d.renderer;

import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private final BufferedImage[] frames;
    private int frame = 0;

    public Animation(BufferedImage spriteSheet, int x, int y, int w, int h, int frameCount, int xOffset) {
        frames = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            frames[i] = spriteSheet.getSubimage((x + i) * w + i*xOffset + xOffset   , y * h, w, h);
        }
    }

    public Animation(BufferedImage spriteSheet, int y, int w, int h, int frameCount) {
        frames = new BufferedImage[frameCount];

        for (int i = 0; i < frameCount; i++) {
            frames[i] = spriteSheet.getSubimage((i) * w + i*10 + 5, y, w, h);
        }
    }

    public BufferedImage getFrame() {
        return frames[frame % frames.length];
    }

    public void nextFrame() {
        frame++;
    }

    public boolean isReset() {
        return frame % frames.length == 0;
    }

    public boolean isLastFrame() {
        return frame == frames.length - 1;
    }

    public void resetFrames() {
        frame = 0;
    }
}
