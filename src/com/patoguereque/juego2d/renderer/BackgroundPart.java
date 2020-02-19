package com.patoguereque.juego2d.renderer;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundPart implements Renderable {

    private final Game game;
    private final Player player;
    private int x = 0;
    private final BufferedImage bufferedImage;

    public BackgroundPart(Game game, Player player, BufferedImage bufferedImage) {
        this.game = game;
        this.player = player;
        this.bufferedImage = bufferedImage;
    }

    public void move(int xOffset) {
        x += xOffset;
    }

    @Override
    public void render(Graphics g) {
        x = -player.getX();
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
