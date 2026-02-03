package net.kuko.fisch.registries;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.kuko.fisch.Fisch;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities implements AutoRegistryContainer<BlockEntityType<?>> {
//    public static final BlockEntityType<ExamplePedestalBlockEntity> EXAMPLE_PEDESTAL_BE = FabricBlockEntityTypeBuilder.create(ExamplePedestalBlockEntity::new,
//            ModBlocksInit.EXAMPLE_PEDESTAL).build();

    @Override
    public Registry<BlockEntityType<?>> getRegistry() {
        return Registries.BLOCK_ENTITY_TYPE;
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
