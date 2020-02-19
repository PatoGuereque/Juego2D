package com.patoguereque.juego2d;

import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.renderer.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    private String title;
    private int width;
    private int height;
    private Thread thread;

    private Player player;

    private volatile boolean running;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.running = false;
    }

    private void init() {
        this.display = new Display(title, width, height);
        Assets.init();
        // player dimensions = 80w x 94h
        player = new Player(0, height - 100, 1, 80, 94, this);
        display.getjFrame().addKeyListener(player.getMovementController());
    }

    @Override
    public void run() {
        init();

        int fps = 50;
        double tickTime = 1e9 / fps; //second to nanoseconds
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / tickTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }

        stop();
    }

    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    private void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            bs.show();
            g.dispose();
        }
    }

    private void tick() {
        player.tick();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
