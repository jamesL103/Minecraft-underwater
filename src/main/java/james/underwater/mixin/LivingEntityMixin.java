package james.underwater.mixin;

import james.underwater.OxygenTimeStatus;
import james.underwater.StateSaverAndLoader;
import james.underwater.init.ComponentInit;
import james.underwater.inventory.PlayerEquipmentData;
import james.underwater.item.AbstractTankItem;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {

    @Shadow public abstract void equipStack(EquipmentSlot slot, ItemStack stack);

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
        if (targetInstance instanceof PlayerEntity player && !player.getWorld().isClient) {
            PlayerEquipmentData equipment = StateSaverAndLoader.getPlayerState(player);
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

    @Inject(at = @At("HEAD"), method = "getNextAirOnLand")
    private void getNextAirOnLand(int air, CallbackInfoReturnable<Integer> cir) {
        LivingEntity targetInstance = (LivingEntity)(Object)this;
        if (targetInstance instanceof PlayerEntity player && !player.getWorld().isClient) {

            //check if oxygen tank is equipped
            PlayerEquipmentData equipment = StateSaverAndLoader.getPlayerState(player);
            ItemStack tankStack = equipment.getStack(PlayerEquipmentData.TANK_SLOT);
            if (tankStack != ItemStack.EMPTY && tankStack.contains(ComponentInit.TANK_AIR_COMPONENT)) {
                int airTime = tankStack.get(ComponentInit.TANK_AIR_COMPONENT);
                AbstractTankItem tank = (AbstractTankItem)(tankStack.getItem());
                //increment tank air if needed
                if (airTime < tank.MAX_AIR_TIME) {
                    tankStack.set(ComponentInit.TANK_AIR_COMPONENT, airTime + 1);
                }
            }
        }
    }

}


