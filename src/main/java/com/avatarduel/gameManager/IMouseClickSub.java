// IMouseClickSub.java
package com.avatarduel.gameManager;

import javafx.scene.input.MouseEvent;

/**
 * IMouseClickSub merupakan sebuah interface yang bertanggung jawab terhadap event-event yang terjadi
 * ketika mouse diclick. IMouseClickSub diimplementasikan dengan konsep Publish-Subscriber dengan
 * IMouseClickSub sebagai subscriber. IMouseClickSub digunakan dalam class GameState dan perantara
 * Publish-Subscriber diatur dalam class GameManager.
 */
public interface IMouseClickSub {
    /**
     * Melaksanakan suatu aksi tertentu ketika suatu event terjadi akibat klik dari mouse
     * @param event sebuah kejadian MouseEvent yang mentriggered suatu aksi tertentu
     */
    public void OnMouseClick(MouseEvent event);
}
