package com.avatarduel.sprite;

import javafx.scene.text.Font;

/**
 * CardText digunakan untuk menyimpan data tulisan untuk
 * digambar pada tampak depan kartu.
 */
public class CardText {
    private String text;
    private double x;
    private double y;
    private Font font;

    /**
     * Membuat sebuah card text baru dengan fon standar
     * @param text tulisan dari teks
     * @param x posisi x dari teks
     * @param y posisi y dari teks
     */
    public CardText (String text, double x, double y) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = new Font("Palatino Linotype Bold", 18);
    }

    /**
     * Membuat sebuah card text baru
     * @param text tulisan dari teks
     * @param x posisi x dari teks
     * @param y posisi y dari teks
     * @param font fon teks untuk dicetak
     */
    public CardText (String text, double x, double y, Font font) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = font;
    }

    /**
     * Membuat sebuah card text baru dengan ukuran fon standar
     * @param text tulisan dari teks
     * @param x posisi x dari teks
     * @param y posisi y dari teks
     * @param fontName nama fon untuk dicetak
     */
    public CardText (String text, double x, double y, String fontName) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = new Font(fontName, 18);
    }

    /**
     * /**
     * Membuat sebuah card text baru
     * @param text tulisan dari teks
     * @param x posisi x dari teks
     * @param y posisi y dari teks
     * @param fontName nama fon untuk dicetak
     * @param fontSize ukuran fon untuk dicetak
     */
    public CardText (String text, double x, double y, String fontName, double fontSize) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = new Font(fontName, fontSize);
    }

    /**
     * Mendapatkan tulisan dari teks
     * @return tulisan teks
     */
    public String getText () { 
        return text; 
    }

    /**
     * Mendapatkan komponen x dari posisi teks
     * @return posisi x dari teks
     */
    public double getX () { 
        return x; 
    }

    /**
     * Mendapatkan komponen y dari posisi teks
     * @return posisi y dari teks
     */
    public double getY () { 
        return y; 
    }

    /**
     * Mendapatkan fon dari teks
     * @return fon tulisan
     */
    public Font getFont () { 
        return font; 
    }
}