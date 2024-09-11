package dev.smoothskies;

import dev.smoothskies.config.IdentifierGuiProvider;
import dev.smoothskies.config.ModConfigData;
import dev.smoothskies.config.StringSetGuiProvider;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.util.Identifier;

public final class SmoothSkiesClient {

  public static void init() {
    StringSetGuiProvider<Identifier> guiProvider = new StringSetGuiProvider<>(Identifier.class, Identifier::of);
    AutoConfig.getGuiRegistry(ModConfigData.class).registerPredicateProvider(guiProvider, guiProvider.getPredicate());
    AutoConfig.getGuiRegistry(ModConfigData.class).registerTypeProvider(new IdentifierGuiProvider(), Identifier.class);
  }
}
