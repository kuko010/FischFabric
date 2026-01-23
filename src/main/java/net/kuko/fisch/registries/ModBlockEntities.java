package net.kuko.fisch.registries;

import net.kuko.fisch.Fisch;

public class ModBlockEntities {
//    public static BlockEntityType<CrystalChargerBlockEntity> CRYSTAL_CHARGER;

    public static void registerBlockEntities() {
//        CRYSTAL_CHARGER = Registry.register(
//                Registries.BLOCK_ENTITY_TYPE,
//                Fisch.id("crystal_charger"),
//                FabricBlockEntityTypeBuilder.create(CrystalChargerBlockEntity::new,
//                        ModBlocks.CRYSTAL_CHARGER).build()
//        );
    }

    public static void register() {
        Fisch.LOGGER.info("Registering Mod Block Entities for " + Fisch.MOD_ID);
        registerBlockEntities();
    }
}
