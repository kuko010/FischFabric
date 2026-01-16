package net.kuko.fisch.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import net.kuko.fisch.config.yacl.ModConfigv1;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Create fresh config each time
            if (FabricLoader.getInstance().isModLoaded("yet-another-config-lib")) {
                return ModConfigv1.MainConfig().generateScreen(parent);
            } else if (FabricLoader.getInstance().isModLoaded("midnightlib")) {
                return parent;
            }

            return parent;
        };
    }
}
