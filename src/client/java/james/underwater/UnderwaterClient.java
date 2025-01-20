package james.underwater;

import james.underwater.init.ScreenHandlerTypeInit;
import james.underwater.network.OpenMenuPayload;
import james.underwater.screens.EquipmentScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;

public class UnderwaterClient implements ClientModInitializer {

	//opens the equipment menu
	public static final KeyBinding OPEN_MENU = KeyBindingHelper.registerKeyBinding(Keybinds.OPEN_MENU);

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		//register open_menu keybind
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (OPEN_MENU.wasPressed()) {
                if (client.player != null) {
					ClientPlayNetworking.send(new OpenMenuPayload((byte)0));
				}
			}
		});

		HandledScreens.register(ScreenHandlerTypeInit.EQUIPMENT_SCREEN_HANDLER, EquipmentScreen::new);
	}
}