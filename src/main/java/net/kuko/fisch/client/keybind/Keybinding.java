package net.kuko.fisch.client.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.kuko.fisch.Fisch;
import net.kuko.fisch.client.screen.OdeToBeansScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinding {
    public static final KeyBinding open = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key." + Fisch.MOD_ID + "." + "open_paper",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_B,
                    "key.categories.ui"));


    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if  (open.wasPressed()) {
                // client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
                //client.player
                client.setScreen(new OdeToBeansScreen());
            }
        });
    }
}
