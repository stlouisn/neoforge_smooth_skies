package dev.smoothskies.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public final class ConfigHandler {

  public static final ModConfigSpec configSpec;

  public static ConfigValue<Integer> distance;
  public static ConfigValue<Boolean> lowerSkyVoidDarkness;
  public static ConfigValue<Boolean> clearSkies;

  static {

    ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

    distance = builder
        .comment("Set a custom view distance for the skybox fog. Higher numbers mean bluer skies.\nDefault: 96")
        .defineInRange("distance", 96, 0, 550);

    lowerSkyVoidDarkness = builder
        .comment("Lowers the required height for the sky to turn black to the world's lowest height.\nThis prevents the sky turning black at a high Y value.\nDefault: true")
        .define("lowerSkyVoidDarkness", true);

    clearSkies = builder
        .comment("Removes banding on the horizon, making the sky one uniform color.\nDefault: false")
        .define("clearSkies", false);

    configSpec = builder.build();

  }
}