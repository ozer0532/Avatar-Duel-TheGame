// IMouseMoveSub.java
package com.avatarduel.gamemanager;

import javafx.scene.input.MouseEvent;

/**
 * IMouseMoveSub merupakan interface yang bertanggung jawab apabila posisi mouse bergerak. IMouseMoveSub
 * digunakan dalam hover pada GUI sehingga IMouseMoveSub terhubung dengan pengelolaan sprite dalam GUI/
 * IMouseMoveSub menerapkan konsep Publisher-Subsciber dimana IMouseMoveSub sebagai subscriber yang diatur
 * dalam class GameManager
 */
public interface IMouseMoveSub {
    /**
     * Melaksanakan suatu aksi tertentu ketika suatu event terjadi akibat pergerakan mouse
     * @param event sebuah kejadian MouseEvent yang mentriggered suatu aksi tertentu
     */
    public void OnMouseMove(MouseEvent event);
}
