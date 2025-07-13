package james.underwater.block;


import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class Rock extends Block implements Waterloggable {

    public static final String ID = "rock";

    public static final float MODEL_SIZE_X = 5.0f/16;
    public static final float MODEL_SIZE_Y = 2.0f/16;
    public static final float MODEL_SIZE_Z = 6.0f/16;
    public static final float START_POS_X = 5f/16;
    public static final float START_POS_Y = 0f/16;
    public static final float START_POS_Z = 5f/16;



    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public Rock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
            .with(WATERLOGGED, false)
        );
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState()
                .with(WATERLOGGED, context.getWorld().getFluidState(context.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.getMainHandStack().isEmpty()) {
            return ActionResult.PASS;
        }
        PlayerInventory inventory = player.getInventory();
        inventory.offerOrDrop(new ItemStack(asItem(), 1));
        world.removeBlock(pos, false);
        return ActionResult.SUCCESS;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(START_POS_X, START_POS_Y, START_POS_Z, START_POS_X + MODEL_SIZE_X, START_POS_Y + MODEL_SIZE_Y, START_POS_Z + MODEL_SIZE_Z);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolidBlock(world, pos);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        if (!canPlaceAt(state, world, pos)) {
            if (state.get(WATERLOGGED)) {
                return Blocks.WATER.getDefaultState();
            } else {
                return Blocks.AIR.getDefaultState();
            }
        } else {
            if (state.get(WATERLOGGED)) {
                tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }
        }
        return state;
    }

}
