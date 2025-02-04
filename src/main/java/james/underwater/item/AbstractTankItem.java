package james.underwater.item;

import james.underwater.init.ComponentInit;
import james.underwater.inventory.PlayerEquipmentData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public abstract class AbstractTankItem extends AbstractUnderwaterEquipmentItem {


    //the max air time in seconds that can be stored
    public final int MAX_AIR_TIME;

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.contains(ComponentInit.TANK_AIR_COMPONENT)) {
            int currAir = stack.get(ComponentInit.TANK_AIR_COMPONENT)/20;
            tooltip.add(Text.translatable("item.underwater.tank.info", currAir));
        }
    }

    public AbstractTankItem(Settings settings, int airTime) {
        super(settings, PlayerEquipmentData.TANK_SLOT);
        MAX_AIR_TIME = airTime;
    }

    private static void applySettings(Settings settings, int airTime) {
    }



}
