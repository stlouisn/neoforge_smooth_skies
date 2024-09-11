package dev.smoothskies.config;

import dev.smoothskies.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;

@Config(name = Constants.MOD_ID)
public class ModConfigData implements ConfigData {

  @BoundedDiscrete(min = 0, max = 550)
  @Tooltip
  public int distance = 96;

  @Tooltip
  public boolean lowerSkyVoidDarkness = true;

  @Tooltip
  public boolean clearSkies = false;
  
}
