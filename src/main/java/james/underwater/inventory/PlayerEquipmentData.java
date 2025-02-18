package james.underwater.inventory;

import james.underwater.StateSaverAndLoader;
import james.underwater.network.SyncEquipmentPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;

/** Class containing the fields for individual player equipment data
 *
 */
public class PlayerEquipmentData implements Inventory {

    public final static int EQUIPMENT_SLOTS = 6;

    public DefaultedList<ItemStack> equipment = DefaultedList.ofSize(EQUIPMENT_SLOTS, ItemStack.EMPTY);

    public final static int HEAD_SLOT = 0;
    public final static int BODY_SLOT = 1;
    public final static int FOOT_SLOT = 2;
    public final static int TANK_SLOT = 3;
    public final static int RARM_SLOT = 4;
    public final static int LARM_SLOT = 5;

    public PlayerEquipmentData() {
        super();
    }

    @Override
    public int size() {
        return EQUIPMENT_SLOTS;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < EQUIPMENT_SLOTS; i ++) {
            if (equipment.get(i) != ItemStack.EMPTY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot >= 0 && slot < EQUIPMENT_SLOTS) {
            return equipment.get(slot);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (slot >= 0 && slot < EQUIPMENT_SLOTS) {
            ItemStack item = equipment.get(slot);
            equipment.set(slot, ItemStack.EMPTY);
            return item;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack removeStack(int slot) {
        return removeStack(slot, 1);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot >= 0 && slot < EQUIPMENT_SLOTS) {
            equipment.set(slot, stack);
        }
    }

    @Override
    public void markDirty() {

    }

    @Override
    public void onClose(PlayerEntity player) {
        if (!player.getWorld().isClient) {
            //save the inventory data when equipment screen closes
            StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(player.getServer());
            serverState.players.replace(player.getUuid(), this);
            serverState.markDirty();

            //send the updated inventory to the player
            ServerPlayNetworking.send((ServerPlayerEntity) player, new SyncEquipmentPayload(writeNbt(new NbtCompound(), player.getRegistryManager())));
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        equipment.clear();
    }

    //writes the inventory contents to the specified NbtCompound
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        Inventories.writeNbt(nbt, equipment, registries);
        return nbt;
    }

    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        clear();

        Inventories.readNbt(nbt, equipment, registries);
    }

}
