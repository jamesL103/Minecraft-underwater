package james.underwater.block.item;

import james.underwater.Underwater;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class RaftBlockItem extends BlockItem {

    public RaftBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    //I just fucking stole this from PlaceableOnWaterItem
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.PASS;
    }

    //this is fucking stupid the PlaceableOnWaterItem method is almost fucking perfect except it sets the BlockPos to one higher
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        BlockHitResult blockHitResult2 = blockHitResult.withBlockPos(blockHitResult.getBlockPos());
        Underwater.LOGGER.info(blockHitResult2.getBlockPos().toShortString());
        return super.useOnBlock(new ItemUsageContext(user, hand, blockHitResult2));
    }
}
