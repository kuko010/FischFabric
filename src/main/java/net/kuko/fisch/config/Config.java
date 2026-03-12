package net.kuko.fisch.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.kuko.fisch.Fisch;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static net.kuko.fisch.config.ConfigUtils.handler;

public class Config {
    // Create one instance to hold your data
    public static Screen genScreen(Screen screen) {
        return YetAnotherConfigLib.createBuilder()
                .save(() -> ConfigUtils.save(handler))
                .title(Component.literal("Fisch Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("General"))
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Options"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("Boolean Option"))
                                        .binding(
                                                true,
                                                () -> handler.myBooleanOption, // Use handler instance
                                                newVal -> handler.myBooleanOption = newVal
                                        )
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .build().generateScreen(screen);
    }


}