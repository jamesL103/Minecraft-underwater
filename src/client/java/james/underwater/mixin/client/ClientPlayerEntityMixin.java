package james.underwater.mixin.client;

import com.mojang.authlib.GameProfile;
import james.underwater.UnderwaterClient;
import james.underwater.inventory.PlayerEquipmentData;
import james.underwater.item.Goggles;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "getUnderwaterVisibility", at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerEntity;underwaterVisibilityTicks:I", ordinal = 0), cancellable = true)
    public void getUnderwaterVisibility(CallbackInfoReturnable<Float> cir) {
        PlayerEquipmentData equipmentInventory = UnderwaterClient.equipmentData;
        if (equipmentInventory.getStack(PlayerEquipmentData.HEAD_SLOT).getItem() instanceof Goggles) {
            cir.setReturnValue(1.0f);
        }
    }


}
