// PlayerStatsMain.java

public class PlayerStatsMain {
    public static void main(String[] args){
        System.out.println("Player 1:");
        PlayerStats Player1 = new PlayerStatsBuilder().health(100).airPower(50).earthPower(50).firePower(25).
        waterPower(45).playedLandThisRound(true).remainingAir(5).remainingEarth(5).remainingFire(4).remainingWater(3).build();
        Player1.printInfo();

        System.out.println();
        System.out.println("Player 2:");
        PlayerStats Player2 = new PlayerStatsBuilder().health(100).airPower(75).earthPower(75).firePower(25).
        waterPower(45).playedLandThisRound(false).remainingAir(7).remainingEarth(7).remainingFire(4).remainingWater(3).build();
        Player2.printInfo();
    }
}