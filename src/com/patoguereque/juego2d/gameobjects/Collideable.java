package com.patoguereque.juego2d.gameobjects;

public interface Collideable extends IGameObject {

    default boolean collide(Collideable collideable) {
        if (isDead() || collideable.isDead()) {
            return false;
        }

        int thisWidth = this.getWidth();
        int thisHeight = this.getHeight();
        int otherWidth = collideable.getWidth();
        int otherHeight = collideable.getHeight();
        if (otherWidth <= 0 || otherHeight <= 0 || thisWidth <= 0 || thisHeight <= 0) {
            return false;
        }

        double thisX = this.getX();
        double thisY = this.getY();
        double otherX = collideable.getX();
        double otherY = collideable.getY();
        otherWidth += otherX;
        otherHeight += otherY;
        thisWidth += thisX;
        thisHeight += thisY;
        return ((otherWidth < otherX || otherWidth > thisX) &&
                (otherHeight < otherY || otherHeight > thisY) &&
                (thisWidth < thisX || thisWidth > otherX) &&
                (thisHeight < thisY || thisHeight > otherY));
    }

}
