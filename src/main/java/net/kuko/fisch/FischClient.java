package net.kuko.fisch;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class FischClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinding open = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + Fisch.MOD_ID + ".open_paper",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        "key.categories.ui"));

    }
}