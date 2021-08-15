package net.ludocrypt.backrooms.mixin;

import java.util.HashSet;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.DefaultResourcePack;

@SuppressWarnings("all")
@Environment(EnvType.CLIENT)
@Mixin(DefaultResourcePack.class)
public class DefaultResourcePackMixin {

	@Inject(method = "method_4372", at = @At("RETURN"), cancellable = true)
	private void BACKROOMS_getBackroomsPack(CallbackInfoReturnable<Set> ci) {
		Set set = new HashSet();
		set.addAll(ci.getReturnValue());
		set.add("backrooms");
		ci.setReturnValue(set);
	}

}
