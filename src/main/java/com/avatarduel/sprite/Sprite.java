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

    public Sprite(String image) {
        this.image = new Image(image);
        x = y = 0;
        w = this.image.getWidth(); 
        h = this.image.getHeight();
        pivotX = pivotY = 0.5;
        anchorX = anchorY = 0.5;

        targetX = targetY = 0;
        targetW = w;
        targetH = h;
        anchorX = anchorY = 0.5;
    }

    public Sprite(String image, double x, double y) {
        this.image = new Image(image);;
        this.x = x;
        this.y = y;
        w = this.image.getWidth(); 
        h = this.image.getHeight();
        pivotX = pivotY = 0.5;
        anchorX = anchorY = 0.5;

        targetX = x;
        targetY = y;
        targetW = w;
        targetH = h;
        anchorX = anchorY = 0.5;
    }

    public Sprite(String image, double x, double y, double w, double h) {
        this.image = new Image(image);;
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

    // Getter
    public Image getImage () { return image; }
    public double getX () { return x; }
    public double getY () { return y; }
    public double getW () { return w; }
    public double getH () { return h; }
    public double getPivotX () { return pivotX; }
    public double getPivotY () { return pivotY; }
    public double getAnchorX () { return anchorX; }
    public double getAnchorY () { return anchorY; }
    public double getTargetX () { return targetX; }
    public double getTargetY () { return targetY; }
    public double getTargetW () { return targetW; }
    public double getTargetH () { return targetH; }
    public double getTargetAnchorX () { return targetAnchorX; }
    public double getTargetAnchorY () { return targetAnchorY; }
    public double getAbsoluteX () { return absoluteX; }
    public double getAbsoluteY () { return absoluteY; }

    // Setter
    public void setImage (String image) { setImage(image, true); }
    public void setImage (String image, boolean autoWidth) { setImage(new Image(image), autoWidth); }
    public void setImage (Image image) { setImage(image, true); }
    public void setImage (Image image, boolean autoWidth) 
    {  
        this.image = image;
        if (autoWidth) {
            w = image.getWidth(); 
            h = image.getHeight();
            targetW = w;
            targetH = h;
        }
    }
    
    public void moveToPos (double x, double y) {
        targetX = x;
        targetY = y;
    }
    
    public void jumpToPos (double x, double y) {
        this.x = x;
        this.y = y;
        
        targetX = x;
        targetY = y;
    }
    
    public void changeSize (double w, double h) {
        changeSize(w, h, false);
    }
    
    public void changeSize (double w, double h, boolean instant) {
        targetW = w;
        targetH = h;
        
        if (instant) {
            this.w = w;
            this.h = h;
        }
    }
    
    public void changeAnchor (double x, double y) {
        changeAnchor(x, y, false);
    }
    
    public void changeAnchor (double x, double y, boolean instant) {
        targetAnchorX = x;
        targetAnchorY = y;
        
        if (instant) {
            anchorX = x;
            anchorY = y;
        }
    }
    
    public void setPivot (double x, double y) {
        pivotX = x;
        pivotY = y;
    }

    public void setAbsolutePosition (double x, double y) {
        absoluteX = x;
        absoluteY = y;
    }
    
    protected void updateAbsolutePosition (GraphicsContext gc) {
        double screenWidth = gc.getCanvas().getWidth();
        double screenHeight = gc.getCanvas().getHeight();
        double xStartPos = screenWidth * anchorX;
        double yStartPos = screenHeight * anchorY;
        
        setAbsolutePosition(xStartPos + x - w * pivotX, yStartPos + y - h * pivotY);
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

        updateAbsolutePosition(gc);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, absoluteX, absoluteY, w, h);
    }

    public boolean isPointOverlap(float x, float y){
        return (x > absoluteX) && (x < absoluteX + w) && (y > absoluteY) && (y < absoluteY + h);
    }
}