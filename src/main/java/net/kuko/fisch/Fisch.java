package net.kuko.fisch;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fisch implements ModInitializer {
	public static final String MOD_ID = "fisch";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String id) {
		return Identifier.of(MOD_ID, id);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
//		ModItems.register();
//		ModBlocks.register();
//		ModBlockEntities.register();
//		ModEnergy.register(); // Add this line
	}
}