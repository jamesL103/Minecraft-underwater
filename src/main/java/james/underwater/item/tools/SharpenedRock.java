package james.underwater.item.tools;

import james.underwater.Underwater;
import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class SharpenedRock extends MiningToolItem {

    public static final String ID = "sharpened_rock";

    private static final int STARTER_DURABILITY = 100;
    private static final float STARTER_MINING_SPEED = 2;
    private static final float STARTER_ATTACK = 1;
    private static final int STARTER_ENCHANTABILITY = 1;

    private static final int ATTACK_SPEED = 4;

    public final static TagKey<Block> EFFECTIVE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, Underwater.id(ID));



    public static final ToolMaterial STARTER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            STARTER_DURABILITY,
            STARTER_MINING_SPEED,
            STARTER_ATTACK,
            STARTER_ENCHANTABILITY,
            null
    );

    public SharpenedRock(Settings settings) {
        super(STARTER, EFFECTIVE_BLOCKS, STARTER_ATTACK, ATTACK_SPEED, settings);
    }

    /*
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (block instanceof SeagrassBlock || block instanceof TallSeagrassBlock) {
            PlayerEntity playerEntity = context.getPlayer();
            ItemStack itemStack = context.getStack();
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, pos, itemStack);
            }

            world.playSound(playerEntity, pos, SoundEvents.BLOCK_WET_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            BlockState blockState2 = abstractPlantStemBlock.withMaxAge(blockState);
            world.setBlockState(blockPos, blockState2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(context.getPlayer(), blockState2));
            if (playerEntity != null) {
                itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
            }

            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(context);
    }
    */
}
