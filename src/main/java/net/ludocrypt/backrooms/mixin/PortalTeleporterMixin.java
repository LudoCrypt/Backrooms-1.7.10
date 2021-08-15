package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.PortalTeleporter;
import net.minecraft.server.world.ServerWorld;

@Mixin(PortalTeleporter.class)
public class PortalTeleporterMixin {

	@Shadow
	@Final
	private ServerWorld world;

	@Inject(method = "method_8561", at = @At("HEAD"), cancellable = true)
	private void BACKROOMS_avoidNetherPortal(Entity entity, double d, double e, double f, float g, CallbackInfo ci) {
		if (this.world.dimension.field_9616 != 1) {
			ci.cancel();
		}
	}

}
