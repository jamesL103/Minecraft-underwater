package james.underwater;

import james.underwater.providers.UnderwaterEnglishLanguageProvider;
import james.underwater.providers.UnderwaterModelGenerator;
import james.underwater.providers.UnderwaterRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class UnderwaterDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(UnderwaterEnglishLanguageProvider::new);
		pack.addProvider(UnderwaterRecipeProvider::new);
		pack.addProvider(UnderwaterModelGenerator::new);
	}
}
