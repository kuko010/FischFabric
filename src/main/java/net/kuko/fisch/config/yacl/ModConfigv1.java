package net.kuko.fisch.config.yacl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Files;
import java.nio.file.Path;


public class ModConfigv1 {
    public static ConfigData config = new ConfigData();

    static {
        loadConfig();
    }


    public static class ConfigData {
        public static class FreezeConfig {
            @Nullable public String item = "minecraft:stick";
            @Nullable public Boolean enable = Boolean.FALSE;
        }

        public FreezeConfig freeze = new FreezeConfig();
    }

    private static final Path CONFIG_FILE = Path.of("config/fisch.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public static YetAnotherConfigLib MainConfig() {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Fisch Settings"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("config.fisch.fisch_category"))
                        .tooltip(Text.translatable("config.fisch.fisch_category.tooltip"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("config.fisch.freeze.group"))
                                .description(OptionDescription.of(Text.translatable("config.fisch.freeze.group.description")))
                                .option(Options.freeze_item)
                                .option(Options.freeze_enable)
                                .build())
                        .build())

                .save(ModConfigv1::saveConfig)
                .build();
    }



    private static void loadConfig() {
        try {
            if (Files.exists(CONFIG_FILE)) {
                String json = Files.readString(CONFIG_FILE);
                config = GSON.fromJson(json, ConfigData.class);

                // Validate/set defaults if fields are null
                if (config.freeze.item == null) config.freeze.item = "minecraft:air";
                if (config.freeze.enable == null) config.freeze.enable = Boolean.FALSE;
            } else {
                saveConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void saveConfig() {
        try {
            Files.createDirectories(CONFIG_FILE.getParent());
            String json = GSON.toJson(config);
            Files.writeString(CONFIG_FILE, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}