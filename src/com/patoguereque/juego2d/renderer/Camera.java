package com.patoguereque.juego2d.renderer;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.GameObject;
import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Camera implements Renderable {

    private final Game game;
    private final Player player;
    private final int sizeOffset;
    private final int screnWidth;
    private final int screenHeight;
    private double x = 0;
    private double y = 0;

    public Camera(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.screnWidth = game.getWidth();
        this.screenHeight = game.getHeight();
        this.sizeOffset = game.getWidth() / 2;
    }

    @Override
    public void render(Graphics2D graphics) {
        x = player.getX() - sizeOffset;
        CameraFrame cameraFrame = new CameraFrame(this, graphics, x, y);
        for (GameObject gameObject : game.getGameObjects()) {
            gameObject.render(cameraFrame);
        }
    }

    public int getSizeOffset() {
        return sizeOffset;
    }

    public int getScrenWidth() {
        return screnWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
