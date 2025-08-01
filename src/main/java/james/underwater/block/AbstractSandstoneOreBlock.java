package james.underwater.block;

import james.underwater.item.tools.StoneChisel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractSandstoneOreBlock extends Block {

    public static final BooleanProperty HAS_RESOURCE = BooleanProperty.of("has_resource");

    public AbstractSandstoneOreBlock(Settings settings) {
        super(
                settings
                        .solid()
                        .hardness(0.8f)
        );
        setDefaultState(getDefaultState().with(HAS_RESOURCE, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HAS_RESOURCE);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!(stack.getItem() instanceof StoneChisel)) {
            return ActionResult.PASS;
        }
        if (!player.getAbilities().allowModifyWorld) {
            return  ActionResult.PASS;
        }

        if (!state.get(HAS_RESOURCE)) {
            return ActionResult.FAIL;
        }

        if (world.isClient()) {
            return ActionResult.PASS;
        }
        world.setBlockState(pos, state.with(HAS_RESOURCE, false));
        dropStack(world, pos, getChiselResource(state, (ServerWorld) world, pos));
        return ActionResult.SUCCESS;
    }

    public abstract ItemStack getChiselResource(BlockState state, ServerWorld world, BlockPos pos);

}
