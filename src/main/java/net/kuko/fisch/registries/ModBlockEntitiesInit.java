package net.kuko.fisch.registries;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kuko.fisch.block.entity.ExamplePedestalBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntitiesInit implements AutoRegistryContainer<BlockEntityType<?>> {
    public static final BlockEntityType<ExamplePedestalBlockEntity> EXAMPLE_PEDESTAL_BE = FabricBlockEntityTypeBuilder.create(ExamplePedestalBlockEntity::new,
            ModBlocksInit.EXAMPLE_PEDESTAL).build();

    @Override
    public Registry<BlockEntityType<?>> getRegistry() {
        return Registries.BLOCK_ENTITY_TYPE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<BlockEntityType<?>> getTargetFieldType() {
        return (Class<BlockEntityType<?>>) (Object) BlockEntityType.class;
    }
}
