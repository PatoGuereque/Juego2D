package com.patoguereque.juego2d.gameobjects;

public interface Damageable extends Collideable, IGameObject {

    double getHealth();

    void setHealth(double newHealth);

    void damage(double amount);

    void kill();
}
