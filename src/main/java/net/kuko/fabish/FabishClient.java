package net.kuko.fabish;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.kuko.fabish.client.RainbowArrowRenderer;
import net.kuko.fabish.registry.ModEntityTypes;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import org.lwjgl.glfw.GLFW;

public class FabishClient implements ClientModInitializer {





    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(
                ModEntityTypes.RAINBOW_ARROW,
                RainbowArrowRenderer::new
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

        });
        if (FabricLoader.getInstance().isModLoaded("computercraft")) {
            net.kuko.fabish.compat.computercraft.UpgradeRegistry.clientRegister();
        }
    }
}