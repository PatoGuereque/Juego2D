package com.patoguereque.juego2d.renderer;

import com.patoguereque.juego2d.Game;
import com.patoguereque.juego2d.gameobjects.player.Player;
import com.patoguereque.juego2d.util.ImageLoader;
import com.patoguereque.juego2d.util.Renderable;

import java.awt.*;

public class BackgroundRenderer implements Renderable {

    private final BackgroundPart ground;
    private final BackgroundPart treesAndBushes;
    private final BackgroundPart distantTrees;
    private final BackgroundPart bushes;
    private final BackgroundPart hill1;
    private final BackgroundPart hill2;
    private final BackgroundPart hugeClouds;
    private final BackgroundPart clouds;
    private final BackgroundPart distantClouds1;
    private final BackgroundPart distantClouds2;
    private final BackgroundPart background;
    private final Game game;

    public BackgroundRenderer(Game game, Player player) {
        this.game = game;
        ground = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_01_ground.png"), 1);
        treesAndBushes = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_02_trees and bushes.png"), 1);
        distantTrees = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_03_distant_trees.png"), 0.5);
        bushes = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_04_bushes.png"), 0.5);
        hill1 = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_05_hill1.png"), 0.2);
        hill2 = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_06_hill2.png"), 0.1);
        hugeClouds = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_07_huge_clouds.png"), 0.1);
        clouds = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_08_clouds.png"), 0.3);
        distantClouds1 = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_09_distant_clouds1.png"), 0.2);
        distantClouds2 = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_10_distant_clouds.png"), 0.1);
        background = new BackgroundPart(game, player, ImageLoader.loadImage("/images/background/_11_background.png"), 0);
    }

    int tick = 0;
    @Override
    public void render(Graphics2D g) {
        tick++;
        if (tick % 3 == 0) {
            clouds.move(1);
        }

        if (tick % 4 == 0) {
            distantClouds1.move(1);
        }

        if (tick % 6 == 0) {
            distantClouds2.move(1);
        }

        background.render(g);
        distantClouds2.render(g);
        distantClouds1.render(g);
        clouds.render(g);
        hugeClouds.render(g);
        hill2.render(g);
        hill1.render(g);
        bushes.render(g);
        distantTrees.render(g);
        treesAndBushes.render(g);
        ground.render(g);
    }
}
