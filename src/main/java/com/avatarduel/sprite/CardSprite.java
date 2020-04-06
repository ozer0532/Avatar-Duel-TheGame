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
    protected void UpdateAbsolutePosition (GraphicsContext gc) {
        double screenWidth = gc.getCanvas().getWidth();
        double screenHeight = gc.getCanvas().getHeight();
        double xStartPos = screenWidth * anchorX;
        double yStartPos = screenHeight * anchorY;
        
        if (w > 0) {
            absoluteX = xStartPos + x - w * pivotX;
        } else {
            absoluteX = xStartPos + x - w * (pivotX - 1);
        }
        absoluteY = yStartPos + y - h * pivotY;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (w > 0) {
            gc.drawImage(image, absoluteX, absoluteY, w, h);
        }
        else {
            gc.drawImage(backface, absoluteX, absoluteY, -w, h);
        }
    }

    @Override
    public boolean isPointOverlap(float x, float y){
        if (w > 0) {
            return (x > absoluteX) && (x < absoluteX + w) && (y > absoluteY) && (y < absoluteY + h);
        } else {
            return (x > absoluteX) && (x < absoluteX - w) && (y > absoluteY) && (y < absoluteY + h);
        }
    }
}