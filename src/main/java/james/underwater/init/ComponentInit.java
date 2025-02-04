package james.underwater.init;

import com.mojang.serialization.Codec;
import james.underwater.Underwater;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ComponentInit {

    //item component that stores the remaining air time in ticks of the item
    public static final ComponentType<Integer> TANK_AIR_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Underwater.id("tank_air_component"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );


    public static void load() {

    }

}
