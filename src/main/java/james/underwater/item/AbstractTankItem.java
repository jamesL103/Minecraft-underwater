package james.underwater.item;

import james.underwater.inventory.PlayerEquipmentData;

public abstract class AbstractTankItem extends AbstractUnderwaterEquipmentItem {


    private static int airTime = 60;

    public AbstractTankItem(Settings settings, int airTime) {
        super(settings, PlayerEquipmentData.TANK_SLOT);
        AbstractTankItem.airTime = airTime;

    }

    private static void applySettings(Settings settings, int airTime) {
    }



}
