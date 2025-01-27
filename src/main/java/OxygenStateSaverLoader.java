import james.underwater.OxygenTimeStatus;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;

/** Class to store and load the states of player's oxygen tanks
 * fill levels.
 *
 */
public class OxygenStateSaverLoader extends PersistentState {
    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        return null;
    }


    public static OxygenStateSaverLoader createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {

    }

    private static final Type<OxygenStateSaverLoader> type = new Type<>(
            OxygenStateSaverLoader::new,
            OxygenStateSaverLoader::createFromNbt,
            null
    );

    public static OxygenStateSaverLoader getServerState(MinecraftServer server) {

    }

    public static OxygenTimeStatus getPlayerState(LivingEntity player) {

    }

}
