package com.avatarduel.player;

import com.avatarduel.model.Element;

public class PlayerStats {
    private int health;
    private int airPower;
    private int earthPower;
    private int firePower;
    private int waterPower;
    private int energyPower;
    private int remainingAir;
    private int remainingEarth;
    private int remainingFire;
    private int remainingWater;
    private int remainingEnergy;
    private boolean playedLandThisRound;

    /* CTOR */
    public PlayerStats(){
        this.health = 80;
        this.airPower = 0;
        this.earthPower = 0;
        this.firePower = 0;
        this.waterPower = 0;
        this.energyPower = 0;
        this.remainingAir = 0;
        this.remainingEarth = 0;
        this.remainingFire = 0;
        this.remainingWater = 0;
        this.remainingEnergy = 0;
        this.playedLandThisRound = false; 
    }

    /* GETTER */
    public int getHealth(){
        return this.health;
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
            case ENERGY:
                ret = this.energyPower;
                break;
            default:
                System.out.println("Invalid element");
        }
        return ret;
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
            case ENERGY:
                ret = this.remainingEnergy;
                break;
            default:
                System.out.println("Invalid element");
        }
        return ret;
    }
    
    public boolean getPlayedLandThisRound(){
        return this.playedLandThisRound;
    }
    
    /* SETTER */
    public void setHealth(int val){
        this.health = val;
    }

    public void setPower(Element element, int val){
        switch (element){
            case AIR:
                this.airPower = val;
                break;
            case EARTH:
                this.earthPower = val;
                break;
            case FIRE:
                this.firePower = val;
                break;
            case WATER:
                this.waterPower = val;
                break;
            case ENERGY:
                this.energyPower = val;
                break;
            default:
                System.out.println("Invalid element");
        }
    }

    public void setRemainingPower(Element element, int val){
        switch (element){
            case AIR:
                this.remainingAir = val;
                break;
            case EARTH:
                this.remainingEarth = val;
                break;
            case FIRE:
                this.remainingFire = val;
                break;
            case WATER:
                this.remainingWater = val;
                break;
            case ENERGY:
                this.remainingEnergy = val;
                break;
            default:
                System.out.println("Invalid element");
        }
    }

    public void setPlayedLandThisRound(boolean val){
        this.playedLandThisRound = val;
    }

    /* UTIL */
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
            case ENERGY:
                this.energyPower += 1;
                this.remainingEnergy += 1;
                break;
            default:
                System.out.println("Invalid element");
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
                case ENERGY:
                    this.remainingEnergy -= amount;
                    break;
                default:
                    System.out.println("Invalid element");
            }
        } else {
            System.out.println("Power insufficient");
        }   
    }
    
    public void resetStats(){
        this.remainingAir = this.airPower;
        this.remainingEarth = this.earthPower;
        this.remainingFire = this.firePower;
        this.remainingWater = this.waterPower;
        this.remainingEnergy = this.energyPower;
        this.playedLandThisRound = false; 
    }
    
    public void takeDamage(int amount){
        this.health -= amount;
    }

    public void printPlayerStats(){
        System.out.format("health              : %d", this.getHealth());
        System.out.format("airPower            : %d", this.getPower(Element.AIR));
        System.out.format("earthPower          : %d", this.getPower(Element.EARTH));
        System.out.format("firePower           : %d", this.getPower(Element.FIRE));
        System.out.format("waterPower          : %d", this.getPower(Element.WATER));
        System.out.format("energyPower         : %d", this.getPower(Element.ENERGY));
        System.out.format("remainingAir        : %d", this.getRemainingPower(Element.AIR));
        System.out.format("remainingEarth      : %d", this.getRemainingPower(Element.EARTH));
        System.out.format("remainingFire       : %d", this.getRemainingPower(Element.FIRE));
        System.out.format("remainingWater      : %d", this.getRemainingPower(Element.WATER));
        System.out.format("remainingEnergy     : %d", this.getRemainingPower(Element.ENERGY));
        System.out.format("playedLandThisRound : %s", this.getPlayedLandThisRound() ? "true" : "false");
    }
}