package com.avatarduel.sprite;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Sprite {
    private Image image;
    private double x, y;
    private double w, h; // Width, Height
    private double pivotX, pivotY; // Posisi Scaling
    private double anchorX, anchorY; // Posisi relatif dari layar

    // Untuk animasi
    private double targetX, targetY; // Target posisi animasi
    private double targetW, targetH;
    private double targetAnchorX, targetAnchorY;

    private double absoluteX, absoluteY; // Posisi dari pojok atas kiri

    public Sprite(Image image) {
        this.image = image;
        x = y = 0;
        w = image.getWidth(); 
        h = image.getHeight();
        pivotX = pivotY = 0.5;
        anchorX = anchorY = 0.5;

        targetX = targetY = 0;
        targetW = w;
        targetH = h;
        anchorX = anchorY = 0.5;
    }

    public Sprite(Image image, double x, double y) {
        this.image = image;
        this.x = x;
        this.y = y;
        w = image.getWidth(); 
        h = image.getHeight();
        pivotX = pivotY = 0.5;
        anchorX = anchorY = 0.5;

        targetX = x;
        targetY = y;
        targetW = w;
        targetH = h;
        anchorX = anchorY = 0.5;
    }

    public Sprite(Image image, double x, double y, double w, double h) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        pivotX = pivotY = 0.5;
        anchorX = anchorY = 0.5;

        targetX = x;
        targetY = y;
        targetW = w;
        targetH = h;
        anchorX = anchorY = 0.5;
    }

    public void MoveToPos (double x, double y) {
        targetX = x;
        targetY = y;
    }

    public void JumpToPos (double x, double y) {
        this.x = x;
        this.y = y;

        targetX = x;
        targetY = y;
    }

    public void ChangeSize (double w, double h) {
        ChangeSize(w, h, false);
    }

    public void ChangeSize (double w, double h, boolean instant) {
        targetW = w;
        targetH = h;

        if (instant) {
            this.w = w;
            this.h = h;
        }
    }

    public void ChangeAnchor (double x, double y, boolean instant) {
        targetAnchorX = x;
        targetAnchorY = y;

        if (instant) {
            anchorX = x;
            anchorY = y;
        }
    }

    private void UpdateAbsolutePosition (GraphicsContext gc) {
        double screenWidth = gc.getCanvas().getWidth();
        double screenHeight = gc.getCanvas().getHeight();
        double xStartPos = screenWidth * anchorX;
        double yStartPos = screenHeight * anchorY;
        
        absoluteX = xStartPos + x - w * pivotX;
        absoluteY = yStartPos + y - h * pivotY;
    }

    // Melakukan smoothing terhadap atribut-atribut sprite
    // agar mendekati atribut target
    // Terima delta time ((currentFrameTime - previousFrameTime)/1000000000)
    public void update(GraphicsContext gc, double deltaTime) {
        final double smoothing = 5;

        // Melakukan smoothing dengan "linear interpolation" (a*t + b*(1-t))
        x = (x * smoothing * deltaTime) + (targetX * (1 - smoothing * deltaTime));
        y = (y * smoothing * deltaTime) + (targetY * (1 - smoothing * deltaTime));
        w = (w * smoothing * deltaTime) + (targetW * (1 - smoothing * deltaTime));
        h = (h * smoothing * deltaTime) + (targetH * (1 - smoothing * deltaTime));
        anchorX = (anchorX * smoothing * deltaTime) + (targetAnchorX * (1 - smoothing * deltaTime));
        anchorY = (anchorY * smoothing * deltaTime) + (targetAnchorY * (1 - smoothing * deltaTime));

        UpdateAbsolutePosition(gc);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, absoluteX, absoluteY, w, h);
    }

    public boolean isPointOverlap(float x, float y){
        return (x > absoluteX) && (x < absoluteX + w) && (y > absoluteY) && (y < absoluteY + h);
    }
}