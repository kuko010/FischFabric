package net.kuko.fisch.mixin;


import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.kuko.fisch.config.yacl.ModConfigv1;
import net.kuko.fisch.interfs.LivingEntityFreeze;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.kuko.fisch.config.yacl.ModConfigv1.config;

@Mixin({PlayerEntity.class})
public class AttackMixin {
    static Boolean flag;
    static String item;

    static {
        if (FabricLoader.getInstance().isModLoaded("yet-another-config-lib")) {
            flag = config.freeze.enable;
            item = config.freeze.item;
        } else if (FabricLoader.getInstance().isModLoaded("midnightlib")) {
            //TODO: Actually make Midnight lib
            flag = config.freeze.enable;
            item = config.freeze.item;
        }
    }



    @Inject(
            method = {"attack"},
            at = {@At("HEAD")}
    )
    private void freezeOnHit(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.getWorld().isClient) {
            ItemStack mainHand = player.getMainHandStack();
            ModConfigv1.ConfigData config = ModConfigv1.config;
            Identifier configItem = new Identifier(item); // Debugger said: "blorb:error"

            Item freezeItem = Registries.ITEM.get(configItem);// Debugger said: air

            // safe fail
            Item item = (freezeItem.equals(Items.AIR)) ? Items.BEDROCK : freezeItem;
            // Why bedrock? Because, surely the players won't get bedrock.

            if (mainHand.getItem().equals(item) && flag) {
                {
                    if (target instanceof LivingEntity living) {
                        if (living instanceof LivingEntityFreeze freezeable) {
                            freezeable.toggleFrozen();
                        }
                    }
                }
            }
        }
    }
}
