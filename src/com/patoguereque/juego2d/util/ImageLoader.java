package com.patoguereque.juego2d.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageLoader {

    public static BufferedImage loadImage(String path) {
        BufferedImage bufferedImage = null;
        try {
            System.out.println(ImageLoader.class.getResource(path));
            bufferedImage = ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, "Could not load image " + path + "!", e);
            System.exit(-1);
        }

        return bufferedImage;
    }

}
