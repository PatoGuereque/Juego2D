package com.patoguereque.juego2d.gameobjects.enemy;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.renderer.Animation;
import com.patoguereque.juego2d.util.ImageLoader;
import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyRenderer {

    private final Game game;
    private final Animation idleAnimation;
    private final Animation runningAnimation;
    private final Enemy enemy;
    private int tick = 0;

    public EnemyRenderer(Game game, Enemy enemy) {
        this.game = game;
        this.enemy = enemy;

        BufferedImage idleSpritesheet = ImageLoader.loadImage("/images/enemy/idle.png");
        BufferedImage walkingSpritesheet = ImageLoader.loadImage("/images/enemy/walk.png");
        idleAnimation = new Animation(idleSpritesheet, 0, 0, 200, 308, 6);
        runningAnimation = new Animation(walkingSpritesheet, 0, 0, 200, 308, 10);
    }

    public BufferedImage getDrawnImage() {
        tick++;
        Animation animation = (enemy.isWalking() ? runningAnimation : idleAnimation);

        if (tick % 6 == 0) {
            animation.nextFrame();
        }
        return animation.getFrame();
    }
}
