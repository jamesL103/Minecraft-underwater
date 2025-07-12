package james.underwater.item;

import james.underwater.inventory.PlayerEquipmentData;

public class Goggles extends AbstractUnderwaterEquipmentItem {

    public static final String ID = "goggles";

    public Goggles(Settings settings) {
        super(settings, PlayerEquipmentData.HEAD_SLOT);
    }

    @Override
    public String getId() {
        return ID;
    }
}
