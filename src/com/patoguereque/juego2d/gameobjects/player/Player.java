package com.patoguereque.juego2d.gameobjects.player;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.Collideable;
import com.patoguereque.juego2d.gameobjects.GameObject;
import com.patoguereque.juego2d.gameobjects.bullet.Bullet;
import com.patoguereque.juego2d.renderer.CameraFrame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements Collideable {

    private Game game;
    private final PlayerRenderer playerRenderer;
    private final PlayerMovementController movementController = new PlayerMovementController();
    private boolean walking = false;

    public Player(int x, int y, int direction, Game game) {
        super(x, y, 70, 65);
        this.direction = direction;
        this.game = game;
        this.playerRenderer = new PlayerRenderer(game, this);
    }

    @Override
    public void render(CameraFrame cameraFrame) {
        playerRenderer.render(cameraFrame);
    }

    private double velocity = 0;
    private double maxVelocity = 2.5;
    private double acceleration = 0.3;
    private double deceleration = 0.8;
    private boolean shooting = false;

    @Override
    public void tick() {
        movementController.tick();
        walking = false;
        double moveX = 0;
        double moveY = 0;
        if (movementController.up) {
            moveY--;
        }

        if (movementController.down) {
            moveY++;
        }

        if (movementController.left) {
            moveX--;
        }

        if (movementController.right) {
            moveX++;
        }

        if (moveX != 0 || moveY != 0) {
            velocity += acceleration;
            velocity = Math.min(maxVelocity, velocity);
        } else {
            velocity -= deceleration;
            velocity = Math.max(velocity, 0);
        }

        if (movementController.space) {
            if (!shooting) {
                shooting = true;
                game.getGameObjects().add(new Bullet(x + (direction == 1 ? getWidth() - 10 : 0), y + 3, direction));
            }
        } else {
            shooting = false;
        }

        if (velocity == 0) {
            return;
        }

        walking = true;
        moveX*=velocity;
        moveY*=velocity;
        x+=moveX;
        y+=moveY;
        if (Math.abs(moveX) > 0) {
            direction = moveX > 0 ? 1 : -1;
        }

        if (y + 80 >= game.getHeight()) {
            y = game.getHeight() - 80;
        } else if (y <= -20) {
            y = -20;
        }
    }

    public double getVelocity() {
        return velocity;
    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Game getGame() {
        return game;
    }

    public PlayerMovementController getMovementController() {
        return movementController;
    }

    public boolean isWalking() {
        return walking;
    }
}
