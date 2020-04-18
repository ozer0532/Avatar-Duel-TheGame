package com.avatarduel.player;

import com.avatarduel.model.Element;

/**
 * PlayerStats adalah class yang mengatur
 * informasi status dari player
 */
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
    /**
     * Membuat PlayerStats dengan nilai default
     */
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
    /**
     * Getter untuk atribut health
     * @return health
     */
    public int getHealth(){
        return this.health;
    }
    
    /**
     * Getter untuk atribut power berdasarkan parameter element
     * Jika element tidak valid maka output "Invalid element"
     * @param element jenis element dari power
     * @return power dari element
     */
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

    /**
     * Getter untuk atribut remaining power berdasarkan parameter element
     * Jika element tidak valid maka output "Invalid element"
     * @param element jenis element dari remaining power
     * @return remaining power dari element
     */
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
    
    /**
     * Getter untukatribut playedLandThisRound
     * @return true jika telah memainkan Land
     */
    public boolean getPlayedLandThisRound(){
        return this.playedLandThisRound;
    }
    
    /* SETTER */
    /**
     * Setter untuk atribut health
     * @param val nilai health
     */
    public void setHealth(int val){
        this.health = val;
    }

    /**
     * Setter untuk atribut power berdasarkan parameter element
     * Jika element tidak valid maka output "Invalid element"
     * @param element jensi element dari power
     * @param val nilai power
     */
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

    /**
     * Setter untuk atribut remaining power berdasarkan parameter element
     * Jika element tidak valid maka output "Invalid element"
     * @param element jenis element dari power
     * @param val nilai power
     */
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

    /**
     * Setter untuk atribut playedLandThisRound
     * @param val nilai playedLandThisRound
     */
    public void setPlayedLandThisRound(boolean val){
        this.playedLandThisRound = val;
    }

    /* UTIL */
    /**
     * Menambahkan power sebesar 1 berdasarkan parameter element
     * Jika element tidak valid maka output "Invalid element"
     * @param element jenis element dari power
     */
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

    /**
     * Menggunakan power sebesar amount
     * @param element jenis element dari power
     * @param amount besar power
     */
    public void usePower(Element element, int amount){
        switch(element){
            case AIR:
                if (this.remainingAir-amount>=0){
                    this.remainingAir -= amount;
                } else {
                    System.out.println("Power insufficient");
                }
                break;
            case EARTH:
                if (this.remainingEarth-amount>=0){
                    this.remainingEarth -= amount;
                } else {
                    System.out.println("Power insufficient");
                }
                break;
            case FIRE:
                if (this.remainingFire-amount>=0){
                    this.remainingFire -= amount; 
                } else {
                    System.out.println("Power insufficient");
                }
                break;
            case WATER:
                if (this.remainingWater-amount>=0){
                    this.remainingWater -= amount;
                } else {
                    System.out.println("Power insufficient");
                }
                break;
            case ENERGY:
                if (this.remainingEnergy-amount>=0){
                    this.remainingEnergy -= amount;
                } else {
                    System.out.println("Power insufficient");
                }
                break;
            default:
                System.out.println("Invalid element");
        }
    }
    
    /**
     * Mengubah nilai dari remaining power menjadi nilai dari power
     * dan playedLandThisRound menjadi false
     */
    public void resetStats(){
        this.remainingAir = this.airPower;
        this.remainingEarth = this.earthPower;
        this.remainingFire = this.firePower;
        this.remainingWater = this.waterPower;
        this.remainingEnergy = this.energyPower;
        this.playedLandThisRound = false; 
    }
    
    /**
     * Mengurangi atribut health
     * @param amount nilai yang akan dikurangi
     */
    public void takeDamage(int amount){
        this.health -= amount;
    }

    /**
     * Output status player
     */
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