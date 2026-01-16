package net.kuko.fisch.item;

import net.kuko.fisch.Fisch;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {


    public static Item item(String name, Item item) {
        return Registry.register(Registries.ITEM, Fisch.id(name), item);
    }

    public static void register() {
        Fisch.LOGGER.info("Initialized Items for {}.", Fisch.MOD_ID);
    }
}
