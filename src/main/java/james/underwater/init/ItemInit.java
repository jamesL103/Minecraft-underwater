package james.underwater.init;

import james.underwater.Underwater;
import james.underwater.item.BasicFlipperItem;
import james.underwater.item.BasicOxygenTank;
import james.underwater.item.SuperFlipperItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

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

    public static final RegistryKey<Item> BASIC_FLIPPER_KEY = RegistryKey.of(RegistryKeys.ITEM, Underwater.id(BasicFlipperItem.ID));
    public static final BasicFlipperItem BASIC_FLIPPER = Registry.register(Registries.ITEM, Underwater.id(BasicFlipperItem.ID), new BasicFlipperItem(new Item.Settings().registryKey(BASIC_FLIPPER_KEY)));

    public static final RegistryKey<Item> SUPER_FLIPPER_KEY = RegistryKey.of(RegistryKeys.ITEM, Underwater.id(SuperFlipperItem.Id));
    public static final SuperFlipperItem SUPER_FLIPPER_ITEM = Registry.register(Registries.ITEM, Underwater.id(SuperFlipperItem.Id), new SuperFlipperItem(new Item.Settings().registryKey(SUPER_FLIPPER_KEY)));

    public static void load() {
        Registry.register(Registries.ITEM_GROUP, UNDERWATER_ITEM_GROUP_KEY, UNDERWATER_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(UNDERWATER_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(BASIC_TANK);
            itemGroup.add(BASIC_FLIPPER);
            itemGroup.add(SUPER_FLIPPER_ITEM);
        });
    }


}

