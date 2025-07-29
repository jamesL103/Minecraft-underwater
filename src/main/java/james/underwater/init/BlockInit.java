package james.underwater.init;

import james.underwater.Underwater;
import james.underwater.block.CoralPile;
import james.underwater.block.RaftBlock;
import james.underwater.block.Rock;
import james.underwater.block.item.RaftBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.PlaceableOnWaterItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Function;

public class BlockInit {

    private static final boolean REGISTER_ITEM = true;
    private static final boolean NO_REGISTER_ITEM = false;

    public static final Block ROCK = register(
            Rock.ID,
            Rock::new,
            AbstractBlock.Settings.create().
                    sounds(BlockSoundGroup.STONE)
                    .noCollision()
                    .hardness(1.5f),
            REGISTER_ITEM
    );

    public static final Block CORAL_PILE = register(
            CoralPile.ID,
            CoralPile::new,
            AbstractBlock.Settings.create()
                    .noCollision()
                    .breakInstantly()
                    .nonOpaque(),
            REGISTER_ITEM
    );

    public static final Block REEDS = register(
        "reeds",
            Block::new,
            AbstractBlock.Settings.create()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS),
            REGISTER_ITEM
    );

    public static final Block RAFT_BLOCK = registerRaftBlock();


    public static void load() {

    }

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static Block registerRaftBlock() {
        RegistryKey<Block> blockKey = keyOfBlock(RaftBlock.ID);
        AbstractBlock.Settings settings = AbstractBlock.Settings.create()
                .registryKey(blockKey)
                .sounds(BlockSoundGroup.WOOD)
                .nonOpaque()
                .solid();
        RaftBlock block = new RaftBlock(settings);

        RegistryKey<Item> itemKey = keyOfItem(RaftBlock.ID);
        RaftBlockItem blockItem = new RaftBlockItem(block, new Item.Settings().registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, blockItem);

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name){
        return RegistryKey.of(RegistryKeys.BLOCK, Underwater.id(name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Underwater.id(name));
    }
}

