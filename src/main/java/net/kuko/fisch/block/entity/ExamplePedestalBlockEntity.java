package net.kuko.fisch.block.entity;

import net.kuko.fisch.registries.ModBlockEntitiesInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ExamplePedestalBlockEntity extends BlockEntity {
    public ExamplePedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitiesInit.EXAMPLE_PEDESTAL_BE, pos, state);
    }
}
