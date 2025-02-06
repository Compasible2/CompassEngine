package com.compasible;

import com.compasible.managers.*;
import com.compasible.entity.Entity;
import com.compasible.misc.GameStates;
import com.compasible.particle.Particle;
import com.compasible.ui.Ui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

    // Main
    protected GameCore gameCore;
    protected final int screenWidth = 500;
    protected final int screenHeight = 500;
    protected final int ticks = 100;
    protected Thread gameThread;

    // Grid
    protected final int gridSizeX = 100;
    protected final int gridSizeY = 100;
    protected final int maxGridX = 19;
    protected final int maxGridY = 10;

    // Managers
    protected KeyManager keyManager = new KeyManager();
    protected TexturesManager texturesManager = new TexturesManager();
    protected SoundManager soundManager = new SoundManager();
    protected MouseManager mouseManager = new MouseManager();
    protected CameraManager cameraManager = new CameraManager(this);

    // Game State
    protected int GameState;

    // variables
    protected boolean checkEntityCollision;
    protected boolean updateEntities;
    protected boolean updateBlocks;
    protected boolean updateParticles;
    protected boolean updateUis;

    // Lists
    protected ArrayList<Entity> entities = new ArrayList<>();
    protected ArrayList<Entity> entitiesToRemove = new ArrayList<>();
    protected ArrayList<Entity> entitiesToAdd = new ArrayList<>();
    protected ArrayList<Particle> particles = new ArrayList<>();
    protected ArrayList<Particle> particlesToRemove = new ArrayList<>();
    protected ArrayList<Ui> uis = new ArrayList<>();

    public GamePanel(GameCore gameCore) {
        this.gameCore = gameCore;
        this.gameCore.setupGamePanel(this);

        setupGame();
    }
    public void setupGame() {
        // Setup
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        // Listeners
        this.addKeyListener(keyManager);
        this.addMouseListener(mouseManager);
        this.addMouseMotionListener(mouseManager);
        this.addMouseWheelListener(mouseManager);

        GameState = GameStates.PlayState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        gameCore.onInitialize();

        double drawInterval = (double) 1000000000 / ticks;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {

                update();

                delta--;
            }
            repaint();
        }
    }


    // Main Update
    public void update() {

        // Update Uis
        if (updateUis) {
            for (Ui ui : uis) {
                if (ui.enabled) {
                    ui.update();
                }
            }
        }

        // Entity Update
        if (updateEntities) {
            if (!entities.isEmpty()) {
                for (Entity entity : entities) {
                    for (Entity checkEntity : entities) {
                        if (checkEntity != entity) {

                            // Check Entities Collision
                            if (checkEntityCollision) {
                                if (entity.checkCollision(checkEntity.getRotatedCollisionBox())) {

                                    if (!entity.ignoreCollideWith.isEmpty()) {
                                        for (Class<?> classOfEntity : entity.ignoreCollideWith) {
                                            if (classOfEntity != checkEntity.getClass()) {
                                                entity.isColliding = true;
                                                entity.collidingEntity = checkEntity;

                                                break;
                                            }
                                        }
                                    }
                                    else {
                                        entity.isColliding = true;
                                        entity.collidingEntity = checkEntity;

                                        break;
                                    }
                                }
                            }
                        }
                    }
                    entity.update();
                }
            }

            entities.removeAll(entitiesToRemove);
            entitiesToRemove.clear();

            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
        }

        // Particles Update
        if (updateParticles) {
            // Remove Particles After Lifetime
            if (!particles.isEmpty()) {
                for (Particle particle : particles) {
                    if (particle.toDelete) {
                        removeParticle(particle);
                    }

                    particle.update();
                }
            }
            particles.removeAll(particlesToRemove);
            particlesToRemove.clear();
        }

        gameCore.onUpdate();
    }

    // Main Draw
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Layers Priority:
        // 1. Uis
        // 2. Entities (with priorities)
        // 3. Particles

        if (GameState == GameStates.PlayState || GameState == GameStates.PauseState) {

            // Draw Particles
            for (Particle particle : particles) {
                if (GameState == GameStates.PlayState || GameState == GameStates.PauseState) {
                    particle.drawParticleElements(g2, cameraManager);
                }
            }

            // Draw Entities
            try {
                HashMap<Entity, Integer> entityWithPriority = new HashMap<>();

                for (Entity entity : getEntities()) {
                    entityWithPriority.put(entity, entity.getDrawPriority());
                }

                List<Map.Entry<Entity, Integer>> entryList = new ArrayList<>(entityWithPriority.entrySet());
                entryList.sort(Comparator.comparingInt(Map.Entry::getValue));

                for (Map.Entry<Entity, Integer> entry : entryList) {
                    entry.getKey().draw(g2, cameraManager); // Entity Draw
                }


            } catch (ConcurrentModificationException | NoSuchElementException ignored){}
        }

        // Draw Uis
        for (Ui ui : uis) {
            ui.drawUiElements(g2);
        }

        g2.dispose();
    }

    // Functions
    public void addEntity(Entity entity) {
        entitiesToAdd.add(entity);
    }
    public void removeEntity(Entity entity) {
        entitiesToRemove.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }
    public void removeParticle(Particle particle) {
        particlesToRemove.add(particle);
    }
    public void addUi(Ui ui) {
        uis.add(ui);
    }

    public void setGameState(int gameState) {
        this.GameState = gameState;
    }
    public int getGameState() {
        return this.GameState;
    }

    public void setEntityUpdate(boolean canUpdate) {
        this.updateEntities = canUpdate;
    }

    public boolean getEntityUpdate() {
        return  this.updateEntities;
    }

    public void setParticlesUpdate(boolean canUpdate) {
        this.updateParticles = canUpdate;
    }

    public boolean getParticlesUpdate() {
        return this.updateParticles;
    }

    public void setUisUpdate(boolean canUpdate) {
        this.updateUis = canUpdate;
    }

    public boolean getUisUpdate() {
        return this.updateUis;
    }

    public int getGridSizeX() {
        return gridSizeX;
    }

    public int getGridSizeY() {
        return gridSizeY;
    }

    public int getMaxGridX() {
        return maxGridX;
    }

    public int getMaxGridY() {
        return maxGridY;
    }


    public KeyManager getKeyManager() {
        return keyManager;
    }

    public TexturesManager getTexturesManager() {
        return texturesManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
    public CameraManager getCameraManager() {
        return cameraManager;
    }


}
