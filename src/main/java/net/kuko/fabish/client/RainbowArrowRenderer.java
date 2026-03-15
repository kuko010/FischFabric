package net.kuko.fabish.client;

import net.kuko.fabish.entity.AbstractRainbowArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.NonNull;

import static net.minecraft.resources.ResourceLocation.DEFAULT_NAMESPACE;

public class RainbowArrowRenderer extends ArrowRenderer<AbstractRainbowArrow> {
    public RainbowArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NonNull ResourceLocation getTextureLocation(AbstractRainbowArrow entity) {
        return new ResourceLocation(DEFAULT_NAMESPACE, "textures/entity/projectiles/spectral_arrow.png");
    }
}
