package net.kuko.fisch.registries;


import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ModBlocksInit implements BlockRegistryContainer {
//    public static final Block EXAMPLE_PEDESTAL = new ExamplePedestal(FabricBlockSettings.create());


    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new Item.Settings());
    }
}
