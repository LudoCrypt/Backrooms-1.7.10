package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.backrooms.access.MusicTypeAccess;
import net.minecraft.class_2391;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@Mixin(class_2391.class)
public class MusicTypeMixin implements MusicTypeAccess {

	@Unique
	private boolean replace = false;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void BACKROOMS_musicReplaceStuffs(String name, int place, Identifier identifier, int j, int k, CallbackInfo ci) {
		if (identifier.equals(new Identifier("minecraft:music.menu")) || identifier.equals(new Identifier("minecraft:music.game.end.credits")) || identifier.equals(new Identifier("minecraft:music.game.end.dragon")) || identifier.equals(new Identifier("minecraft:music.game.end"))) {
			this.makeReplace(true);
		} else {
			this.makeReplace(false);
		}
	}

	@Override
	public boolean shouldReplace() {
		return replace;
	}

	@Override
	public void makeReplace(boolean replace) {
		this.replace = replace;
	}

}
