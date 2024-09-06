package dev.smoothskies.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.smoothskies.config.ModConfig;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.HeightLimitView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientWorld.Properties.class)
public class ClientWorldMixin {

  @ModifyReturnValue(method = "getSkyDarknessHeight", at = @At("RETURN"))
  private double modifySkyDarknessHeight(double original, HeightLimitView world) {
    if (world != null && ModConfig.CONFIG.instance().lowerSkyVoidDarkness) {
      return world.getBottomY();
    }
    return original;
  }
}
