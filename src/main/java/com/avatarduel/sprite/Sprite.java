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

    protected double rotation;

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
    protected double targetRotation;

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
    }
    
    /**
     * Melakukan translasi terhadap gambar secara lancar
     * @param x posisi x yang dituju
     * @param y posisi y yang dituju
     */
    public void changePos (double x, double y) {
        changePos(x, y, false);
    }
    
    /**
     * Melakukan translasi terhadap gambar
     * @param x posisi x yang dituju
     * @param y posisi y yang dituju
     * @param instant jika true, translasi dilakukan secara instan
     */
    public void changePos (double x, double y, boolean instant) {
        targetX = x;
        targetY = y;
        
        if (instant) {
            this.x = x;
            this.y = y;
        }
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
     * Mengubah rotasi gambar secara lancar
     * @param r rotasi gambar dalam derajat
     */
    public void changeRotation (double r) {
        changeRotation(r, false);
    }

    /**
     * Mengubah rotasi gambar
     * @param r rotasi gambar dalam derajat
     * @param instant jika true, rotasi dilakukan secara instan
     */
    public void changeRotation (double r, boolean instant) {
        targetRotation = r;

        if (instant) {
            rotation = r;
        }
    }
    
    /**
     * Mengubah titik referensi gambar relatif terhadap layar
     * @param x titik jangkar x yang dituju
     * @param y titik jangkar y yang dituju
     * jika false, perubahan dilakukan secara lancar
     */
    public void setAnchor (double x, double y) {
        anchorX = x;
        anchorY = y;
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
        final double smoothing = 15;

        // Melakukan smoothing dengan "linear interpolation" (a*t + b*(1-t))
        double t = smoothing * deltaTime;
        x = lerp(x, targetX, t);
        y = lerp(y, targetY, t);
        rotation = lerp(rotation, targetRotation, t);
        scaleX = lerp(scaleX, targetScaleX, t);
        scaleY = lerp(scaleY, targetScaleY, t);

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
        a.appendTranslation(pivotX * w, pivotY * h);
        a.appendRotation(rotation);
        a.appendTranslation(-pivotX * w, -pivotY * h);
        
        a.prependTranslation(absoluteX, absoluteY);
        gc.setTransform(a);
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
        return (x > absoluteX) && (x < absoluteX + w * scaleX) && (y > absoluteY) && (y < absoluteY + h * scaleY);
    }

    /**
     * Melakukan interpolasi linier antara a dan b
     * @param a Nilai awal interpolasi
     * @param b Nilai akhir interpolasi
     * @param t Persentase interpolasi dari a ke b, bernilai 0..1
     * @return Hasil interpolasi nilai a dan b
     */
    protected double lerp(double a, double b, double t) {
        if (t > 1) {
            return b;
        } else if (t < 0) {
            return a;
        } else {
            return (a * (1 - t)) + (b * t);
        }
    }
}