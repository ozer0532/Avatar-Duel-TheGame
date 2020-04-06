package com.avatarduel.player;

import com.avatarduel.model.Element;

public class PlayerStats {
    private int health;
    private int airPower;
    private int earthPower;
    private int firePower;
    private int waterPower;
    private int remainingAir;
    private int remainingEarth;
    private int remainingFire;
    private int remainingWater;
    private boolean playedLandThisRound;

    public PlayerStats(){
        this.health = 0;
        this.airPower = 0;
        this.earthPower = 0;
        this.firePower = 0;
        this.waterPower = 0;
        this.remainingAir = 0;
        this.remainingEarth = 0;
        this.remainingFire = 0;
        this.remainingWater = 0;
        this.playedLandThisRound = false; 
    }
    
    public int getPower(Element element){
        int ret = 0;
        switch (element){
            case AIR:
                ret = this.airPower;
                break;
            case EARTH:
                ret = this.earthPower;
                break;
                case FIRE:
                ret = this.firePower;
                break;
                case WATER:
                ret = this.waterPower;
                break;
                default:
                System.out.println("Invalid element");
        }
        return ret;
    }
    
    public void incrementPower(Element element){
        switch (element){
            case AIR:
                this.airPower += 1;
                this.remainingAir += 1;
                break;
                case EARTH:
                this.earthPower += 1;
                this.remainingEarth += 1;
                break;
                case FIRE:
            this.firePower += 1;
            this.remainingFire += 1;
                break;
                case WATER:
                this.waterPower += 1;
                this.remainingWater += 1;
                break;
            }
        }

    public void usePower(Element element, int amount){
        if (this.remainingAir-amount>=0 || this.remainingEarth-amount>=0 || this.remainingFire-amount>=0 || this.remainingWater-amount>=0){
            switch (element){
                case AIR:
                this.remainingAir -= amount;
                break;
                case EARTH:
                this.remainingEarth -= amount;
                break;
                case FIRE:
                this.remainingFire -= amount;
                break;
                case WATER:
                this.remainingWater -= amount;
                    break;
                }
            } else {
                System.out.println("Need more power");
        }   
    }
    
    public void resetStats(){
        this.remainingAir = 0;
        this.remainingEarth = 0;
        this.remainingFire = 0;
        this.remainingWater = 0;
        this.playedLandThisRound = false; 
    }
    
    public void takeDamage(int amount){
        this.health -= amount;
    }
    
    // debug
    public int getHealth(){
        return this.health;
    }

    public String getPlayedLandThisRound(){
        return this.playedLandThisRound ? "True" : "False";
    }

    public int getRemainingPower(Element element){
        int ret = 0;
        switch (element){
            case AIR:
                ret = this.remainingAir;
                break;
            case EARTH:
            ret = this.remainingEarth;
            break;
            case FIRE:
            ret = this.remainingFire;
            break;
            case WATER:
            ret = this.remainingWater;
            break;
            default:
                System.out.println("Invalid element");
        }
        return ret;
    }

    public void printPlayerStats(){
        System.out.format("health              : %d", this.getHealth());
        System.out.format("airPower            : %d", this.getPower(Element.AIR));
        System.out.format("earthPower          : %d", this.getPower(Element.EARTH));
        System.out.format("firePower           : %d", this.getPower(Element.FIRE));
        System.out.format("waterPower          : %d", this.getPower(Element.WATER));
        System.out.format("remainingAir        : %d", this.getRemainingPower(Element.AIR));
        System.out.format("remainingEarth      : %d", this.getRemainingPower(Element.EARTH));
        System.out.format("remainingFire       : %d", this.getRemainingPower(Element.FIRE));
        System.out.format("remainingWater      : %d", this.getRemainingPower(Element.WATER));
        System.out.format("playedLandThisRound : %s", this.getPlayedLandThisRound());
    }

    public static void main(String args[]){
        
    }
}