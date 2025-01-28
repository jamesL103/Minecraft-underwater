package james.underwater.mixin;

import james.underwater.OxygenTimeStatus;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {

    //map that links every player with their oxygen tank status
    @Unique
    private static final Map<UUID, OxygenTimeStatus> PLAYER_OXYGEN = new HashMap<>();

    private LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "getNextAirUnderwater", cancellable = true)
    private void getNextAirUnderwater(int air, CallbackInfoReturnable<Integer> cir) {
        //check if the entity is a player, and if they are listed as having an oxygen tank
        LivingEntity targetInstance = (LivingEntity)(Object)this;
        if (targetInstance instanceof PlayerEntity player) {
            OxygenTimeStatus status = PLAYER_OXYGEN.get(player.getUuid());
            if (status != null) {

                //if air still in tank, decrement the air in tank and do not use up player's breath
                if (status.getCurrAir() > 0) {
                    status.decrementAir();
                    cir.setReturnValue(air);
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "getNextAirOnLand")
    private void getNextAirOnLand(int air, CallbackInfoReturnable<Integer> cir) {
        LivingEntity targetInstance = (LivingEntity)(Object)this;
        if (targetInstance instanceof PlayerEntity player) {
            OxygenTimeStatus status = PLAYER_OXYGEN.get(player.getUuid());
            if (status != null) {
                status.incrementAir(4);
            }
        }
    }

}


