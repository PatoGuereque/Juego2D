package com.patoguereque.juego2d.gameobjects;

import com.patoguereque.juego2d.Assets;
import com.patoguereque.juego2d.Game;

import java.awt.*;

public class Player extends GameObject {

    private int direction;
    private int width;
    private int height;
    private Game game;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        if (game.getKeyManager().up) {
            setY(getY() - 1);
        }

        if (game.getKeyManager().down) {
            setY(getY() + 1);
        }

        if (game.getKeyManager().left) {
            setX(getX() - 1);
        }

        if (game.getKeyManager().right) {
            setX(getX() + 1);
        }

        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) {
            setX(-30);
        }

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
}