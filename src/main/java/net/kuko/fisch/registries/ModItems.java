package net.kuko.fisch.registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kuko.fisch.Fisch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {


    private static void addItemsToIngredientTab(FabricItemGroupEntries entries) {

    }

    private static void addItemsToToolsTab(FabricItemGroupEntries entries) {

    }

    public static Item item(String name, Item item) {
        return Registry.register(Registries.ITEM, Fisch.id(name), item);
    }

    public static void register() {
        Fisch.LOGGER.info("Initialized Items for {}.", Fisch.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsTab);
    }
}
