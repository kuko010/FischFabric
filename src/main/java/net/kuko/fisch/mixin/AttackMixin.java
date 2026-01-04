package net.kuko.fisch.mixin;


import net.kuko.fisch.Fisch;
import net.kuko.fisch.config.ModConfigv1;
import net.kuko.fisch.interfs.LivingEntityFreeze;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerEntity.class})
public class AttackMixin {

    @Inject(
            method = {"attack"},
            at = {@At("HEAD")}
    )
    private void freezeOnHit(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.getWorld().isClient) {
            ItemStack mainHand = player.getMainHandStack();
            if (mainHand.isOf(Items.STICK)) {
                if (target instanceof LivingEntity living) {
                    if (living instanceof LivingEntityFreeze freezeable) {
                        freezeable.toggleFrozen();
                        Fisch.LOGGER.info(ModConfigv1.config.freeze_item);
                    }
                }
            }
        }
    }
}
