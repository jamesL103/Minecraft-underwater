package james.underwater.item;

import james.underwater.init.ComponentInit;
import james.underwater.inventory.PlayerEquipmentData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public abstract class AbstractTankItem extends AbstractUnderwaterEquipmentItem {


    private static int airTime = 60;

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int currAir = stack.get(ComponentInit.TANK_AIR_COMPONENT);
        tooltip.add(Text.translatable("item.underwater.tank.info", currAir));
    }

    public AbstractTankItem(Settings settings, int airTime) {
        super(settings, PlayerEquipmentData.TANK_SLOT);
        AbstractTankItem.airTime = airTime;

    }

    private static void applySettings(Settings settings, int airTime) {
    }



}
