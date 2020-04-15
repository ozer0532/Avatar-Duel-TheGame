package com.avatarduel.sprite;

import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import javafx.scene.canvas.GraphicsContext;

/**
 * Sprite adalah sebuah gambar yang memiliki data transformasi
 * dan dapat digerakan dalam sebuah animasi.
 */
public class Sprite {
    protected Image image;

    /**
     * Komponen translasi gambar
     */
    protected double x;
    protected double y;

    /**
     * Besar gambar yang dihasilkan dalam piksel
     */
    protected double w;
    protected double h;

    /**
     * Titik referensi untuk translasi dan penyekalaan
     * relatif terhadap sprite
     * Pivot (0,0) merupakan pojok kiri atas dari sprite
     * Pivot (1,1) merupakan pojok kanan bawah dari sprite
     */
    protected double pivotX;
    protected double pivotY;

    /**
     * Titik referensi untuk translasi dan penyekalaan
     * relatif terhadap layar
     * Anchor (0,0) merupakan pojok kiri atas dari layar
     * Anchor (1,1) merupakan pojok kanan bawah dari layar
     */
    protected double anchorX;
    protected double anchorY;

    /**
     * Pengganda besar gambar
     * Scale (1,1) akan menghasilkan gambar yang besarnya sesuai dengan lebar w dan tinggi h
     */
    protected double scaleX;
    protected double scaleY;

    /**
     * Komponen animasi sprite
     * Atribut yang di rujuk komponen-komponen ini akan diubah nilainya
     * menuju komponen target secara perlahan, sehingga animasi terlihat halus
     */
    protected double targetX, targetY;
    protected double targetScaleX, targetScaleY;
    protected double targetAnchorX, targetAnchorY;

    /**
     * Posisi "absolut" sudut kiri atas gambar
     * relatif terhadap layar, dalam piksel
     */
    protected double absoluteX, absoluteY;

    /**
     * Membuat sebuah sprite baru pada posisi (0,0) dengan ukuran
     * sebesar ukuran gambar, dan porosnya berada pada (0.5, 0.5)
     * @param image nama fail gambar
     */
    public Sprite(String image) {
        this.image = new Image(image);
        x = y = 0;
        w = this.image.getWidth(); 
        h = this.image.getHeight();
        pivotX = pivotY = 0.5;
        anchorX = anchorY = 0;
        scaleX = scaleY = 1;

        targetX = targetY = 0;
        targetScaleX = 1;
        targetScaleY = 1;
        anchorX = anchorY = 0.5;
    }

    /**
     * Membuat sebuah sprite baru dengan ukuran sebesar ukuran gambar, 
     * dan porosnya berada pada (0.5, 0.5)
     * @param image nama fail gambar
     * @param x posisi x gambar dalam piksel
     * @param y posisi y gambar dalam piksel
     */
    public Sprite( String image, double x, double y) {
        this.image = new Image(image);;
        this.x = x;
        this.y = y;
        w = this.image.getWidth(); 
        h = this.image.getHeight();
        pivotX = pivotY = 0.5;
        anchorX = anchorY = 0;
        scaleX = scaleY = 1;

        targetX = x;
        targetY = y;
        targetScaleX = 1;
        targetScaleY = 1;
        anchorX = anchorY = 0.5;
    }

    /**
     * Membuat sebuah sprite baru yang porosnya berada pada (0.5, 0.5)
     * @param image nama fail gambar
     * @param x posisi x gambar dalam piksel
     * @param y posisi y gambar dalam piksel
     * @param w lebar gambar dalam piksel
     * @param h tinggi gambar dalam piksel
     */
    public Sprite(String image, double x, double y, double w, double h) {
        this.image = new Image(image);;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        pivotX = pivotY = 0.5;
        anchorX = anchorY = 0;
        scaleX = scaleY = 1;

        targetX = x;
        targetY = y;
        targetScaleX = 1;
        targetScaleY = 1;
        anchorX = anchorY = 0.5;
    }

    // Getter
    // public Image getImage () { return image; }
    // public double getX () { return x; }
    // public double getY () { return y; }
    // public double getW () { return w; }
    // public double getH () { return h; }
    // public double getPivotX () { return pivotX; }
    // public double getPivotY () { return pivotY; }
    // public double getAnchorX () { return anchorX; }
    // public double getAnchorY () { return anchorY; }
    // public double getTargetX () { return targetX; }
    // public double getTargetY () { return targetY; }
    // public double getTargetScaleX () { return targetScaleX; }
    // public double getTargetScaleY () { return targetScaleY; }
    // public double getTargetAnchorX () { return targetAnchorX; }
    // public double getTargetAnchorY () { return targetAnchorY; }
    // public double getAbsoluteX () { return absoluteX; }
    // public double getAbsoluteY () { return absoluteY; }
    // public double getScaleX () { return scaleX; }
    // public double getScaleY () { return scaleY; }

    // Setter
    // public void setImage (String image) { setImage(image, true); }
    // public void setImage (String image, boolean autoWidth) { setImage(new Image(image), autoWidth); }
    // public void setImage (Image image) { setImage(image, true); }
    // public void setImage (Image image, boolean autoWidth) 
    // {  
    //     this.image = image;
    //     if (autoWidth) {
    //         w = image.getWidth(); 
    //         h = image.getHeight();
    //         targetScaleX = w;
    //         targetScaleY = h;
    //     }
    // }
    
    /**
     * Melakukan translasi terhadap gambar secara lancar
     * @param x posisi x yang dituju
     * @param y posisi y yang dituju
     */
    public void moveToPos (double x, double y) {
        targetX = x;
        targetY = y;
    }
    
    /**
     * Melakukan translasi terhadap gambar secara instan
     * @param x posisi x yang dituju
     * @param y posisi y yang dituju
     */
    public void jumpToPos (double x, double y) {
        this.x = x;
        this.y = y;
        
        targetX = x;
        targetY = y;
    }
    
    /**
     * Mengubah besar gambar dalam piksel secara instan
     * @param w lebar gambar baru
     * @param h tinggi gambar baru
     */
    public void setSize (double w, double h) {
        this.w = w;
        this.h = h;
    }
    
    /**
     * Mengubah titik referensi gambar relatif terhadap layar secara lancar
     * @param x titik jangkar x yang dituju
     * @param y titik jangkar y yang dituju
     */
    public void changeAnchor (double x, double y) {
        changeAnchor(x, y, false);
    }
    
    /**
     * Mengubah titik referensi gambar relatif terhadap layar
     * @param x titik jangkar x yang dituju
     * @param y titik jangkar y yang dituju
     * @param instant jika true, perubahan dilakukan secara instan;
     * jika false, perubahan dilakukan secara lancar
     */
    public void changeAnchor (double x, double y, boolean instant) {
        targetAnchorX = x;
        targetAnchorY = y;
        
        if (instant) {
            anchorX = x;
            anchorY = y;
        }
    }
    
    /**
     * Mengubah titik referensi gambar relatif terhadap gambar secara instan
     * @param x titik poros x yang dituju
     * @param y titik poros y yang dituju
     */
    public void setPivot (double x, double y) {
        pivotX = x;
        pivotY = y;
    }

    /**
     * Mengubah skala gambar secara lancar
     * @param x besar skala komponen x yang dituju
     * @param y besar skala komponen y yang dituju
     */
    public void changeScale (double x, double y) {
        changeScale(x, y, false);
    }

    /**
     * Mengubah skala gambar
     * @param x besar skala komponen x yang dituju
     * @param y besar skala komponen y yang dituju
     * @param instant jika true, perubahan dilakukan secara instan;
     * jika false, perubahan dilakukan secara lancar
     */
    public void changeScale (double x, double y, boolean instant) {
        targetScaleX = x;
        targetScaleY = y;

        if (instant) {
            scaleX = x;
            scaleY = y;
        }
    }

    /**
     * Mengubah posisi absolut gambar (relatif terhadap canvas, dalam piksel)
     * @param x posisi x yang dituju
     * @param y posisi y yang dituju
     */
    protected void setAbsolutePosition (double x, double y) {
        absoluteX = x;
        absoluteY = y;
    }
    
    /**
     * Mengkalkulasi posisi absolut gambar berdasarkan nilai atribut transformasi
     * @param gc Graphics Context canvas sebagai referensi titik jangkar
     */
    protected void updateAbsolutePosition (GraphicsContext gc) {
        final double screenWidth = gc.getCanvas().getWidth();
        final double screenHeight = gc.getCanvas().getHeight();
        final double xStartPos = screenWidth * anchorX;
        final double yStartPos = screenHeight * anchorY;
        
        setAbsolutePosition(xStartPos + x - w * pivotX * scaleX, yStartPos + y - h * pivotY * scaleY);
    }

    /**
     * Melakukan pembaruan terhadap atribut-atribut beranimasi pada sprite
     * @param gc Graphics Context sebagai referensi titik jangkar
     * @param deltaTime waktu sejak animasi sebelumnya dalam detik
     */
    public void update(GraphicsContext gc, double deltaTime) {
        final double smoothing = 5;

        // Melakukan smoothing dengan "linear interpolation" (a*t + b*(1-t))
        x = (x * smoothing * deltaTime) + (targetX * (1 - smoothing * deltaTime));
        y = (y * smoothing * deltaTime) + (targetY * (1 - smoothing * deltaTime));
        anchorX = (anchorX * smoothing * deltaTime) + (targetAnchorX * (1 - smoothing * deltaTime));
        anchorY = (anchorY * smoothing * deltaTime) + (targetAnchorY * (1 - smoothing * deltaTime));
        scaleX = (scaleX * smoothing * deltaTime) + (targetScaleX * (1 - smoothing * deltaTime));
        scaleY = (scaleY * smoothing * deltaTime) + (targetScaleY * (1 - smoothing * deltaTime));

        updateAbsolutePosition(gc);
    }

    /**
     * Menggambarkan sprite ini pada GraphicsContext
     * @param gc Graphics Context dari canvas yang ingin digambar
     */
    public void render(GraphicsContext gc) {
        gc.save();

        final Affine a = new Affine();
        a.appendScale(scaleX, scaleY);

        a.prependTranslation(absoluteX, absoluteY);
        gc.drawImage(image, 0, 0, w, h);

        gc.restore();
    }

    /**
     * Mengecek apabila sebuah titik bertumpang tindih dengan sprite
     * @param x Komponen x titik
     * @param y Komponen y titik
     * @return Bernilai true bila titik bertumpang tindih dengan sprite
     */
    public boolean isPointOverlap(double x, double y){
        return (x > absoluteX) && (x < absoluteX + w) && (y > absoluteY) && (y < absoluteY + h);
    }
}