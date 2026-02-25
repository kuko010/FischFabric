package net.kuko.fisch.registries;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kuko.fisch.Fisch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

public class ModItems implements ItemRegistryContainer {
    //public static final Item NEW_ITEM = new NewItem(new Item.Settings());


    public static void register() {
        FieldRegistrationHandler.register(ModItems.class, Fisch.MOD_ID, false);
        addTab();
    }

    public static void addTab() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
         //   content.add(NEW_ITEM); // Adds to the end
//            content.addAfter(Items.DIAMOND, MOD_ITEM); // Adds after a specific item
        });
    }
}
