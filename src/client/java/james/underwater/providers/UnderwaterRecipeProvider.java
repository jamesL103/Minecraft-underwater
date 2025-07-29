package james.underwater.providers;

import james.underwater.init.BlockInit;
import james.underwater.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class UnderwaterRecipeProvider extends FabricRecipeProvider {

    public UnderwaterRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                createShapeless(RecipeCategory.TOOLS, ItemInit.SHARPENED_ROCK)
                        .input(BlockInit.ROCK.asItem(), 2)
                        .criterion(hasItem(BlockInit.ROCK), conditionsFromItem(BlockInit.ROCK))
                        .offerTo(recipeExporter);
                createShapeless(RecipeCategory.MISC, ItemInit.PLANT_FIBER)
                        .input(ItemInit.SEAGRASS_BLADES, 3)
                        .criterion(hasItem(ItemInit.SEAGRASS_BLADES), conditionsFromItem(ItemInit.SEAGRASS_BLADES))
                        .offerTo(recipeExporter);
                createShaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.RAFT_BLOCK.asItem(), 1)
                        .pattern("ss")
                        .pattern("ss")
                        .input('s', Items.STICK)
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(recipeExporter);
            }
        };
    }

    @Override
    public String getName() {
        return "UnderwaterRecipeProvider";
    }
}
