package james.underwater.block;

import james.underwater.item.tools.StoneChisel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractSandstoneOreBlock extends Block {

    public static final IntProperty STORED_RESOURCE = IntProperty.of("stored_resource", 0, 1);

    public AbstractSandstoneOreBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(STORED_RESOURCE, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STORED_RESOURCE);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!(stack.getItem() instanceof StoneChisel)) {
            return ActionResult.PASS;
        }
        if (!player.getAbilities().allowModifyWorld) {
            return  ActionResult.PASS;
        }

        int resourceLeft = state.get(STORED_RESOURCE);
        if (resourceLeft < 1) {
            return ActionResult.FAIL;
        }

        world.setBlockState(pos, state.with(STORED_RESOURCE, resourceLeft - 1));
        dropStack(world, pos, new ItemStack(getResource(), getDropCount(state)));
        return ActionResult.SUCCESS;
    }

    public abstract Item getResource();

    // SHOULD NOT DROP MORE THAN REMAINING RESOURCES
    public abstract int getDropCount(BlockState state);

}
