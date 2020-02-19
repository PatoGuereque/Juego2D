package com.patoguereque.juego2d.gameobjects.player;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.renderer.Animation;
import com.patoguereque.juego2d.util.ImageLoader;
import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerRenderer implements Renderable {

    private final Game game;
    private final Animation idleAnimation;
    private final Animation runningAnimation;
    private final Player player;
    private int tick = 0;

    public PlayerRenderer(Game game, Player player) {
        this.game = game;
        this.player = player;

        BufferedImage spritesheet = ImageLoader.loadImage("/images/player/hero_spritesheet.png");
        idleAnimation = new Animation(spritesheet, 0, 0, player.getWidth(), player.getHeight(), 8);
        runningAnimation = new Animation(spritesheet, 0, 1, player.getWidth(), player.getHeight(), 6);
    }

    @Override
    public void render(Graphics g) {
        tick++;
        int x = player.getX();
        int y = player.getY();
        int width = player.getWidth();
        int height = player.getHeight();
        Animation animation = (player.isWalking() ? runningAnimation : idleAnimation);

        if (x > game.getWidth() - 50) {
            x = game.getWidth() - 50;
        } else if(x < 50) {
            x = 50;
        }

        if (player.getDirection() == -1) {
            x += width;
            width *= -1;
        }

        if (tick % 6 == 0) {
            animation.nextFrame();
        }

        g.drawImage(animation.getFrame(), x, y, width, height, null);
    }
}
