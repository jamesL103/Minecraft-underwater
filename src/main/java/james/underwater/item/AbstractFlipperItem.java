package james.underwater.item;

import james.underwater.inventory.PlayerEquipmentData;

public class AbstractFlipperItem extends AbstractUnderwaterEquipmentItem{

    private static int SPEED_BOOST;

    public AbstractFlipperItem(Settings settings, int speedBoost) {
        super(settings, PlayerEquipmentData.FOOT_SLOT);
        SPEED_BOOST = speedBoost;
    }
}


