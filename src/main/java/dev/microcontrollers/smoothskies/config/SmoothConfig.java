package dev.microcontrollers.smoothskies.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SmoothConfig {
    public static final ConfigClassHandler<SmoothConfig> CONFIG = ConfigClassHandler.createBuilder(SmoothConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("smoothskies.json"))
                    .build())
            .build();

    @SerialEntry public int distance = 96;
    @SerialEntry public boolean lowerSkyVoidDarkness = true;
    @SerialEntry public boolean clearSkies = false;

    @SuppressWarnings("deprecation")
    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.literal("Smooth Skies"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Smooth Skies"))
                        .option(Option.createBuilder(int.class)
                                .name(Text.literal("Set Fog View Distance"))
                                .description(OptionDescription.of(Text.of("Set a custom view distance for the skybox fog. Higher numbers mean bluer skies.")))
                                .binding(96, () -> config.distance, newVal -> config.distance = newVal)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                        .range(0, 550)
                                        .step(1))
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Lower Sky Void Darkness"))
                                .description(OptionDescription.of(Text.of("Lowers the required height for the sky to turn black to the world's lowest height. This prevents the sky turning black at a high Y value. Most noticeable in Skyblock-type worlds.")))
                                .binding(defaults.lowerSkyVoidDarkness, () -> config.lowerSkyVoidDarkness, newVal -> config.lowerSkyVoidDarkness = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Clear Skies"))
                                .description(OptionDescription.of(Text.of("Removes banding on the horizon, making the sky one uniform color. This is taken from grondag's Clear Skies mod.")))
                                .binding(defaults.clearSkies, () -> config.clearSkies, newVal -> config.clearSkies = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build()
                ))).generateScreen(parent);
    }
}
