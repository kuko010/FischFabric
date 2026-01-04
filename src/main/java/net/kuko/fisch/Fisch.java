package net.kuko.fisch;

import net.fabricmc.api.ModInitializer;

import net.kuko.fisch.item.ModItems;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fisch implements ModInitializer {
	public static final String MOD_ID = "fisch";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	// Test



	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItems.register();
	}
}