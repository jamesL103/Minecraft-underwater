package james.underwater.inventory;

import james.underwater.item.AbstractUnderwaterEquipmentItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

/** Slot that can only store Equipment
 *
 */
public class EquipmentSlot extends Slot {

    protected final PlayerEquipmentData inventory;

    public EquipmentSlot(PlayerEquipmentData inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.inventory = inventory;
    }

    @Override
    public boolean canInsert(ItemStack itemStack) {
        if (itemStack.getItem() instanceof AbstractUnderwaterEquipmentItem) {
            AbstractUnderwaterEquipmentItem item = (AbstractUnderwaterEquipmentItem)(itemStack.getItem());
            return getIndex() == item.getSlot();
        }
        return false;
    }


}
