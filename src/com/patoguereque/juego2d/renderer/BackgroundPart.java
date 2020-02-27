package com.patoguereque.juego2d.renderer;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundPart implements Renderable {

    private final Game game;
    private final Player player;
    private int offsetX = 0;
    private final BufferedImage bufferedImage;
    private final double speed;

    public BackgroundPart(Game game, Player player, BufferedImage bufferedImage, double speed) {
        this.game = game;
        this.player = player;
        this.bufferedImage = bufferedImage;
        this.speed = speed;
    }

    public void move(int xOffset) {
        offsetX += xOffset;
    }

    @Override
    public void render(Graphics2D g) {
        int x = (int) (-player.getX() * speed);
        x += offsetX;
        x %= game.getWidth();
        if (x == 0) {
            g.drawImage(bufferedImage, x, 0, game.getWidth(), game.getHeight(), null);
        } else {
            g.drawImage(bufferedImage, x, 0, game.getWidth(), game.getHeight(), null);
            int offset = x;
            if (x > 0) {
                offset -= game.getWidth();
            } else {
                offset += game.getWidth();
            }
            g.drawImage(bufferedImage, offset, 0, game.getWidth(), game.getHeight(), null);
        }
    }
}
