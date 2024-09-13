package dev.smoothskies.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.smoothskies.config.ConfigHandler;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

  @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/CubicSampler;sampleColor(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/CubicSampler$RgbFetcher;)Lnet/minecraft/util/math/Vec3d;"))
  private static Vec3d onSampleColor(Vec3d pos, CubicSampler.RgbFetcher rgbFetcher, Operation<Vec3d> original, @Local(argsOnly = true) ClientWorld world, @Local(ordinal = 0) Vec3d vec3d) {
    return (ConfigHandler.clearSkies.get() && world.getDimension().hasSkyLight()) ? vec3d : original.call(pos, rgbFetcher);
  }

  @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lorg/joml/Vector3f;dot(Lorg/joml/Vector3fc;)F"))
  private static float afterVectorDot(Vector3f instance, Vector3fc v, Operation<Float> original) {
    return ConfigHandler.clearSkies.get() ? 0F : original.call(instance, v);
  }

  @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getRainGradient(F)F"))
  private static float onGetRainGradient(ClientWorld instance, float v, Operation<Float> original) {
    return ConfigHandler.clearSkies.get() ? 0F : original.call(instance, v);
  }

  @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getThunderGradient(F)F"))
  private static float onGetThunderGradient(ClientWorld instance, float v, Operation<Float> original) {
    return ConfigHandler.clearSkies.get() ? 0F : original.call(instance, v);
  }
}
