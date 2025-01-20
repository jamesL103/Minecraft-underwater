package james.underwater;

import james.underwater.init.ScreenHandlerTypeInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class EquipmentScreenHandler extends ScreenHandler {


    //server constructor
    public EquipmentScreenHandler(int syncId, PlayerInventory inventory, PlayerEntity player) {
        super(ScreenHandlerTypeInit.EQUIPMENT_SCREEN_HANDLER, syncId);

        addPlayerInventorySlots(inventory, 8, 84);
        addPlayerHotbarSlots(inventory, 8, 142);
    }

    //client constructor
    public EquipmentScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, inventory.player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
