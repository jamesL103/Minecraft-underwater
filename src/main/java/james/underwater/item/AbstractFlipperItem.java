package james.underwater.item;

import james.underwater.inventory.PlayerEquipmentData;

public abstract class AbstractFlipperItem extends AbstractUnderwaterEquipmentItem{

    public final int SPEED_BOOST;

    public AbstractFlipperItem(Settings settings, int speedBoost) {
        super(settings, PlayerEquipmentData.FOOT_SLOT);
        SPEED_BOOST = speedBoost;
    }
}


