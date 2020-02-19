package com.patoguereque.juego2d.gameobjects;

import com.patoguereque.juego2d.util.Renderable;
import com.patoguereque.juego2d.util.Tickable;

public abstract class GameObject implements Tickable, Renderable {

    protected int x;
    protected int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
