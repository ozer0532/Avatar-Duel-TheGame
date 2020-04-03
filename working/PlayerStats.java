// PlayerStats.java

public class PlayerStats {
    private int health;
    private int airPower;
    private int earthPower;
    private int firePower;
    private int waterPower;
    private boolean playedLandThisRound;
    private int remainingAir;
    private int remainingEarth;
    private int remainingFire;
    private int remainingWater;

    // Constructor
    public PlayerStats(){
        this.health = 0;
        this.airPower = 0;
        this.earthPower = 0;
        this.firePower = 0;
        this.waterPower = 0;
        this.playedLandThisRound = false;
        this.remainingAir = 0;
        this.remainingEarth = 0;
        this.remainingFire = 0;
        this.remainingWater = 0;
    }

    // Getter
    public int getHealth(){
        return this.health;
    }

    public int getAirPower(){
        return this.airPower;
    }

    public int getEarthPower(){
        return this.earthPower;
    }

    public int getFirePower(){
        return this.firePower;
    }

    public int getWaterPower(){
        return this.waterPower;
    }

    public boolean getPlayedLandThisRound(){
        return this.playedLandThisRound;
    }

    public int getRemainingAir(){
        return this.remainingAir;
    }

    public int getRemainingEarth(){
        return this.remainingEarth;
    }

    public int getRemainingFire(){
        return this.remainingFire;
    }

    public int getRemainingWater(){
        return this.remainingWater;
    }

    // Setter
    public void setHealth(int val){
        this.health = val;
    }

    public void setAirPower(int val){
        this.airPower = val;
    }

    public void setEarthPower(int val){
        this.earthPower = val;
    }

    public void setFirePower(int val){
        this.firePower = val;
    }

    public void setWaterPower(int val){
        this.waterPower = val;
    }

    public void setPlayedLandThisRound(boolean val){
        this.playedLandThisRound = val;
    }

    public void setRemainingAir(int val){
        this.remainingAir = val;
    }

    public void setRemainingEarth(int val){
        this.remainingEarth = val;
    }

    public void setRemainingFire(int val){
        this.remainingFire = val;
    }

    public void setRemainingWater(int val){
        this.remainingWater = val;
    }

    public void resetStats(){
        this.remainingAir = 0;
        this.remainingEarth = 0;
        this.remainingFire = 0;
        this.remainingWater = 0;
    }

    public boolean usePower(String element){
        if (element.equals("air")){
            this.remainingAir -= 1;
        }
        else if (element.equals("earth")){
            this.remainingEarth -= 1;
        }
        else if (element.equals("fire")){
            this.remainingFire -= 1;
        }
        else if (element.equals("water")){
            this.remainingWater -= 1;
        }
        else {
            // Throw Exception
            return false;
        }
        return true;
    }

    public void takeDamage(int amount){
        this.health -= amount;
    }

    public void printInfo(){
        System.out.println("Health: "+getHealth());
        System.out.println("Air Power: "+getAirPower());
        System.out.println("Earth Power: "+getEarthPower());
        System.out.println("Fire Power: "+getFirePower());
        System.out.println("Water Power: "+getWaterPower());
        System.out.println("Played Land this round: "+getPlayedLandThisRound());
        System.out.println("Remaining Air Power: "+getRemainingAir());
        System.out.println("Remaining Earth Power: "+getRemainingEarth());
        System.out.println("Remaining Fire Power: "+getRemainingFire());
        System.out.println("Remaining Water Power: "+getRemainingWater());
    }
}