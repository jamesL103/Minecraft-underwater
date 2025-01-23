package james.underwater.inventory;

import net.minecraft.screen.slot.Slot;

/** Slot that can only store Equipment
 *
 */
public class EquipmentSlot extends Slot {

    private final PlayerEquipmentData inventory;

    public EquipmentSlot(PlayerEquipmentData inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.inventory = inventory;
    }


}
