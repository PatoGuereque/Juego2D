package com.patoguereque.juego2d.gameobjects.player;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.renderer.Animation;
import com.patoguereque.juego2d.renderer.Camera;
import com.patoguereque.juego2d.renderer.CameraFrame;
import com.patoguereque.juego2d.util.ImageLoader;
import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerRenderer {

    private final Game game;
    private final Animation idleAnimation;
    private final Animation runningAnimation;
    private final Player player;
    private int tick = 0;

    public PlayerRenderer(Game game, Player player) {
        this.game = game;
        this.player = player;

        BufferedImage spritesheet = ImageLoader.loadImage("/images/player/hero_spritesheet.png");
        idleAnimation = new Animation(spritesheet, 18, 70, 65, 8);
        runningAnimation = new Animation(spritesheet, 32+65+18, 70, 65, 6);
    }

    public void render(CameraFrame camera) {
        tick++;
        Animation animation = (player.isWalking() ? runningAnimation : idleAnimation);

        if (player.isWalking()) {
            if (tick % 4 == 0) {
                animation.nextFrame();
            }
        } else {
            if (tick % 6 == 0) {
                animation.nextFrame();
            }
        }

        camera.render(player, animation.getFrame());
    }
}
