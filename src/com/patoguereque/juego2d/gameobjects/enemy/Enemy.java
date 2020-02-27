package com.patoguereque.juego2d.gameobjects.enemy;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.Collideable;
import com.patoguereque.juego2d.gameobjects.GameObject;
import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.renderer.CameraFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends GameObject implements Collideable {

    private final Player player;
    private final EnemyRenderer enemyRenderer;

    public Enemy(int x, int y, Game game, Player player) {
        super(x, y, (85*200)/308, 85);
        this.player = player;
        this.enemyRenderer = new EnemyRenderer(game, this);
    }

    @Override
    public void render(CameraFrame cameraFrame) {
        cameraFrame.render(this, enemyRenderer.getDrawnImage());
    }

    private double velocity = 0;
    private double maxVelocity = 2;
    private double acceleration = 0.1;
    private double deceleration = 0.8;
    private double maxDistance = 100;
    private boolean walking;
    @Override
    public void tick() {
        double distanceX = player.getX() - x;
        double distanceY = player.getY() - y;
        double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
        if (distance > maxDistance) {
            velocity += acceleration;
            velocity = Math.min(maxVelocity, velocity);
        } else {
            velocity -= deceleration;
            velocity = Math.max(velocity, 0);
        }

        double move = velocity;
        double angle = Math.atan2(distanceY, distanceX);
        double moveX = move * Math.cos(angle);
        walking = Math.abs(moveX) > 0.5;
        if (walking) {
            direction = moveX < 0 ? 1 : -1;
        }
        double moveY = move * Math.sin(angle);
        x += moveX;
        y += moveY;
    }


    public boolean isWalking() {
        return walking;
    }
}
