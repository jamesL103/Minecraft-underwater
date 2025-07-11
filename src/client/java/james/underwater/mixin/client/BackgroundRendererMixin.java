package james.underwater.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {

    @Unique
    private static final float FOG_END_INCREASE = 4;



    @Inject(method = "applyFog", at = @At(value = "INVOKE_ASSIGN", target="Lnet/minecraft/client/network/ClientPlayerEntity;getWorld()Lnet/minecraft/world/World;"))
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<Fog> cir, @Local BackgroundRenderer.FogData fogData, @Local ClientPlayerEntity clientPlayerEntity) {
        fogData.fogEnd += FOG_END_INCREASE;
    }


}
