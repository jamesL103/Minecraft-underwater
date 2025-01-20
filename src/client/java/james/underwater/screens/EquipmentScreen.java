package james.underwater.screens;

import james.underwater.EquipmentScreenHandler;
import james.underwater.Underwater;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EquipmentScreen extends HandledScreen<EquipmentScreenHandler> {

    private static final Identifier TEXTURE = Underwater.id("textures/gui/container/equipment_screen.png");


    public EquipmentScreen(EquipmentScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }


    //draws the background of the screen
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(null,TEXTURE, this.x, this.y, 0,  0, this.backgroundWidth, this.backgroundHeight, 176, 166);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
