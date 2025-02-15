package james.underwater.providers;

import james.underwater.EquipmentScreenHandlerFactory;
import james.underwater.Underwater;
import james.underwater.item.BasicFlipperItem;
import james.underwater.item.BasicOxygenTank;
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
        translationBuilder.add("item.underwater." + BasicOxygenTank.ID, "Basic Tank");
        translationBuilder.add("item.underwater." + BasicFlipperItem.ID, "Basic Flippers");
        addText(translationBuilder, EquipmentScreenHandlerFactory.TITLE, "Equipment");
        translationBuilder.add("item.underwater.tank.info", "Storing %s seconds of air");
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
