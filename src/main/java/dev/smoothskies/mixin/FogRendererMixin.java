package dev.smoothskies.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.smoothskies.config.ConfigHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.util.CubicSampler;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

  @WrapOperation(method = "setupColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/CubicSampler;gaussianSampleVec3(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/util/CubicSampler$Vec3Fetcher;)Lnet/minecraft/world/phys/Vec3;"))
  private static Vec3 setGaussianSample(Vec3 i, CubicSampler.Vec3Fetcher j, Operation<Vec3> original, @Local(argsOnly = true) ClientLevel level, @Local(ordinal = 0) Vec3 vec3) {
    return (ConfigHandler.clearSkies.get() && level.dimensionType().hasSkyLight()) ? vec3 : original.call(i, j);
  }

  @WrapOperation(method = "setupColor", at = @At(value = "INVOKE", target = "Lorg/joml/Vector3f;dot(Lorg/joml/Vector3fc;)F"))
  private static float setVectorDot(Vector3f instance, Vector3fc v, Operation<Float> original) {
    return ConfigHandler.clearSkies.get() ? 0F : original.call(instance, v);
  }

  @WrapOperation(method = "setupColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getRainLevel(F)F"))
  private static float setRainLevel(ClientLevel instance, float v, Operation<Float> original) {
    return ConfigHandler.clearSkies.get() ? 0F : original.call(instance, v);
  }


  @WrapOperation(method = "setupColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getThunderLevel(F)F"))
  private static float setThunderLevel(ClientLevel instance, float v, Operation<Float> original) {
    return ConfigHandler.clearSkies.get() ? 0F : original.call(instance, v);
  }
}
