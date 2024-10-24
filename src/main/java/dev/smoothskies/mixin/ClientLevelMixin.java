package dev.smoothskies.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.smoothskies.config.ConfigHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientLevel.ClientLevelData.class)
public class ClientLevelMixin {

  @ModifyReturnValue(method = "getHorizonHeight", at = @At("RETURN"))
  private double modifyHorizonHeight(double original, LevelHeightAccessor level) {
    if (level != null && ConfigHandler.lowerSkyVoidDarkness.get()) {
      return level.getMinBuildHeight();
    }
    return original;
  }
}
