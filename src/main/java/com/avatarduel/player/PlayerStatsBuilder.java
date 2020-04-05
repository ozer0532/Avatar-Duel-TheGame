// PlayerStatsBuilder.java
package com.avatarduel.player;

public class PlayerStatsBuilder {
    private PlayerStats PS;

    public PlayerStatsBuilder(){
        PS = new PlayerStats();
    }

    public PlayerStatsBuilder health(int val){
        PS.setHealth(val);
        return this;
    }

    public PlayerStatsBuilder airPower(int val){
        PS.setAirPower(val);
        return this;
    }

    public PlayerStatsBuilder earthPower(int val){
        PS.setEarthPower(val);
        return this;
    }

    public PlayerStatsBuilder firePower(int val){
        PS.setFirePower(val);
        return this;
    }

    public PlayerStatsBuilder waterPower(int val){
        PS.setWaterPower(val);
        return this;
    }

    public PlayerStatsBuilder playedLandThisRound(boolean val){
        PS.setPlayedLandThisRound(val);
        return this;
    }

    public PlayerStatsBuilder remainingAir(int val){
        PS.setRemainingAir(val);
        return this;
    }

    public PlayerStatsBuilder remainingEarth(int val){
        PS.setRemainingEarth(val);
        return this;
    }

    public PlayerStatsBuilder remainingFire(int val){
        PS.setRemainingFire(val);
        return this;
    }

    public PlayerStatsBuilder remainingWater(int val){
        PS.setRemainingWater(val);
        return this;
    }

    public PlayerStats build(){
        return PS;
    }
}