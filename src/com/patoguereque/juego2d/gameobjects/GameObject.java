package com.patoguereque.juego2d.gameobjects;

import com.patoguereque.juego2d.renderer.CameraFrame;

import java.awt.image.BufferedImage;

public abstract class GameObject implements IGameObject {

    protected double x;
    protected double y;
    protected final int width;
    protected final int height;
    protected int direction;

    public GameObject(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    public abstract void render(CameraFrame cameraFrame);

    public int getDirection() {
        return direction;
    }
}
