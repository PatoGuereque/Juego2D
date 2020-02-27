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
    private final Enemy enemy;
    private int tick = 0;

    public EnemyRenderer(Game game, Enemy enemy) {
        this.game = game;
        this.enemy = enemy;
    }

    public BufferedImage getDrawnImage() {
        tick++;
        //Animation animation = (enemy.isWalking() ? EnemyState.WALKING : EnemyState.IDLING).getAnimation();
        //Animation animation = (enemy.isWalking() ? EnemyState.ATTACKING : EnemyState.APPEARING).getAnimation();
        Animation animation = enemy.getEnemyState().getAnimation();

        if (tick % enemy.getEnemyState().getAnimationSpeed() == 0) {
            animation.nextFrame();
        }
        return animation.getFrame();
    }
}
