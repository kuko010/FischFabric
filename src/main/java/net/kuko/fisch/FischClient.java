package net.kuko.fisch;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
//import net.kuko.fisch.client.screen.OdeToBeansScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class FischClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinding open = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + Fisch.MOD_ID + "." + "open_paper",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        "key.categories.ui"));




        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while  (open.wasPressed()) {
               // client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
                //client.player
//                MinecraftClient.getInstance().setScreen(new OdeToBeansScreen());
            }
        });
    }

	/*
   // Because of Fish Entry
    public static final ResourceKey<Registry<FishProperties>> FISH_REGISTRY =
            ResourceKey.createRegistryKey(rl("fish"));

    // Because of Fish Entry
    @SubscribeEvent
    public static void addRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                FISH_REGISTRY,
                FishProperties.CODEC,
                FishProperties.CODEC,
                builder -> builder.maxId(256)
        );
    }
*/
}
