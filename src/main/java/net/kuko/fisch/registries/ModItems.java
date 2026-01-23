package net.kuko.fisch.registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kuko.fisch.Fisch;
import net.kuko.fisch.item.FluxControl;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {

    public static final Item FluxControl = item("flux_control", new FluxControl(
            new Item.Settings().maxCount(1)));

    private static void addItemsToIngredientTab(FabricItemGroupEntries entries) {

    }

    private static void addItemsToToolsTab(FabricItemGroupEntries entries) {
        entries.add(FluxControl);
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
