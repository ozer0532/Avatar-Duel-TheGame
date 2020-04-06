package com.avatarduel.sprite;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class CardSprite extends Sprite {

    public Image backface;

    public CardSprite (String front, String back) {
        super(front);
        backface = new Image(back);
    }

    public CardSprite (String front, String back, double x, double y) {
        super(front, x, y);
        backface = new Image(back);
    }

    public CardSprite (String front, String back, double x, double y, double w, double h) {
        super(front, x, y, w, h);
        backface = new Image(back);
    }

    @Override
    protected void updateAbsolutePosition (GraphicsContext gc) {
        double screenWidth = gc.getCanvas().getWidth();
        double screenHeight = gc.getCanvas().getHeight();
        double xStartPos = screenWidth * getAnchorX();
        double yStartPos = screenHeight * getAnchorY();
        double absoluteX;
        double absoluteY;
        
        if (getW() > 0) {
            absoluteX = xStartPos + getX() - getW() * getPivotX();
        } else {
            absoluteX = xStartPos + getX() - getW() * (getPivotX() - 1);
        }
        absoluteY = yStartPos + getY() - getH() * getPivotY();

        setAbsolutePosition(absoluteX, absoluteY);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (getW() > 0) {
            gc.drawImage(getImage(), getAbsoluteX(), getAbsoluteY(), getW(), getH());
        }
        else {
            gc.drawImage(backface, getAbsoluteX(), getAbsoluteY(), -getW(), getH());
        }
    }

    @Override
    public boolean isPointOverlap(float x, float y){
        if (getW() > 0) {
            return (x > getAbsoluteX()) && (x < getAbsoluteX() + getW()) && (y > getAbsoluteY()) && (y < getAbsoluteY() + getH());
        } else {
            return (x > getAbsoluteX()) && (x < getAbsoluteX() - getW()) && (y > getAbsoluteY()) && (y < getAbsoluteY() + getH());
        }
    }
}