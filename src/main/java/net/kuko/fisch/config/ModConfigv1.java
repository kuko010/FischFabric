package net.kuko.fisch.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import net.minecraft.text.Text;

import java.nio.file.Files;
import java.nio.file.Path;


public class ModConfigv1 {
    public static ConfigData config = new ConfigData();
    public static class ConfigData {
        public String freeze_item = "minecraft:stick";
    }

    private static final Path CONFIG_FILE = Path.of("config/fisch.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public static Option<String> item = Option.<String>createBuilder()
            .name(Text.translatable("config.fisch.freeze_item"))
            .description(OptionDescription.of(Text.translatable("config.fisch.freeze_item.description")))
            .binding(
                    "minecraft:stick",
                    () -> config.freeze_item,
                    newVal -> config.freeze_item = newVal)
            .controller(StringControllerBuilder::create)
            .flag(OptionFlag.GAME_RESTART)
            .build();

    public static YetAnotherConfigLib MainConfig() {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Fisch Settings"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("config.fisch.fisch_category"))
                        .tooltip(Text.translatable("config.fisch.fisch_category.tooltip"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("config.fisch.freeze.group"))
                                .description(OptionDescription.of(Text.translatable("config.fisch.freeze.group.description")))
                                .option(item)
                                .build())
                        .build())
                .save(ModConfigv1::saveConfig)
                .build();
    }


    static {
        loadConfig();
    }

    private static void loadConfig() {
        try {
            if (Files.exists(CONFIG_FILE)) {
                String json = Files.readString(CONFIG_FILE);
                config = GSON.fromJson(json, ConfigData.class);

                // Validate/set defaults if fields are null
                if (config.freeze_item == null) config.freeze_item = "minecraft:stick";
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