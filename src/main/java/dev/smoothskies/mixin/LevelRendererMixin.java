package dev.smoothskies.mixin;

import dev.smoothskies.config.ConfigHandler;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

  @ModifyArg(method = "method_37365", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/FogRenderer;setupFog(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/FogRenderer$FogMode;FZF)V"), index = 2)
	private static float setFogDistance(float farPlaneDistance) {
		return Math.min(farPlaneDistance, ConfigHandler.distance.get());
	}
}
