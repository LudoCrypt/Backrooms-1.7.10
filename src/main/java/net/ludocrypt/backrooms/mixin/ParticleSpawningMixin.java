package net.ludocrypt.backrooms.mixin;

import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2346;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.VillageParticle;
import net.minecraft.world.client.ClientWorld;

@Environment(EnvType.CLIENT)
@Mixin(class_2346.class)
public class ParticleSpawningMixin {

	@Shadow
	private ClientWorld field_10550;

	@Shadow
	private MinecraftClient field_10500;

	@Inject(method = "method_9533", at = @At("HEAD"), cancellable = true)
	private void BACKROOMS_changeParticles(String string, double d, double e, double f, double g, double h, double i, CallbackInfoReturnable<Particle> ci) {
		if (this.field_10500 != null && this.field_10500.field_9861 != null && this.field_10500.particleManager != null) {
			if (string.startsWith("coloredSuspended_")) {
				String[] regex = string.split("_", StringUtils.countMatches(string, "_") + 1);
				Particle particle = new VillageParticle(this.field_10550, d, e, f, g, h, i);
				particle.setColor(shiftColor(Integer.parseInt(regex[1])), shiftColor(Integer.parseInt(regex[2])), shiftColor(Integer.parseInt(regex[3])));
				particle.method_9347(regex.length == 5 ? shiftColor(Integer.parseInt(regex[4])) : 1.0F);
				this.field_10500.particleManager.method_3256(particle);
				ci.setReturnValue(particle);
			}
		}
	}

	@Unique
	private float shiftColor(int c) {
		return (float) (((double) c) / 255.0D);
	}
}
