package dev.smoothskies.config;

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

public class ModConfig {

  public static final ConfigClassHandler<ModConfig> CONFIG = ConfigClassHandler.createBuilder(ModConfig.class)
                                                                               .serializer(config -> GsonConfigSerializerBuilder.create(config)
                                                                                                                                .setPath(FabricLoader.getInstance()
                                                                                                                                                     .getConfigDir()
                                                                                                                                                     .resolve("smoothskies.json"))
                                                                                                                                .build())
                                                                               .build();
  @SerialEntry
  public int distance = 96;
  @SerialEntry
  public boolean lowerSkyVoidDarkness = true, clearSkies = false;

  public static Screen configScreen(Screen parent) {
    return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder)
        -> builder.title(Text.translatable("smooth-skies.smooth-skies"))
                  .category(ConfigCategory.createBuilder()
                                          .name(Text.translatable("smooth-skies.smooth-skies"))
                                          .option(Option.<Integer>createBuilder()
                                                        .name(Text.translatable("smooth-skies.set-fog-view-distance"))
                                                        .description(OptionDescription.of(Text.translatable(
                                                            "smooth-skies.set-fog-view-distance.description")))
                                                        .binding(96, () -> config.distance, newVal -> config.distance = newVal)
                                                        .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                                                                         .range(0, 550)
                                                                                                         .step(1))
                                                        .build())
                                          .option(Option.<Boolean>createBuilder()
                                                        .name(Text.translatable("smooth-skies.lower-sky-void-darkness"))
                                                        .description(OptionDescription.of(Text.translatable(
                                                            "smooth-skies.lower-sky-void-darkness.description")))
                                                        .binding(defaults.lowerSkyVoidDarkness,
                                                                 () -> config.lowerSkyVoidDarkness,
                                                                 newVal -> config.lowerSkyVoidDarkness = newVal)
                                                        .controller(TickBoxControllerBuilder::create)
                                                        .build())
                                          .option(Option.<Boolean>createBuilder()
                                                        .name(Text.translatable("smooth-skies.clear-skies"))
                                                        .description(OptionDescription.of(Text.translatable(
                                                            "smooth-skies.clear-skies.description")))
                                                        .binding(defaults.clearSkies,
                                                                 () -> config.clearSkies,
                                                                 newVal -> config.clearSkies = newVal)
                                                        .controller(TickBoxControllerBuilder::create)
                                                        .build())
                                          .build()))).generateScreen(parent);
  }
}
