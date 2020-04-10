package com.avatarduel.sprite;

import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

/**
 * Variasi dari sprite khusus untuk penggambaran kartu pada game.
 * Memiliki beberapa fitur tambahan: 
 * menggambar tampak belakang kartu, 
 * menampilkan beberapa teks dan sebuah gambar tambahan pada tampak depan kartu.
 */
public class CardSprite extends Sprite {

    private Image backface;
    private List<CardText> textList = new ArrayList<>();
    private Image cardImage;

    /**
     * Membuat sebuah card sprite baru pada posisi (0,0) dengan ukuran
     * sebesar ukuran gambar, dan porosnya berada pada (0.5, 0.5)
     * @param front nama fail gambar dasar tampak depan
     * @param back nama fail gambar dasar tampak belakang
     * @param image nama fail gambar pembumbu
     */
    public CardSprite (String front, String back, String image) {
        super(front);
        backface = new Image(back);
        cardImage = new Image(image);
    }

    /**
     * Membuat sebuah card sprite baru dengan ukuran sebesar ukuran gambar, 
     * dan porosnya berada pada (0.5, 0.5)
     * @param front nama fail gambar dasar tampak depan
     * @param back nama fail gambar dasar tampak belakang
     * @param image nama fail gambar pembumbu
     * @param x posisi x gambar dalam piksel
     * @param y posisi y gambar dalam piksel
     */
    public CardSprite (String front, String back, String image, double x, double y) {
        super(front, x, y);
        backface = new Image(back);
        cardImage = new Image(image);
    }

    /**
     * Membuat sebuah sprite baru yang porosnya berada pada (0.5, 0.5)
     * @param front nama fail gambar dasar tampak depan
     * @param back nama fail gambar dasar tampak belakang
     * @param image nama fail gambar pembumbu
     * @param x posisi x gambar dalam piksel
     * @param y posisi y gambar dalam piksel
     * @param w lebar gambar dalam piksel
     * @param h tinggi gambar dalam piksel
     */
    public CardSprite (String front, String back, String image, double x, double y, double w, double h) {
        super(front, x, y, w, h);
        backface = new Image(back);
        cardImage = new Image(image);
    }

    /**
     * Menambahkan sebuah teks untuk digambarkan pada tampak depan kartu
     * @param text objek teks untuk digambar
     */
    public void InsertText (CardText text) {
        textList.add(text);
    }

    /**
     * Menambahkan sebuah teks untuk digambarkan pada tampak depan kartu
     * @param text tulisan untuk digambar
     * @param x posisi x teks relatif terhadap pojok kiri atas kartu
     * @param y posisi y teks relatif terhadap pojok kiri atas kartu
     */
    public void InsertText (String text, double x, double y) {
        textList.add(new CardText(text, x, y));
    }
    
    /**
     * Menambahkan sebuah teks untuk digambarkan pada tampak depan kartu
     * @param text tulisan untuk digambar
     * @param x posisi x teks relatif terhadap pojok kiri atas kartu
     * @param y posisi y teks relatif terhadap pojok kiri atas kartu
     * @param fontName nama fon untuk menggambar teks
     */
    public void InsertText (String text, double x, double y, String fontName) {
        textList.add(new CardText(text, x, y, fontName));
    }

    /**
     * Menambahkan sebuah teks untuk digambarkan pada tampak depan kartu
     * @param text tulisan untuk digambar
     * @param x posisi x teks relatif terhadap pojok kiri atas kartu
     * @param y posisi y teks relatif terhadap pojok kiri atas kartu
     * @param fontName nama fon untuk menggambar teks
     * @param fontSize besar fon teks
     */
    public void InsertText (String text, double x, double y, String fontName, double fontSize) {
        textList.add(new CardText(text, x, y, fontName, fontSize));
    }

    /**
     * Mengkalkulasi posisi absolut gambar berdasarkan nilai atribut transformasi
     * @param gc Graphics Context canvas sebagai referensi titik jangkar
     */
    @Override
    protected void updateAbsolutePosition (GraphicsContext gc) {
        double screenWidth = gc.getCanvas().getWidth();
        double screenHeight = gc.getCanvas().getHeight();
        double xStartPos = screenWidth * anchorX;
        double yStartPos = screenHeight * anchorY;
        double absoluteX;
        double absoluteY;
        
        if (w > 0) {
            absoluteX = xStartPos + x - w * pivotX * scaleX;
        } else {
            absoluteX = xStartPos + x - w * (pivotX - 1) * scaleX;
        }
        absoluteY = yStartPos + y - h * pivotY * scaleY;

        setAbsolutePosition(absoluteX, absoluteY);
    }

    /**
     * Menggambarkan card sprite ini pada GraphicsContext
     * @param gc Graphics Context dari canvas yang ingin digambar
     */
    @Override
    public void render(GraphicsContext gc) {
        final double imageXOffset = 0;
        final double imageYOffset = 0;
        final double imageWidth = 250;
        final double imageHeight = 250;

        gc.save();

        Affine a = new Affine();
        a.appendScale(scaleX, scaleY);

        a.prependTranslation(absoluteX, absoluteY);
        gc.setTransform(a);
        if (w > 0) {
            gc.drawImage(image, absoluteX, absoluteY, w, h);
            a.prependTranslation(-absoluteX, -absoluteY);

            for (CardText text : textList) {
                a.prependTranslation(text.getX() + absoluteX, text.getY() + absoluteY);;
                gc.setTransform(a);
                gc.setFont(text.getFont());
                gc.fillText(text.getText(), 0, 0);
                a.prependTranslation(-text.getX() - absoluteX, -text.getY() - absoluteY);
            }

            a.prependTranslation(absoluteX + imageXOffset, absoluteY + imageYOffset);
            gc.setTransform(a);
            gc.drawImage(cardImage, x, y, imageWidth, imageHeight);
        }
        else {
            gc.drawImage(backface, absoluteX, absoluteY, -w, h);
        }

        gc.restore();
    }

    /**
     * Mengecek apabila sebuah titik bertumpang tindih dengan card sprite
     * @param x Komponen x titik
     * @param y Komponen y titik
     * @return Bernilai true bila titik bertumpang tindih dengan card sprite
     */
    @Override
    public boolean isPointOverlap(double x, double y){
        if (w > 0) {
            return (x > absoluteX) && (x < absoluteX + w * scaleX) && (y > absoluteY) && (y < absoluteY + h * scaleY);
        } else {
            return (x > absoluteX) && (x < absoluteX - w * scaleX) && (y > absoluteY) && (y < absoluteY + h * scaleY);
        }
    }
}