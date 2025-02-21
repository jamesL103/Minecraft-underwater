package james.underwater.init;

import james.underwater.Underwater;
import james.underwater.UnderwaterClient;
import james.underwater.inventory.PlayerEquipmentData;
import james.underwater.item.AbstractTankItem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class RenderInit {


    public static void load() {

        HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
            ItemStack tankStack = UnderwaterClient.equipmentData.getStack(PlayerEquipmentData.TANK_SLOT);
            if (!tankStack.isEmpty()) {
                //get ID of the tank item that is equipped
                String tankId = ((AbstractTankItem)tankStack.getItem()).getId() + ".png";
                Identifier tankTexture = Identifier.of(Underwater.MOD_ID, "textures/item/" + tankId);

                int tankX = (int)(0.1 * drawContext.getScaledWindowWidth());
                int tankY = (int)(0.8 * drawContext.getScaledWindowHeight());

                //render the tank item icon in the hud
                drawContext.drawTexture(RenderLayer::getGuiTextured, tankTexture, tankX, tankY, 0, 0, 16, 16, 16, 16);

                //render oxygen counter
                if (tankStack.contains(ComponentInit.TANK_AIR_COMPONENT)) {
                    int oxygenX = tankX;
                    int oxygenY = tankY + 20;

                    int airTime = tankStack.get(ComponentInit.TANK_AIR_COMPONENT);
                    drawContext.drawText(MinecraftClient.getInstance().textRenderer, "O2: " + (airTime/20) + "s", oxygenX, oxygenY, 0xFFFFFFFF, false);
                }
            }
        }));

    }


}
