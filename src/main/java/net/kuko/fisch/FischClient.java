package net.kuko.fisch;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform1f;
import ladysnake.satin.api.managed.uniform.Uniform3f;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class FischClient implements ClientModInitializer {
    private boolean enableShader = false;

    private final ManagedShaderEffect outlineShader = ShaderEffectManager.getInstance()
            .manage(new Identifier(Fisch.MOD_ID, "shaders/post/outline.json"));

    // Access to shader uniforms for customization
    private final Uniform1f edgeThickness = outlineShader.findUniform1f("EdgeThickness");
    private final Uniform1f edgeStrength = outlineShader.findUniform1f("EdgeStrength");
    private final Uniform3f outlineColor = outlineShader.findUniform3f("OutlineColor");

    @Override
    public void onInitializeClient() {
        KeyBinding open = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + Fisch.MOD_ID + ".open_paper",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_B,
                        "key.categories.ui"));

        // Set initial values
        edgeThickness.set(10.0f);
        edgeStrength.set(500.0f);
        outlineColor.set(0.0f, 0.0f, 0.0f); // Black outlines

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (open.wasPressed()) {
                enableShader = !enableShader;
            }
        });

        ShaderEffectRenderCallback.EVENT.register(tickDelta -> {
            if (enableShader) {
                outlineShader.render(tickDelta);
            }
        });
    }
}