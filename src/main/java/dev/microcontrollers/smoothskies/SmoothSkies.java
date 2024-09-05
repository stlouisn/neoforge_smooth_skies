package dev.microcontrollers.smoothskies;

import dev.microcontrollers.smoothskies.config.Config;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmoothSkies implements ModInitializer {

    public static final String MOD_ID = "smoothskies";

    @SuppressWarnings("unused")
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Config.CONFIG.load();
    }
}