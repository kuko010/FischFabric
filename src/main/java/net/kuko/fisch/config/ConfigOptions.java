package net.kuko.fisch.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.kuko.fisch.Fisch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigOptions {
    // These are NOT static so GSON can save them
    public boolean myBooleanOption = true;
}