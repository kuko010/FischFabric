package net.kuko.fisch.client.screen.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class OdeToBeansScreen extends Screen {
    public OdeToBeansScreen() {
        super(Text.literal("Ode To Beans"));
    }

    protected void init() {
        super.init();
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int baseSize = 150; // Base width and height
        int x = (this.width - baseSize) / 2;
        int y = (this.height - baseSize) / 2;


        int glowThickness = 4; // How thick the neon glow is

// Base rectangle
        int neonColor =  0xF04f62bd;
        int baseColor = 0xF0798ffc;
        context.fill(x, y, x + baseSize, y + baseSize, baseColor);

        context.fill(x - glowThickness, y - glowThickness, x + baseSize + glowThickness, y, neonColor); // Top
        context.fill(x - glowThickness, y + baseSize, x + baseSize + glowThickness, y + baseSize + glowThickness, neonColor); // Bottom
        context.fill(x - glowThickness, y, x, y + baseSize, neonColor); // Left
        context.fill(x + baseSize, y, x + baseSize + glowThickness, y + baseSize, neonColor); // Right

        super.render(context, mouseX, mouseY, delta);
    }

    public boolean shouldPause() {
        return false;
    }

    public void close() {
        super.close();
        if (this.client != null) {
            this.client.setScreen((Screen) null);
        }
    }

}

