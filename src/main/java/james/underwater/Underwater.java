package james.underwater;

import james.underwater.init.NetworkInit;
import james.underwater.init.ScreenHandlerTypeInit;
import net.fabricmc.api.ModInitializer;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Underwater implements ModInitializer {
	public static final String MOD_ID = "underwater";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//returns a string with the mod id added to the front of the specified namespace
	public static Identifier id(String text) {
		return Identifier.of(MOD_ID + ":" + text);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ScreenHandlerTypeInit.load();
		NetworkInit.load();
	}
}