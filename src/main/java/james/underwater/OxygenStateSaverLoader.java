package james.underwater;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/** Class to store and load the states of player's oxygen tanks
 * fill levels.
 *
 */
public class OxygenStateSaverLoader extends PersistentState {

    private Map<UUID, OxygenTimeStatus> players = new HashMap<>();


    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        players.forEach((uuid, data) -> {
            nbt.put(uuid.toString(), data.toNbt());
        });

        return nbt;
    }


    public static OxygenStateSaverLoader createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        OxygenStateSaverLoader state = new OxygenStateSaverLoader();

        tag.getKeys().forEach(uuid -> {
            int[] vals = tag.getIntArray(uuid);

            OxygenTimeStatus status = new OxygenTimeStatus(vals[0], vals[1]);
            state.players.put(UUID.fromString(uuid), status);
        });

        return state;

    }

    private static final Type<OxygenStateSaverLoader> type = new Type<>(
            OxygenStateSaverLoader::new,
            OxygenStateSaverLoader::createFromNbt,
            null
    );

    public static OxygenStateSaverLoader getServerState(MinecraftServer server) {
        PersistentStateManager manager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        OxygenStateSaverLoader state = manager.getOrCreate(type, Underwater.MOD_ID);

        state.markDirty();

        return state;
    }

    /**returns the player's oxygen data if it exists or null
     *
     * @param player the player to get data for
     * @return the player's data or null if it doesn't exist
     */
    public static OxygenTimeStatus getPlayerState(LivingEntity player) {
        OxygenStateSaverLoader state = getServerState(player.getServer());

        return state.players.get(player.getUuid());
    }

}
