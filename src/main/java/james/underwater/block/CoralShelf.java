package james.underwater.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;


public class CoralShelf extends HorizontalFacingBlock {

    public static final String ID = "coral_shelf";

    public static final MapCodec<CoralShelf> CODEC = Block.createCodec(CoralShelf::new);

    public static final BooleanProperty WATERLOGGED = BooleanProperty.of("waterlogged");

    private static final float LENGTH = 14, HEIGHT = 1, WIDTH = 10;

    public CoralShelf(Settings settings) {
        super(settings
                .solid()
                .hardness(1.2f)
                .sounds(BlockSoundGroup.CORAL)
        );

        setDefaultState(getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(WATERLOGGED);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        return switch (dir){
            case NORTH -> VoxelShapes.cuboid(1/16f, 8f/16, (16-WIDTH)/16, (1+LENGTH)/16, (8+HEIGHT)/16, 1f);
            case SOUTH -> VoxelShapes.cuboid(1/16f, 8f/16, 0f, (1+LENGTH)/16, (8+HEIGHT)/16, WIDTH/16);
            case WEST -> VoxelShapes.cuboid((16-WIDTH)/16f, 8f/16, 1f/16, 1f, (8+HEIGHT)/16, (1+LENGTH)/16);
            case EAST -> VoxelShapes.cuboid(0, 8f/16, 1f/16, WIDTH/16, (8+HEIGHT)/16, (1+LENGTH)/16);
            default -> VoxelShapes.fullCube();
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx)
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED,ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
