package com.patoguereque.juego2d.renderer;

import com.patoguereque.juego2d.gameobjects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CameraFrame {

    private final Camera camera;
    private final Graphics2D graphics;
    private final double frameX;
    private final double frameY;

    public CameraFrame(Camera camera, Graphics2D graphics, double frameX, double frameY) {
        this.camera = camera;
        this.graphics = graphics;
        this.frameX = frameX;
        this.frameY = frameY;
    }

    public void render(GameObject gameObject, BufferedImage image) {
        double x = gameObject.getX() - this.frameX;
        double y = gameObject.getY() - this.frameY;
        
        if (x > camera.getScrenWidth() || y > camera.getScreenHeight() || x + gameObject.getWidth() < 0 || y + gameObject.getHeight() < 0) {
            return;
        }

        double width = gameObject.getWidth();
        double height = gameObject.getHeight();
        if (gameObject.getDirection() == -1) {
            x += width;
            width *= -1;
        }

        /* draw ball image to the memory image with transformed x/y double values */
        AffineTransform t = new AffineTransform();
        t.translate(x, y); // x/y set here
        t.scale(width/image.getWidth(), height/image.getHeight()); // scale = 1
        graphics.drawImage(image, t, null);

        graphics.drawRect((int)(gameObject.getX() - this.frameX), (int)y, gameObject.getWidth(), gameObject.getHeight());

        //        graphics.drawImage(image, x, y, width, gameObject.getHeight(), null);
    }
}
