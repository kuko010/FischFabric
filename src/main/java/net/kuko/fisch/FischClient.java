package net.kuko.fisch;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform4f;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class FischClient implements ClientModInitializer {
    /* To be deleted!!!! */
    private boolean renderingBlit = false;
    // literally the same as minecraft's blit, we are just checking that custom paths work
    private final ManagedShaderEffect testShader = ShaderEffectManager.getInstance().manage(
            new Identifier(Fisch.MOD_ID, "shaders/post/blit.json"));
    private final Uniform4f color = testShader.findUniform4f("ColorModulate");


    @Override
    public void onInitializeClient() {
        KeyBinding open = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + Fisch.MOD_ID + "." + "open_paper",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        "key.categories.ui"));

        /*



        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while  (open.wasPressed()) {
               // client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
                //client.player
//                MinecraftClient.getInstance().setScreen(new OdeToBeansScreen());
                renderingBlit = !renderingBlit;
//                color.set((float) Math.random(), (float) Math.random(), (float) Math.random(), 1.0f);
            }
        });
        ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
            if (renderingBlit) {
                testShader.render(tickDelta);
            }
        });*/
    }
}
