package net.kuko.fisch.registries;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kuko.fisch.Fisch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

public class ModItems implements ItemRegistryContainer {

    public static void register() {
        FieldRegistrationHandler.register(ModItems.class, Fisch.MOD_ID, false);
    }

}
