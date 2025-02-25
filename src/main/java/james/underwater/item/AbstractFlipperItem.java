package james.underwater.item;

import james.underwater.inventory.PlayerEquipmentData;

public abstract class AbstractFlipperItem extends AbstractUnderwaterEquipmentItem{

    /** A float between the values 0.0 (non-inclusive) and 1.0 (inclusive) that represents
     * a multiplier of the underwater speed multiplier
     */
    public final float SPEED_BOOST;

    public AbstractFlipperItem(Settings settings, float speedBoost) {
        super(settings, PlayerEquipmentData.FOOT_SLOT);
        SPEED_BOOST = speedBoost;
    }
}


