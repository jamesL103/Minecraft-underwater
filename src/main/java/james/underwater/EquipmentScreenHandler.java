package james.underwater;

import james.underwater.init.ScreenHandlerTypeInit;
import james.underwater.inventory.EquipmentSlot;
import james.underwater.inventory.PlayerEquipmentData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

import java.awt.Point;

public class EquipmentScreenHandler extends ScreenHandler {


    public static final Point POS_TANK_SLOT = new Point(45, 31);
    public static final Point POS_HEAD_SLOT = new Point(103, 7);
    public static final Point POS_BODY_SLOT = new Point(103, 31);
    public static final Point POS_FOOT_SLOT = new Point(103, 55);
    public static final Point POS_LARM_SLOT = new Point(81, 31);
    public static final Point POS_RARM_SLOT = new Point(125, 31);


    //the equipment inventory of the player
    private final PlayerEquipmentData inventory;

    //server constructor
    public EquipmentScreenHandler(int syncId, PlayerInventory inventory, PlayerEntity player) {
        super(ScreenHandlerTypeInit.EQUIPMENT_SCREEN_HANDLER, syncId);

        if (player.getServer() != null) {
            this.inventory = StateSaverAndLoader.getPlayerState(player);
        } else {
            this.inventory = new PlayerEquipmentData();
        }

        addPlayerInventorySlots(inventory, 8, 84);
        addPlayerHotbarSlots(inventory, 8, 142);
        addEquipmentSlots();
    }

    //client constructor
    public EquipmentScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, inventory.player);
    }

    private void addEquipmentSlots() {
        //add tank slot
        addSlot(new EquipmentSlot(inventory, PlayerEquipmentData.TANK_SLOT, POS_TANK_SLOT.x, POS_TANK_SLOT.y));

        //head slot
        addSlot(new EquipmentSlot(inventory, PlayerEquipmentData.HEAD_SLOT, POS_HEAD_SLOT.x, POS_HEAD_SLOT.y));

        //body slot
        addSlot(new EquipmentSlot(inventory, PlayerEquipmentData.BODY_SLOT, POS_BODY_SLOT.x, POS_BODY_SLOT.y));

        //foot slot
        addSlot(new EquipmentSlot(inventory, PlayerEquipmentData.FOOT_SLOT, POS_FOOT_SLOT.x, POS_FOOT_SLOT.y));

        //left arm
        addSlot(new EquipmentSlot(inventory, PlayerEquipmentData.LARM_SLOT, POS_LARM_SLOT.x, POS_LARM_SLOT.y));

        //right arm
        addSlot(new EquipmentSlot(inventory, PlayerEquipmentData.RARM_SLOT, POS_RARM_SLOT.x, POS_RARM_SLOT.y));


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
