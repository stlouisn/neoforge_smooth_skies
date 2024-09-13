package dev.smoothskies.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public final class ConfigHandler {

  public static final ModConfigSpec configSpec;

  public static ConfigValue<Boolean> lowerSkyVoidDarkness, clearSkies;
  public static ConfigValue<Integer> distance;

  static {

    ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

    distance = builder
        .defineInRange("distance", 96, 0, 550);

    lowerSkyVoidDarkness = builder
        .define("lowerSkyVoidDarkness", true);

    clearSkies = builder
        .define("clearSkies", false);

    configSpec = builder.build();

  }
}