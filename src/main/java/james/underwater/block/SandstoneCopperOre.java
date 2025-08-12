package james.underwater.block;

import james.underwater.init.ItemInit;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class SandstoneCopperOre extends AbstractSandstoneOreBlock {

    public static final String ID = "sandstone_copper_ore";

    public SandstoneCopperOre(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getChiselResource(BlockState state, ServerWorld world, BlockPos pos) {
        LootWorldContext.Builder builder = (new LootWorldContext.Builder(world).add(LootContextParameters.ORIGIN, Vec3d.of(pos)).add(LootContextParameters.TOOL, new ItemStack(ItemInit.STONE_CHISEL, 1)));
        return state.getDroppedStacks(builder).getFirst();
    }
}
