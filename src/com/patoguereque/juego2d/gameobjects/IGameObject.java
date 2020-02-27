package com.patoguereque.juego2d.gameobjects;

import com.patoguereque.juego2d.util.Renderable;
import com.patoguereque.juego2d.util.Tickable;

public interface IGameObject extends Tickable {
    double getX();

    double getY();

    int getWidth();

    int getHeight();

    void setX(double x);

    void setY(double y);

    boolean isDead();
}
