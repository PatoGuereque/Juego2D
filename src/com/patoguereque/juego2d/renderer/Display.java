package com.patoguereque.juego2d.renderer;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame jFrame;
    private Canvas canvas;
    private final String title;
    private final int width;
    private final int height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    private void createDisplay() {
        jFrame = new JFrame(title);

        jFrame.setSize(width, height);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        Dimension screenDimensions = new Dimension(width, height);
        canvas = new Canvas();
        canvas.setPreferredSize(screenDimensions);
        canvas.setMaximumSize(screenDimensions);
        canvas.setPreferredSize(screenDimensions);

        jFrame.add(canvas);
        jFrame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
