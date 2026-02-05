package net.kuko.fisch;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.kuko.fisch.client.screen.TheScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class FischClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinding openScreen = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.fisch.open_paper",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        "key.categories.ui"
                ));



        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openScreen.isPressed()) {
                if (client.player != null) {
//                    client.player.displayClientMessage(Component.literal("Key Pressed!"), false);
                    MinecraftClient mc = MinecraftClient.getInstance();
                    mc.setScreen(new TheScreen());
                }
            }
        });
    }
}