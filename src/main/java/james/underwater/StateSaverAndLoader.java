package james.underwater;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

/** Class to save and load the equipment inventory for each player
 *
 */
public class StateSaverAndLoader extends PersistentState  {

    public HashMap<UUID, PlayerEquipmentData> players = new HashMap<>();

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        //compound storing map of player uuids to their equipment nbt
        NbtCompound playersNbt = new NbtCompound();

        players.forEach((uuid, playerEData) ->{
            NbtCompound playerEquipNbt = new NbtCompound();

            //iterate over every slot and put the item identifier as string in the individual's nbt
            for(int slot = 0; slot <PlayerEquipmentData.EQUIPMENT_SLOTS; slot ++) {
                ItemStack equipmentStack = playerEData.equipment.get(slot);
                NbtElement itemStackNbt = equipmentStack.toNbt(registries);
                playersNbt.put(String.valueOf(slot), itemStackNbt);
            }

            playersNbt.put(uuid.toString(), playerEquipNbt);

        });

        nbt.put("players", playersNbt);

        return nbt;
    }

    public static StateSaverAndLoader createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        StateSaverAndLoader state = new StateSaverAndLoader();

        NbtCompound playersNbt = tag.getCompound("players");

        //load every equipment set for each player
        playersNbt.getKeys().forEach(key ->{
           PlayerEquipmentData data = new PlayerEquipmentData();

           NbtCompound playerEquipNbt = playersNbt.getCompound(key);

           //load each item into the correct slot
           for (int slot = 0; slot < PlayerEquipmentData.EQUIPMENT_SLOTS; slot ++) {
                NbtElement itemStackNbt = playerEquipNbt.get(String.valueOf(slot));
                ItemStack equipmentStack = ItemStack.fromNbt(registryLookup, itemStackNbt).orElse(ItemStack.EMPTY);
                data.equipment.set(slot, equipmentStack);
           }

           //add the unique player's entry to the hashmap of players
           UUID uuid = UUID.fromString(key);
           state.players.put(uuid, data);
        });

        return state;
    }

    private static final Type<StateSaverAndLoader> type = new Type<>(
            StateSaverAndLoader::new,
            StateSaverAndLoader::createFromNbt,
            null
    );

    //takes a server and returns the StateSaverandLoader associated with it, or creates and returns one if one has not already been created
    public static StateSaverAndLoader getServerState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        StateSaverAndLoader state = persistentStateManager.getOrCreate(type, Underwater.MOD_ID);

        state.markDirty();

        return state;
    }

    //gets a living entity (player) and returns the PlayerEquipmentData associated with it
    public static PlayerEquipmentData getPlayerState(LivingEntity player) {
        StateSaverAndLoader serverState = getServerState(player.getWorld().getServer());

        //return the player data by uuid, or create their data if it doesn't exist yet
        return serverState.players.computeIfAbsent(player.getUuid(), uuid -> new PlayerEquipmentData());
    }

}
