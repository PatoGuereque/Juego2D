package com.patoguereque.juego2d;

import com.patoguereque.juego2d.util.ImageLoader;

import java.awt.*;

public class Assets {

    public static Image background;

    private Assets() {}

    public static void init() {
        background = ImageLoader.loadImage("/images/background/_11_background.png");
    }

}
