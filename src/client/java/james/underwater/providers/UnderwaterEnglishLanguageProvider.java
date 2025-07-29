package james.underwater.providers;

import james.underwater.EquipmentScreenHandlerFactory;
import james.underwater.Underwater;
import james.underwater.init.BlockInit;
import james.underwater.init.ItemInit;
import james.underwater.item.BasicFlipper;
import james.underwater.item.SuperFlipper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;

import java.util.concurrent.CompletableFuture;

public class UnderwaterEnglishLanguageProvider extends FabricLanguageProvider {

    public UnderwaterEnglishLanguageProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        // Specifying en_us is optional, by default it is en_us.
        super(dataGenerator, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ItemInit.UNDERWATER_ITEM_GROUP_KEY.getValue().toTranslationKey(), "Underwater Items");
        translationBuilder.add(ItemInit.BASIC_TANK.getName().getString(), "Basic Tank");
        translationBuilder.add("item.underwater." + BasicFlipper.ID, "Basic Flippers");
        translationBuilder.add("item.underwater." + SuperFlipper.ID, "Super Flippers");
        addText(translationBuilder, EquipmentScreenHandlerFactory.TITLE, "Equipment");
        translationBuilder.add("item.underwater.tank.info", "Storing %s seconds of air");
        translationBuilder.add("generator.underwater.water_world", "Water World");
        translationBuilder.add("generator.terra.water_world/water_world", "Terra Water World");
        translationBuilder.add(BlockInit.ROCK.asItem().getName().getString(), "Rock");
        translationBuilder.add(ItemInit.SHARPENED_ROCK.getName().getString(), "Sharpened Rock");
        translationBuilder.add(BlockInit.CORAL_PILE.asItem().getName().getString(), "Dead Coral Pile");
        translationBuilder.add(BlockInit.REEDS.asItem().getName().getString(), "Reeds");
        translationBuilder.add(ItemInit.SEAGRASS_BLADES.getName().getString(), "Seagrass Blades");
        translationBuilder.add(BlockInit.RAFT_BLOCK.asItem().getName().getString(), "Raft Block");
    }

    //helper to make Identifier out of Text and add it to datagen
    private static void addText(TranslationBuilder builder, Text text, String value) {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            builder.add(translatableTextContent.getKey(), value);
        } else {
            Underwater.LOGGER.warn("Failed to add translation key for text: {}", text.getString());
        }
    }

}
