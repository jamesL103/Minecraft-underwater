package james.underwater;

public class OxygenTimeStatus {

    public final int MAX_AIR;
    private int currAir;

    public OxygenTimeStatus(int maxAirTicks) {
        MAX_AIR = maxAirTicks;
        currAir = MAX_AIR;
    }

    public int getCurrAir() {
        return currAir;
    }

    public void decrementAir() {
        currAir --;
    }

    public void incrementAir() {
        currAir ++;
    }

}
