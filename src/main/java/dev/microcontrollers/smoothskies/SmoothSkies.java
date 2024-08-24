package dev.microcontrollers.smoothskies;

import dev.microcontrollers.smoothskies.config.SmoothConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmoothSkies implements ModInitializer {
    final Logger logger = LoggerFactory.getLogger("Smooth Skies");

    @Override
    public void onInitialize() {
        SmoothConfig.CONFIG.load();
        if (FabricLoader.getInstance().isModLoaded("clearvoid")) logger.warn("Clear Void mod has been disabled as Smooth Skies replaces it.");
        if (FabricLoader.getInstance().isModLoaded("clear-skies")) logger.warn("Clear Skies mod has been disabled as Smooth Skies replaces it.");
    }
}
