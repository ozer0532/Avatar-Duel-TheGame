package com.avatarduel.player;

import com.avatarduel.model.Element;

public class PlayerStatsBuilder {
    private PlayerStats playerStats;

    public PlayerStatsBuilder(){
        this.playerStats = new PlayerStats();
    }

    public PlayerStatsBuilder health(int val){
        this.playerStats.setHealth(val);
        return this;
    }

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
            default:
                System.out.println("Invalid element");
        }
        return this;
    }

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
            default:
                System.out.println("Invalid element");
        }
        return this;
    }

    public PlayerStatsBuilder playedLandThisRound(boolean val){
        this.playerStats.setPlayedLandThisRound(val);
        return this;
    }

    public PlayerStats build(){
        return this.playerStats;
    }

    /* DEBUG */
    public static void main(String args[]){
        PlayerStats player = new PlayerStatsBuilder().health(100).power(Element.AIR, 10).power(Element.EARTH, 20).power(Element.FIRE, 30)
        .power(Element.WATER, 40).remainingPower(Element.AIR, 40).remainingPower(Element.EARTH, 30).remainingPower(Element.FIRE, 20)
        .remainingPower(Element.WATER, 10).playedLandThisRound(true).build();

        player.printPlayerStats();
    }
}