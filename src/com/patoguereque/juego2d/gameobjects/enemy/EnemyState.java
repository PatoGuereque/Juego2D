package com.patoguereque.juego2d.gameobjects.enemy;

import com.patoguereque.juego2d.renderer.Animation;
import com.patoguereque.juego2d.util.ImageLoader;

import java.awt.image.BufferedImage;

public enum EnemyState {

    APPEARING("appear", 6, 0, 11, 220, 288),
    IDLING("idle", 6, 6),
    WALKING("walk", 6, 10),
    ATTACKING("attack", 8, 0, 7, 372, 324),
    DYING("die", 6, 0, 8, 444, 292),
    DEAD("die", 6, 7, 1, 444, 292);

    private final Animation animation;
    private final int animationSpeed;
    private final int width;
    private final int height;

    EnemyState(String animationName, int animationSpeed, int frames) {
        this(animationName, animationSpeed, 0, frames);
    }

    EnemyState(String animationName, int animationSpeed, int start, int frames) {
        this(animationName, animationSpeed, start, frames, 200, 308);
    }

    EnemyState(String animationName, int animationSpeed, int start, int frames, int width, int height) {
        this.animationSpeed = animationSpeed;
        BufferedImage idleSpritesheet = ImageLoader.loadImage("/images/enemy/" + animationName + ".png");
        animation = new Animation(idleSpritesheet, start, 0, width, height, frames, 0);
        this.height = height;
        this.width = width;
    }

        public Animation getAnimation() {
        return animation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }
}
