package com.patoguereque.juego2d.gameobjects.player;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.GameObject;

import java.awt.*;

public class Player extends GameObject {

    private int direction;
    private int width;
    private int height;
    private Game game;
    private final PlayerRenderer playerRenderer;
    private final PlayerMovementController movementController = new PlayerMovementController();
    private boolean walking = false;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.playerRenderer = new PlayerRenderer(game, this);
    }

    @Override
    public void render(Graphics g) {
        playerRenderer.render(g);
    }

    @Override
    public void tick() {
        movementController.tick();
        walking = false;
        if (movementController.up) {
            setY(getY() - 1);
            walking = true;
        }

        if (movementController.down) {
            setY(getY() + 1);
            walking = true;
        }

        if (movementController.left) {
            direction = -1;
            setX(getX() - 2);
            walking = true;
        }

        if (movementController.right) {
            direction = 1;
            setX(getX() + 2);
            walking = true;
        }

        /*if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) {
            setX(-30);
        }*/

        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        } else if (getY() <= -20) {
            setY(-20);
        }
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
