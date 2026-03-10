package net.kuko.fisch.registries;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.kuko.fisch.Fisch;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities implements AutoRegistryContainer<BlockEntityType<?>> {
//    public static final BlockEntityType<ExamplePedestalBlockEntity> EXAMPLE_PEDESTAL_BE = FabricBlockEntityTypeBuilder.create(ExamplePedestalBlockEntity::new,
//            ModBlocksInit.EXAMPLE_PEDESTAL).build();

    @Override
    public Registry<BlockEntityType<?>> getRegistry() {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<BlockEntityType<?>> getTargetFieldType() {
        return (Class<BlockEntityType<?>>) (Object) BlockEntityType.class;
    }

    public static void register() {
        FieldRegistrationHandler.register(ModBlockEntities.class, Fisch.MOD_ID, false);
    }
}
