package james.underwater;

import james.underwater.inventory.PlayerEquipmentData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class EquipmentScreenHandlerFactory implements NamedScreenHandlerFactory {

    public static final Text TITLE = Text.translatable("container." + Underwater.MOD_ID + ".equipment_screen");

    @Override
    public Text getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EquipmentScreenHandler(syncId, playerInventory, player);
    }
}
