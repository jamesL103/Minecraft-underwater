package james.underwater.mixin.client;

import james.underwater.StateSaverAndLoader;
import james.underwater.UnderwaterClient;
import james.underwater.init.ComponentInit;
import james.underwater.inventory.PlayerEquipmentData;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityClientMixin extends Entity implements Attackable {


    private LivingEntityClientMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(at = @At("HEAD"), method = "getNextAirUnderwater", cancellable = true)
    private void getNextAirUnderwater(int air, CallbackInfoReturnable<Integer> cir) {
        //check if the entity is a player, and if they are listed as having an oxygen tank
        LivingEntity targetInstance = (LivingEntity)(Object)this;
        if (targetInstance instanceof PlayerEntity player) {
            PlayerEquipmentData equipment = UnderwaterClient.equipmentData;
            //check if player has a tank equipped
            ItemStack tankStack = equipment.getStack(PlayerEquipmentData.TANK_SLOT);
            if (tankStack != ItemStack.EMPTY && tankStack.contains(ComponentInit.TANK_AIR_COMPONENT)) {
                //check if there is air in the tank
                int airTime = tankStack.get(ComponentInit.TANK_AIR_COMPONENT);
                //if there is air in tank, decrement and don't update player's breath
                if (airTime > 0) {
                    tankStack.set(ComponentInit.TANK_AIR_COMPONENT, airTime - 1);
                    cir.setReturnValue(air);
                }
            }
        }
    }


}
