package net.kuko.fisch.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.kuko.fisch.config.yacl.ModConfigv1;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Create fresh config each time
            // Nope. Gonna use Midnightlib
//            return ModConfigv1.MainConfig().generateScreen(parent);


        };
    }
}
