package james.underwater.providers;

import james.underwater.init.BlockInit;
import james.underwater.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class UnderwaterBlockLootProvider extends FabricBlockLootTableProvider {


    public UnderwaterBlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockInit.REEDS, LootTable.builder().pool(LootPool.builder()
                .rolls(new UniformLootNumberProvider(new ConstantLootNumberProvider(3), new ConstantLootNumberProvider(5)))
                .with(ItemEntry.builder(Items.STICK))
        ));
        addDrop(
                BlockInit.SANDSTONE_FLINT_ORE,
                LootTable.builder().pool(
                        LootPool.builder()
                                .rolls(new UniformLootNumberProvider(new ConstantLootNumberProvider(1), new ConstantLootNumberProvider(2)))
                                .with(ItemEntry.builder(Items.FLINT))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Registries.ITEM, ItemInit.STONE_CHISEL)))
                )
        );
        addDrop(
                BlockInit.SANDSTONE_IRON_ORE,
                LootTable.builder().pool(
                        LootPool.builder()
                                .rolls(new UniformLootNumberProvider(new ConstantLootNumberProvider(2), new ConstantLootNumberProvider(4)))
                                .with(ItemEntry.builder(Items.IRON_NUGGET))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Registries.ITEM, ItemInit.STONE_CHISEL)))
                )
        );
        addDrop(
                BlockInit.SANDSTONE_COPPER_ORE,
                LootTable.builder().pool(
                        LootPool.builder()
                                .rolls(new UniformLootNumberProvider(new ConstantLootNumberProvider(2), new ConstantLootNumberProvider(4)))
                                .with(ItemEntry.builder(ItemInit.RAW_COPPER_NUGGET))
                                .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Registries.ITEM, ItemInit.STONE_CHISEL)))
                )
        );
    }
}
