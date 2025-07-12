package james.underwater.init;

import james.underwater.Underwater;
import james.underwater.item.BasicFlipper;
import james.underwater.item.BasicOxygenTank;
import james.underwater.item.Goggles;
import james.underwater.item.SuperFlipper;
import james.underwater.item.tools.SharpenedRock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.function.Function;

public class ItemInit {

    public static final RegistryKey<ItemGroup> UNDERWATER_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Underwater.id("underwater_group"));
    public static final ItemGroup UNDERWATER_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.WATER_BUCKET))
            .displayName(Text.translatable("itemGroup.underwater.water_items"))
            .build();

    public static final RegistryKey<Item> BASIC_TANK_KEY = RegistryKey.of(RegistryKeys.ITEM, Underwater.id(BasicOxygenTank.ID));
    public static final BasicOxygenTank BASIC_TANK = Registry.register(Registries.ITEM, Underwater.id(BasicOxygenTank.ID), new BasicOxygenTank(
            new Item.Settings()
                    .registryKey(BASIC_TANK_KEY)
                    .component(ComponentInit.TANK_AIR_COMPONENT, BasicOxygenTank.BASIC_TANK_MAX_AIR * 20)
    ));

    public static final RegistryKey<Item> BASIC_FLIPPER_KEY = RegistryKey.of(RegistryKeys.ITEM, Underwater.id(BasicFlipper.ID));
    public static final BasicFlipper BASIC_FLIPPER = Registry.register(Registries.ITEM, Underwater.id(BasicFlipper.ID), new BasicFlipper(new Item.Settings().registryKey(BASIC_FLIPPER_KEY)));

    public static final RegistryKey<Item> SUPER_FLIPPER_KEY = RegistryKey.of(RegistryKeys.ITEM, Underwater.id(SuperFlipper.ID));
    public static final SuperFlipper SUPER_FLIPPER = Registry.register(Registries.ITEM, Underwater.id(SuperFlipper.ID), new SuperFlipper(new Item.Settings().registryKey(SUPER_FLIPPER_KEY)));

    public static final RegistryKey<Item> SHARPENED_ROCK_KEY = RegistryKey.of(Registries.ITEM.getKey(), Underwater.id(SharpenedRock.ID));
    public static final SharpenedRock SHARPENED_ROCK = Registry.register(Registries.ITEM, Underwater.id(SharpenedRock.ID), new SharpenedRock(new Item.Settings().registryKey(SHARPENED_ROCK_KEY).maxCount(1)));

    public static final RegistryKey<Item> SEAGRASS_BLADES_KEY = RegistryKey.of(RegistryKeys.ITEM, Underwater.id("seagrass_blades"));
    public static final Item SEAGRASS_BLADES = Registry.register(Registries.ITEM, Underwater.id("seagrass_blades"), new Item(new Item.Settings()
            .registryKey(SEAGRASS_BLADES_KEY)
    ));

    public static final Item GOGGLES = registerItem(Goggles.ID, Goggles::new, new Item.Settings());




    public static Item registerItem(String id, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Underwater.id(id));
        Item item = itemFactory.apply(settings.registryKey(registryKey));
        return Registry.register(Registries.ITEM, Underwater.id(id), item);
    }

    public static void load() {
        Registry.register(Registries.ITEM_GROUP, UNDERWATER_ITEM_GROUP_KEY, UNDERWATER_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(UNDERWATER_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(BASIC_TANK);
            itemGroup.add(BASIC_FLIPPER);
            itemGroup.add(SUPER_FLIPPER);
            itemGroup.add(SHARPENED_ROCK);
        });
    }


}

