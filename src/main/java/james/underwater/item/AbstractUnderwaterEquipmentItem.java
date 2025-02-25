package james.underwater.item;

import net.minecraft.item.Item;

public abstract class AbstractUnderwaterEquipmentItem extends Item {

    private final int SLOT;

    public AbstractUnderwaterEquipmentItem(Settings settings, int slot) {
        super(applySettings(settings));
        settings.maxCount(1);
        SLOT = slot;
    }

    public int getSlot() {
        return SLOT;
    }

    private static Settings applySettings(Settings settings) {
        settings.maxCount(1);
        return settings;
    }

    public abstract String getId();


}
