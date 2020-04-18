package com.avatarduel.player;

import com.avatarduel.model.Element;

/**
 * Builder dari class PlayerStats
 */
public class PlayerStatsBuilder {
    /**
     * Atribut dari PlayerStatsBuilder
     */
    private PlayerStats playerStats;

    /**
     * Membuat PlayerStatsBuilder
     */
    public PlayerStatsBuilder(){
        this.playerStats = new PlayerStats();
    }

    /**
     * Mengubah nilai health dari atribut playerStats
     */
    public PlayerStatsBuilder health(int val){
        this.playerStats.setHealth(val);
        return this;
    }

    /**
     * Mengubah nilai power dari atribut playerStats
     * Jika element tidak valid maka output "Invalid element"
     * @param element jenis element dari power
     * @param val nilai power
     * @return PlayerStatsBuilder
     */
    public PlayerStatsBuilder power(Element element, int val){
        switch (element){
            case AIR:
                this.playerStats.setPower(element, val);
                break;
            case EARTH:
                this.playerStats.setPower(element, val);
                break;
            case FIRE:
                this.playerStats.setPower(element, val);
                break;
            case WATER:
                this.playerStats.setPower(element, val);
                break;
            case ENERGY:
                this.playerStats.setPower(element, val);
                break;
            default:
                System.out.println("Invalid element");
        }
        return this;
    }

    /**
     * Mengubah nilai remaining power dari atribut playerStats
     * Jika element tidak valid maka output "Invalid element"
     * @param element jenis element dari remaining power
     * @param val nilai power
     * @return PlayerStatsBuilder
     */
    public PlayerStatsBuilder remainingPower(Element element, int val){
        switch (element){
            case AIR:
                this.playerStats.setRemainingPower(element, val);
                break;
            case EARTH:
                this.playerStats.setRemainingPower(element, val);
                break;
            case FIRE:
                this.playerStats.setRemainingPower(element, val);
                break;
            case WATER:
                this.playerStats.setRemainingPower(element, val);
                break;
            case ENERGY:
                this.playerStats.setRemainingPower(element, val);
                break;
            default:
                System.out.println("Invalid element");
        }
        return this;
    }

    /**
     * Mengubah nilai playedLandThisRound dari atirbut playerStats
     * @param val nilai playedLandThisRound
     * @return PlayerStatsBuilder
     */
    public PlayerStatsBuilder playedLandThisRound(boolean val){
        this.playerStats.setPlayedLandThisRound(val);
        return this;
    }

    /**
     * Build playerStats
     * @return PlayerStats
     */
    public PlayerStats build(){
        return this.playerStats;
    }
}