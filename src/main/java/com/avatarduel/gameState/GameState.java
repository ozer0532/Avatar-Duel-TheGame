// GameState.java
package com.avatarduel.gamestate;

import com.avatarduel.gamemanager.*;

/**
 * GameState merupakan abstract class dari fase-fase game yang ada dan menyimpan gameManager
 * pada permainan ini.
 * @author 13518134 / Muhammad Raihan Iqbal
 */

public abstract class GameState {
    protected GameManager gameManager;

    /**
     * Membuat GameState baru dengan masukan gameManager
     * @param gameManager gameManager sekarang
     */
    public GameState(GameManager gameManager){
        this.gameManager = gameManager;
    }

    /**
     * Memulai fase tersebut
     */
    abstract public void startTurn();

    /**
     * Mengakhiri fase tersebut
     */
    abstract public void endTurn();
}