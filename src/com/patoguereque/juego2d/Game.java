package com.patoguereque.juego2d;

import com.patoguereque.juego2d.gameobjects.GameObject;
import com.patoguereque.juego2d.gameobjects.enemy.Enemy;
import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.renderer.BackgroundRenderer;
import com.patoguereque.juego2d.renderer.Camera;
import com.patoguereque.juego2d.renderer.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends JComponent implements Runnable {

    private Graphics2D g2d;
    private Display display;
    private String title;
    private int width;
    private int height;
    private Thread thread;

    private BufferedImage scene;
    private BackgroundRenderer backgroundRenderer;
    private Camera camera;
    private Player player;
    private Enemy enemy;
    private final List<GameObject> gameObjects = new ArrayList<>();

    private volatile boolean running;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.running = false;
        display = new Display(this, "Juego", 800, 500);

        scene = new BufferedImage(width, height, 1);
        g2d = scene.createGraphics();
        //g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void init() {
        Assets.init();
        // player dimensions = 80w x 94h
        player = new Player(0, height - 100, 1, 80, 94, this);
        enemy = new Enemy(width - 300, height - 100, this, player);
        backgroundRenderer = new BackgroundRenderer(this, player);
        camera = new Camera(this, player);
        display.getjFrame().addKeyListener(player.getMovementController());

        gameObjects.add(player);
        gameObjects.add(enemy);
    }

    @Override
    public void run() {
        init();

        double fps = 50.0;
        double tickTime = 1e9 / fps; //second to nanoseconds
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long last = System.nanoTime();
        int currFps = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / tickTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
                currFps++;
                if (now - last > 1e9) {
                    System.out.println(currFps);
                    currFps = 0;
                    last = now;
                }
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

    @Override
    public void paint(Graphics g) {
        g2d.clearRect(0, 0, (int)width, (int)height);

        if (camera != null) {
            backgroundRenderer.render(g2d);
            camera.render(g2d);
        }

        g.drawImage(scene, 0, 0, this);
    }

    private void render() {
        repaint();
    }

    private void tick() {
        player.tick();
        enemy.tick();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
