package james.underwater;

import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIntArray;

public class OxygenTimeStatus {

    /**Max air int ticks**/
    public final int MAX_AIR;
    /**Current air in ticks**/
    private int currAir;

    public OxygenTimeStatus(int maxAirTicks) {
        this(maxAirTicks, maxAirTicks);
    }

    public OxygenTimeStatus(int maxAirTicks, int currAirTicks) {
        MAX_AIR = maxAirTicks;
        currAir = currAirTicks;
    }

    public int getCurrAir() {
        return currAir;
    }

    public void decrementAir() {
        currAir --;
    }

    public void incrementAir() {
        currAir = Math.min(currAir + 1, MAX_AIR);
    }

    public void incrementAir(int increase) {
        currAir = Math.min(currAir + increase, MAX_AIR);
    }


    /** Creates an NbtArray representing this oxygen data.
     *  Index 0 of the array stores the MAX_AIR value
     *  Index 1 stores the current air in ticks.
     *
     * @return NbtArray representing this data.
     */
    public NbtIntArray toNbt() {
        int[] vals = {MAX_AIR, currAir};
        return new NbtIntArray(vals);
    }


}
