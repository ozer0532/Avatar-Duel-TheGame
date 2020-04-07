package com.avatarduel.sprite;

import javafx.scene.text.Font;

// Text yang akan digambarkan pada kartu
public class CardText {
    private String text;
    private double x;
    private double y;
    private Font font;

    public CardText (String text, double x, double y) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = new Font("Palatino Linotype Bold", 18);
    }

    public CardText (String text, double x, double y, Font font) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = font;
    }

    public CardText (String text, double x, double y, String fontName) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = new Font(fontName, 18);
    }

    public CardText (String text, double x, double y, String fontName, double fontSize) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = new Font(fontName, fontSize);
    }

    public String getText () { return text; }
    public double getX () { return x; }
    public double getY () { return y; }
    public Font getFont () { return font; }
    
}