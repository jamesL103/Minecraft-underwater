package james.underwater;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

/** Class containing the fields for individual player equipment data
 *
 */
public class PlayerEquipmentData {

    public final static int EQUIPMENT_SLOTS = 6;

    public DefaultedList<ItemStack> equipment = DefaultedList.ofSize(EQUIPMENT_SLOTS);

    public final static int HEAD_SLOT = 0;
    public final static int BODY_SLOT = 1;
    public final static int FOOT_SLOT = 2;
    public final static int TANK_SLOT = 3;
    public final static int RARM_SLOT = 4;
    public final static int LARM_SLOT = 5;


    public PlayerEquipmentData() {
        for (int i = 0; i < EQUIPMENT_SLOTS; i ++) {
            equipment.set(i, ItemStack.EMPTY);
        }
    }

}
