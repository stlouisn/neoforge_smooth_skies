package dev.smoothskies.config;

import dev.smoothskies.Constants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = Constants.MOD_ID)
public class ModConfig implements ConfigData {

  @BoundedDiscrete(min = 0, max = 550)
  @Tooltip
  public int distance = 96;

  @Tooltip
  public boolean lowerSkyVoidDarkness = true;

  @Tooltip
  public boolean clearSkies = false;

  public static void init() {
    AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
  }

  public static ModConfig getInstance() {
    return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
  }
}