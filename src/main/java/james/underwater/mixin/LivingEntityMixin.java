package james.underwater.mixin;

import james.underwater.StateSaverAndLoader;
import james.underwater.init.ComponentInit;
import james.underwater.inventory.PlayerEquipmentData;
import james.underwater.item.AbstractTankItem;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {

    private LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "getNextAirUnderwater", cancellable = true)
    private void getNextAirUnderwater(int air, CallbackInfoReturnable<Integer> cir) {
        //check if the entity is a player, and if they are listed as having an oxygen tank
        LivingEntity targetInstance = (LivingEntity)(Object)this;
        if (targetInstance instanceof PlayerEntity player) {
            if (!player.getWorld().isClient) {
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
    }

    @Inject(at = @At("HEAD"), method = "baseTick")
    private void baseTick(CallbackInfo ci) {

        //check if living entity is a playerEntity
        LivingEntity targetInstance = (LivingEntity)(Object)this;

        if (targetInstance instanceof PlayerEntity player) {

            //check if underwater first
            if (player.isSubmergedIn(FluidTags.WATER) && !player.getWorld().getBlockState(BlockPos.ofFloored(player.getX(), player.getEyeY(), player.getZ())).isOf(Blocks.BUBBLE_COLUMN)) {
                return;
            }

            if (!player.getWorld().isClient) {
                //check if tank is equipped
                PlayerEquipmentData equipment = StateSaverAndLoader.getPlayerState(player);
                ItemStack tankStack = equipment.getStack(PlayerEquipmentData.TANK_SLOT);
                if (!tankStack.isEmpty() && tankStack.contains(ComponentInit.TANK_AIR_COMPONENT)) {
                    //add air if needed
                    int airTime = tankStack.get(ComponentInit.TANK_AIR_COMPONENT);
                    AbstractTankItem tank = (AbstractTankItem) (tankStack.getItem());
                    if (airTime < tank.MAX_AIR_TIME * 20) {
                        tankStack.set(ComponentInit.TANK_AIR_COMPONENT, airTime + 4);
                    }
                }
            }
        }
    }
}


