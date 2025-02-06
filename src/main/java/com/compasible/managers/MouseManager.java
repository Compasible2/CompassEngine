package com.compasible.managers;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

    public boolean leftClicked, leftHolding;
    public boolean rightClicked, rightHolding;
    public int mouseX, mouseY;
    public int lastMouseX = -1, lastMouseY = -1;
    public int lastHoldX, lastHoldY;

    public boolean mouseWheelUp, mouseWheelDown;

    public HashMap<String, HashMap<Rectangle, Boolean>> clickAreas = new HashMap<>();
    HashMap<String, HashMap<Rectangle, Boolean>> hoverAreas = new HashMap<>();

    public void removeClickArea(String name) {
        clickAreas.remove(name);
    }

    public void addClickArea(String name, Rectangle rectangle) {
        HashMap<Rectangle, Boolean> hashMap = new HashMap<>();
        hashMap.put(rectangle, false);

        clickAreas.put(name, hashMap);
    }

    public boolean checkClicked(String name) {
        HashMap<Rectangle, Boolean> clicked = clickAreas.get(name);

        AtomicBoolean isTrue = new AtomicBoolean(false);
        clicked.forEach(((rectangle, bool) -> {
            if (bool) {
                isTrue.set(true);
            }
        }));

        return isTrue.get();
    }

    public void updateClickArea(String name, Rectangle rectangleUpdate) {
        HashMap<Rectangle, Boolean> clicked = clickAreas.get(name);

        AtomicBoolean isTrue = new AtomicBoolean(false);
        clicked.forEach(((rectangle, bool) -> {
            if (bool) {
                isTrue.set(true);
            }
        }));

        HashMap<Rectangle, Boolean> hashMap = new HashMap<>();
        hashMap.put(rectangleUpdate, isTrue.get());

        clickAreas.put(name, hashMap);
    }

    public void addHoverArea(String name, Rectangle rectangle) {
        HashMap<Rectangle, Boolean> hashMap = new HashMap<>();
        hashMap.put(rectangle, false);

        hoverAreas.put(name, hashMap);
    }

    public void removeHoverArea(String name) {
        hoverAreas.remove(name);
    }

    public void updateHoverArea(String name, Rectangle rectangleUpdate) {
        HashMap<Rectangle, Boolean> hovered = hoverAreas.get(name);

        AtomicBoolean isTrue = new AtomicBoolean(false);
        hovered.forEach(((rectangle, bool) -> {
            if (bool) {
                isTrue.set(true);
            }
        }));

        HashMap<Rectangle, Boolean> hashMap = new HashMap<>();
        hashMap.put(rectangleUpdate, isTrue.get());

        hoverAreas.put(name, hashMap);
    }

    public boolean checkHovered(String name) {
        HashMap<Rectangle, Boolean> hovered = hoverAreas.get(name);

        AtomicBoolean isTrue = new AtomicBoolean(false);
        hovered.forEach(((rectangle, bool) -> {
            if (bool) {
                isTrue.set(true);
            }
        }));

        return isTrue.get();
    }

    public void update() {
        clickAreas.forEach( (name, hashMap) -> {
            hashMap.forEach( (rectangle, bool) -> {
                HashMap<Rectangle, Boolean> loadRectangle = new HashMap<>();

                if (bool) {
                    loadRectangle.put(rectangle, false);
                    clickAreas.put(name, loadRectangle);
                }

            });
        });

        if (leftClicked) {
            leftClicked = false;
        }

        if (rightClicked) {
            rightClicked = false;
        }

        if (mouseWheelUp) {
            mouseWheelUp = false;
        }

        if (mouseWheelDown) {
            mouseWheelDown = false;
        }

        if (leftHolding) {
            lastMouseX = mouseX;
            lastMouseY = mouseY;
        }

        if (rightHolding) {
            lastMouseX = mouseX;
            lastMouseY = mouseY;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = true;

            clickAreas.forEach( (name, hashMap) -> {
                hashMap.forEach( (rectangle, bool) -> {
                    HashMap<Rectangle, Boolean> loadRectangle = new HashMap<>();

                    if (e.getX() >= rectangle.x && e.getX() <= rectangle.x + rectangle.width) {
                        if (e.getY() >= rectangle.y && e.getY() <= rectangle.y + rectangle.height) {
                            loadRectangle.put(rectangle, true);

                            clickAreas.put(name, loadRectangle);
                        }
                        else {
                            loadRectangle.put(rectangle, false);
                            clickAreas.put(name, loadRectangle);
                        }
                    }
                    else {
                        loadRectangle.put(rectangle, false);
                        clickAreas.put(name, loadRectangle);
                    }
                });
            });
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastHoldX = mouseX;
        lastHoldY = mouseY;

        if (e.getButton() == MouseEvent.BUTTON1) {
            leftHolding = true;
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            rightHolding = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftHolding = false;
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            rightHolding = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Mouse Motion
    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            hoverAreas.forEach( (name, hashMap) -> {
                hashMap.forEach( (rectangle, bool) -> {
                    HashMap<Rectangle, Boolean> loadRectangle = new HashMap<>();

                    if (e.getX() >= rectangle.x && e.getX() <= rectangle.x + rectangle.width) {
                        if (e.getY() >= rectangle.y && e.getY() <= rectangle.y + rectangle.height) {
                            loadRectangle.put(rectangle, true);

                            hoverAreas.put(name, loadRectangle);
                        }
                        else {
                            loadRectangle.put(rectangle, false);
                            hoverAreas.put(name, loadRectangle);
                        }
                    }
                    else {
                        loadRectangle.put(rectangle, false);
                        hoverAreas.put(name, loadRectangle);
                    }
                });
            });
        }

        mouseX = e.getX();
        mouseY = e.getY();
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            hoverAreas.forEach( (name, hashMap) -> {
                hashMap.forEach( (rectangle, bool) -> {
                    HashMap<Rectangle, Boolean> loadRectangle = new HashMap<>();

                    if (e.getX() >= rectangle.x && e.getX() <= rectangle.x + rectangle.width) {
                        if (e.getY() >= rectangle.y && e.getY() <= rectangle.y + rectangle.height) {
                            loadRectangle.put(rectangle, true);

                            hoverAreas.put(name, loadRectangle);
                        }
                        else {
                            loadRectangle.put(rectangle, false);
                            hoverAreas.put(name, loadRectangle);
                        }
                    }
                    else {
                        loadRectangle.put(rectangle, false);
                        hoverAreas.put(name, loadRectangle);
                    }
                });
            });
        }


        lastMouseX = mouseX;
        lastMouseY = mouseY;

        lastHoldX = mouseX;
        lastHoldY = mouseY;

        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        switch (e.getWheelRotation()) {
            case -1:
                mouseWheelUp = true;
                break;
            case 1:
                mouseWheelDown = true;
                break;
        }
    }
}
