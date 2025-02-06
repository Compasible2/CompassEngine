package com.compasible.entity;

import com.compasible.GamePanel;
import com.compasible.effect.Effect;
import com.compasible.managers.CameraManager;
import com.compasible.misc.ShapeUtils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {

    // Main
    protected GamePanel gamePanel;
    protected int x, y;
    protected int width, height;
    protected int rotation;
    protected int velocityX, velocityY;
    protected int force;
    protected Color color;

    // Texture
    public BufferedImage texture;
    public int drawPriority = 0;

    // Collision
    protected Rectangle staticCollisionBox;
    Rectangle rawCollisionBox;
    protected Path2D collisionBox;
    public Entity collidingEntity;
    public boolean isColliding;

    // Lists
    public ArrayList<Class<?>> ignoreCollideWith = new ArrayList<>();
    public ArrayList<Effect> effects = new ArrayList<>();
    public ArrayList<Effect> effectsToRemove = new ArrayList<>();

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g2, CameraManager cameraManager) {
        AffineTransform afx = new AffineTransform();
        afx.rotate(Math.toRadians(rotation), x + (double) width /2, y + (double) height /2);

        g2.setTransform(afx);
        double zoom = cameraManager.getZoom();

        g2.scale(zoom, zoom);

        if (texture != null) {
            g2.drawImage(texture, x + cameraManager.getX(), y + cameraManager.getY(), width, height, null);
        }
        else {
            g2.setColor(color);
            g2.fillRect(x + cameraManager.getX(), y + cameraManager.getY(), width, height);
        }
    }

    public void update() {

        if (staticCollisionBox != null) {
            if (rawCollisionBox == null) {
                rawCollisionBox = staticCollisionBox;
            }
            staticCollisionBox.setBounds((x - rawCollisionBox.x) + x, (y - rawCollisionBox.y) + y, (width - rawCollisionBox.width) + width, (height - rawCollisionBox.height) + height);

            collisionBox = ShapeUtils.rectangleToPath(staticCollisionBox);

            AffineTransform afx = new AffineTransform();
            afx.rotate(Math.toRadians(rotation), x + (double) width / 2, y + (double) height / 2);
            collisionBox.transform(afx);
        }

        if (collidingEntity != null) {
            if (!checkCollision(collidingEntity.collisionBox)) {
                isColliding = false;
                collidingEntity = null;
            }
        }

        if (velocityX > 0) {
            x += velocityX;
            velocityX -= force;
        }
        else if (velocityX < 0) {
            x += velocityX;
            velocityX += force;
        }

        if (velocityY > 0) {
            y += velocityY;
            velocityY -= force;
        }
        else if (velocityY < 0) {
            y += velocityY;
            velocityY += force;
        }

        for (Effect effect : effects) {
            if (effect.owner == null) {
                effect.owner = this;
            }

            effect.update();
        }
        effects.removeAll(effectsToRemove);
        effectsToRemove.clear();
    }

    public boolean checkCollision(Path2D r) {
        if (collisionBox == null) { return false; }
        if (r == null) { return false; }

        if (collisionBox.intersects(r.getBounds2D())) {
            return true;
        }
        return false;
    }

    public boolean checkCollision(Rectangle r) {
        if (collisionBox == null) { return false; }

        if (collisionBox.intersects(r)) {
            return true;
        }
        return false;
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void removeEffect(Effect effect) {
        effectsToRemove.add(effect);
    }

    public ArrayList<Effect> getEffects() {
        return new ArrayList<>(effects);
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getRotation() {
        return this.rotation;
    }
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
    public int getVelocityX() {
        return velocityX;
    }
    public int getVelocityY() {
        return velocityY;
    }
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
    public int getForce() {
        return force;
    }
    public void setForce(int force) {
        this.force = force;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Entity getCollidingEntity() {
        return collidingEntity;
    }
    public boolean isColliding() {
        return isColliding;
    }
    public void setCollisionBox(Rectangle staticCollisionBox) {
        this.staticCollisionBox = staticCollisionBox;
    }
    public Path2D getRotatedCollisionBox() {
        return collisionBox;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDrawPriority() {
        return drawPriority;
    }

    public void setDrawPriority(int drawPriority) {
        this.drawPriority = drawPriority;
    }
}
