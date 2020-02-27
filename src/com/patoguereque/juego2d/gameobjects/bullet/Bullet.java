package com.patoguereque.juego2d.gameobjects.bullet;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.Collideable;
import com.patoguereque.juego2d.gameobjects.Damageable;
import com.patoguereque.juego2d.gameobjects.GameObject;
import com.patoguereque.juego2d.renderer.CameraFrame;
import com.patoguereque.juego2d.util.ImageLoader;

import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements Collideable {

    private final static BufferedImage BULLET_IMAGE = ImageLoader.loadImage("/images/bullet/bullet_2.png");
    private final Game game;

    public Bullet(Game game, double x, double y, int direction) {
        super(x, y, 30, 30);
        this.game = game;
        this.direction = direction;
    }

    @Override
    public void render(CameraFrame cameraFrame) {
        cameraFrame.render(this, BULLET_IMAGE);
    }

    @Override
    public void tick() {
        x+= 3 * direction;

        for (GameObject gameObject : game.getGameObjects()) {
            if (!(gameObject instanceof Damageable)) {
                continue;
            }

            Damageable entityDamageable = ((Damageable)gameObject);
            if (collide(entityDamageable)) {
                entityDamageable.kill();
                game.getGameObjects().remove(this);
                return;
            }
        }
    }
}
