package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.ludocrypt.backrooms.access.MusicTypeAccess;
import net.minecraft.class_2391;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.util.Identifier;

@Mixin(MusicTracker.class)
public class MusicTrackerMixin {

	@Unique
	private class_2391 musicType;

	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;equals(Ljava/lang/Object;)Z", ordinal = 0))
	private boolean BACKROOMS_replaceMusicType(Identifier id, Object in) {
		return !(!id.equals(in) && MusicTypeAccess.get(musicType).shouldReplace());
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;equals(Ljava/lang/Object;)Z", shift = Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
	private void BACKROOMS_replaceMusicType(CallbackInfo ci, class_2391 var1) {
		this.musicType = var1;
	}

}
