package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.ludocrypt.backrooms.world.TheBackroomsDimension;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.Dimension;

@Mixin(Dimension.class)
public class DimensionMixin {

	@Inject(method = "getById", at = @At("HEAD"), cancellable = true)
	private static void BACKROOMS_getDimensionId(int id, CallbackInfoReturnable<Dimension> ci) {
		if (id == 672760) {
			ci.setReturnValue(new TheBackroomsDimension());
		}
	}

	@Inject(method = "getForcedSpawnPoint", at = @At("TAIL"), cancellable = true)
	private void BACKROOMS_getForcedSpawnPoint(CallbackInfoReturnable<BlockPos> ci) {
		if (ci.getReturnValue() == null && (((Dimension) (Object) this) instanceof TheBackroomsDimension)) {
			ci.setReturnValue(new BlockPos(-1, 101, -1));
		}
	}

}
