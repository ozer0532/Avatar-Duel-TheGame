package com.avatarduel.sprite;

import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class CardSprite extends Sprite {

    private Image backface;
    private List<CardText> textList = new ArrayList<>();
    private Image cardImage;

    public CardSprite (String front, String back, String image) {
        super(front);
        backface = new Image(back);
        cardImage = new Image(image);
    }

    public CardSprite (String front, String back, String image, double x, double y) {
        super(front, x, y);
        backface = new Image(back);
        cardImage = new Image(image);
    }

    public CardSprite (String front, String back, String image, double x, double y, double w, double h) {
        super(front, x, y, w, h);
        backface = new Image(back);
        cardImage = new Image(image);
    }

    public void InsertText (CardText text) {
        textList.add(text);
    }

    public void InsertText (String text, double x, double y) {
        textList.add(new CardText(text, x, y));
    }

    public void InsertText (String text, double x, double y, String fontName) {
        textList.add(new CardText(text, x, y, fontName));
    }

    public void InsertText (String text, double x, double y, String fontName, double fontSize) {
        textList.add(new CardText(text, x, y, fontName, fontSize));
    }

    @Override
    protected void updateAbsolutePosition (GraphicsContext gc) {
        double screenWidth = gc.getCanvas().getWidth();
        double screenHeight = gc.getCanvas().getHeight();
        double xStartPos = screenWidth * anchorX;
        double yStartPos = screenHeight * anchorY;
        double absoluteX;
        double absoluteY;
        
        if (getW() > 0) {
            absoluteX = xStartPos + x - w * pivotX * scaleX;
        } else {
            absoluteX = xStartPos + x - w * (pivotX - 1) * scaleX;
        }
        absoluteY = yStartPos + y - h * pivotY * scaleY;

        setAbsolutePosition(absoluteX, absoluteY);
    }

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
        if (getW() > 0) {
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

    @Override
    public boolean isPointOverlap(float x, float y){
        if (getW() > 0) {
            return (x > absoluteX) && (x < absoluteX + w * scaleX) && (y > absoluteY) && (y < absoluteY + h * scaleY);
        } else {
            return (x > absoluteX) && (x < absoluteX - w * scaleX) && (y > absoluteY) && (y < absoluteY + h * scaleY);
        }
    }
}