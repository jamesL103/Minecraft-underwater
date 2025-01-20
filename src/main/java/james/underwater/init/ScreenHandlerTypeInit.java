package james.underwater.init;

import james.underwater.EquipmentScreenHandler;
import james.underwater.Underwater;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerTypeInit {

    public static final ScreenHandlerType<EquipmentScreenHandler> EQUIPMENT_SCREEN_HANDLER = register("equipment", EquipmentScreenHandler::new);

    public static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, Underwater.id(name), new ScreenHandlerType<>(factory, null));
    }

    public static void load() {

    }
}
