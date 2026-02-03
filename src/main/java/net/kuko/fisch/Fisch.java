package net.kuko.fisch;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;

import net.kuko.fisch.registries.ModBlockEntities;
import net.kuko.fisch.registries.ModBlocks;
import net.kuko.fisch.registries.ModEnergy;
import net.kuko.fisch.registries.ModItems;
import net.minecraft.util.Identifier;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
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

		ModBlocks.register();
		ModBlockEntities.register();
		ModItems.register();
		ModEnergy.register();
	}
}