package com.patoguereque.juego2d.gameobjects.enemy;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.Collideable;
import com.patoguereque.juego2d.gameobjects.Damageable;
import com.patoguereque.juego2d.gameobjects.GameObject;
import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.renderer.CameraFrame;

public class Enemy extends GameObject implements Collideable, Damageable {

    private EnemyState enemyState = EnemyState.APPEARING;
    private final Player player;
    private final EnemyRenderer enemyRenderer;

    public Enemy(int x, int y, Game game, Player player) {
        super(x, y, (85*200)/308, 85);
        this.player = player;
        this.enemyRenderer = new EnemyRenderer(game, this);
    }

    @Override
    public void render(CameraFrame cameraFrame) {
        cameraFrame.render(this, enemyRenderer.getDrawnImage());
    }

    private double velocity = 0;
    private double maxVelocity = 2;
    private double acceleration = 0.1;
    private double deceleration = 0.8;
    private double maxDistance = 100;
    private boolean walking;
    @Override
    public void tick() {
        if (enemyState == EnemyState.DYING) {
            if (enemyState.getAnimation().isLastFrame()) {
                setEnemyState(EnemyState.DEAD);
            }

            return;
        } else if(enemyState == EnemyState.DEAD) {
            return;
        }

        double distanceX = player.getX() - x;
        double distanceY = player.getY() - y;
        double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
        if (!collide(player)) {
            velocity += acceleration;
            velocity = Math.min(maxVelocity, velocity);
        } else {
            velocity -= deceleration;
            velocity = Math.max(velocity, 0);
        }

        double move = velocity;
        double angle = Math.atan2(distanceY, distanceX);
        double moveX = move * Math.cos(angle);
        walking = Math.abs(moveX) > 0.5;
        if (walking) {
            if (enemyState == EnemyState.WALKING || enemyState.getAnimation().isReset()) {
                direction = moveX < 0 ? 1 : -1;
                double moveY = move * Math.sin(angle);
                x += moveX;
                y += moveY;
                setEnemyState(EnemyState.WALKING);
            }
        } else {
            setEnemyState(EnemyState.ATTACKING);
        }
    }

    public void setEnemyState(EnemyState enemyState) {
        if (this.enemyState == enemyState) {
            return;
        }
        this.enemyState = enemyState;
        this.enemyState.getAnimation().resetFrames();
    }

    public boolean isWalking() {
        return walking;
    }

    @Override
    public int getHeight() {
        return 85;
    }

    @Override
    public int getWidth() {
        return (getHeight()*enemyState.getWidth())/enemyState.getHeight();
    }

    public EnemyState getEnemyState() {
        return enemyState;
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public void setHealth(double newHealth) {

    }

    @Override
    public void damage(double amount) {

    }

    @Override
    public void kill() {
        if (enemyState == EnemyState.DEAD || enemyState == EnemyState.DYING) {
            return;
        }

        setEnemyState(EnemyState.DYING);
    }
}
