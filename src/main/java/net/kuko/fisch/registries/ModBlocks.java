package net.kuko.fisch.registries;


import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.kuko.fisch.Fisch;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModBlocks implements BlockRegistryContainer {
//    public static final Block EXAMPLE_PEDESTAL = new ExamplePedestal(FabricBlockSettings.create());


    @Override
    public BlockItem createBlockItem(Block block, String identifier) {
        return new BlockItem(block, new Item.Properties());
    }

    public static void register() {
        FieldRegistrationHandler.register(ModBlocks.class, Fisch.MOD_ID, false);
    }
}
