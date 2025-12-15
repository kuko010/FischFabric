package net.kuko.fisch;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

public class MobEvents {
    public static void register() {
        Fisch.LOGGER.info("MobEvents Initialized.");
        ServerTickEvents.END_SERVER_TICK.register(server -> {

            for (var entity : server.getWorld(World.END).iterateEntities()) {
                if (entity instanceof ItemEntity item) {
                    var owner = item.getOwner();

                    if (owner instanceof ServerPlayerEntity) {
                        ServerPlayerEntity player = (ServerPlayerEntity) owner;
                        Fisch.LOGGER.info("Item '{}' was thrown by player: {}", item.getName(), player.getDisplayName().getString());
                        Vec3d playerPos = player.getPos();

                    }
                }
            }
        });
    };
}

