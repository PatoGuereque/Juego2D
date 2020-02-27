package com.patoguereque.juego2d.renderer;

import com.patoguereque.juego2d.Game;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame jFrame;
    private Canvas canvas;
    private final String title;
    private final int width;
    private final int height;

    public Display(Game game, String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay(game);
    }

    private void createDisplay(Game game) {
        jFrame = new JFrame(title);

        jFrame.setSize(width, height);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        Dimension screenDimensions = new Dimension(width, height);
        //canvas = new Canvas();
        game.setPreferredSize(screenDimensions);
        game.setMaximumSize(screenDimensions);
        game.setPreferredSize(screenDimensions);

        jFrame.add(game);
        jFrame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
