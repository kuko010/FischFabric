package net.kuko.fisch.fishentry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;


// Used wdiscute's tutorial to make this examplee
public record FishProperties(
        ResourceLocation fish,
        int baseChance,
        boolean bool,
        List<ResourceLocation> baits
) {
    public static final Codec<FishProperties> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ResourceLocation.CODEC.fieldOf("fish").forGetter(FishProperties::fish),
                    Codec.INT.fieldOf("base_chance").forGetter(FishProperties::baseChance),
                    Codec.BOOL.optionalFieldOf("bool", true).forGetter(FishProperties::bool),
                    Codec.list(ResourceLocation.CODEC).fieldOf("baits").forGetter(FishProperties::baits)

            ).apply(instance, FishProperties::new)
    );
}
