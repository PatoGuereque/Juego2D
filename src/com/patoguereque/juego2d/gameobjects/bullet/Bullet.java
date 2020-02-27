package com.patoguereque.juego2d.gameobjects.bullet;

import com.patoguereque.juego2d.gameobjects.GameObject;
import com.patoguereque.juego2d.renderer.CameraFrame;
import com.patoguereque.juego2d.util.ImageLoader;

import java.awt.image.BufferedImage;

public class Bullet extends GameObject {

    private final static BufferedImage BULLET_IMAGE = ImageLoader.loadImage("/images/bullet/bullet_2.png");

    public Bullet(double x, double y, int direction) {
        super(x, y, 30, 30);
        this.direction = direction;
    }

    @Override
    public void render(CameraFrame cameraFrame) {
        cameraFrame.render(this, BULLET_IMAGE);
    }

    @Override
    public void tick() {
        x+= 3 * direction;
    }
}
