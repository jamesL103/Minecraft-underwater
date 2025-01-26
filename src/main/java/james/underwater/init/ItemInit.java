package james.underwater.init;

import james.underwater.Underwater;
import james.underwater.item.BasicFlipperItem;
import james.underwater.item.BasicOxygenTank;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ItemInit {

    public static final RegistryKey<Item> BASIC_TANK_KEY = RegistryKey.of(RegistryKeys.ITEM, Underwater.id(BasicOxygenTank.ID));
    public static final BasicOxygenTank BASIC_TANK = Registry.register(Registries.ITEM, Underwater.id(BasicOxygenTank.ID), new BasicOxygenTank(new Item.Settings().registryKey(BASIC_TANK_KEY)));

    public static final RegistryKey<Item> BASIC_FLIPPER_KEY = RegistryKey.of(RegistryKeys.ITEM, Underwater.id(BasicFlipperItem.ID));
    public static final BasicFlipperItem BASIC_FLIPPER = Registry.register(Registries.ITEM, Underwater.id(BasicFlipperItem.ID), new BasicFlipperItem(new Item.Settings().registryKey(BASIC_FLIPPER_KEY)));

    public static void load() {

    }


}

