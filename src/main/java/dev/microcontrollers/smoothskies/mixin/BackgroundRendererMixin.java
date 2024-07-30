package dev.microcontrollers.smoothskies.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.microcontrollers.smoothskies.config.SmoothConfig;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Taken from grondag's Clear Skies mod under the LGPL-3.0 license
 * <a href="https://github.com/grondag/clear-skies/blob/1.19/LICENSE">license</a>
 * Mixins have been updated to use MixinExtras
 */
@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/CubicSampler;sampleColor(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/CubicSampler$RgbFetcher;)Lnet/minecraft/util/math/Vec3d;"))
    private static Vec3d onSampleColor(Vec3d pos, CubicSampler.RgbFetcher rgbFetcher, Operation<Vec3d> original, @Local(argsOnly = true) ClientWorld world, @Local(ordinal = 0) Vec3d vec3d) {
        if (SmoothConfig.CONFIG.instance().clearSkies && world.getDimension().hasSkyLight()) {
            return vec3d;
        } else {
            return original.call(pos, rgbFetcher);
        }
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lorg/joml/Vector3f;dot(Lorg/joml/Vector3fc;)F"))
    private static float afterPlaneDot(Vector3f instance, Vector3fc v, Operation<Float> original) {
        return SmoothConfig.CONFIG.instance().clearSkies ? 0F : original.call(instance, v);
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getRainGradient(F)F"))
    private static float onGetRainLevel(ClientWorld instance, float v, Operation<Float> original) {
        return SmoothConfig.CONFIG.instance().clearSkies ? 0F : original.call(instance, v);
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getThunderGradient(F)F"))
    private static float onGetThunderLevel(ClientWorld instance, float v, Operation<Float> original) {
        return SmoothConfig.CONFIG.instance().clearSkies ? 0F : original.call(instance, v);
    }
}
