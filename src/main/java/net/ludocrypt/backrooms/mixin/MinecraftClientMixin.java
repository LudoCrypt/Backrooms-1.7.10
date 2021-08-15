package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.backrooms.world.TheBackroomsDimension;
import net.ludocrypt.backrooms.world.biome.base.BackroomsBiome;
import net.minecraft.class_2322;
import net.minecraft.class_2391;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.world.biome.Biome;

@Mixin(MinecraftClient.class)
@Environment(EnvType.CLIENT)
public class MinecraftClientMixin {

	@Shadow
	public class_2322 player;

	@Shadow
	private SoundManager soundManager;

	@Inject(method = "getMusicType", at = @At("HEAD"), cancellable = true)
	private void BACKROOMS_getMusicType(CallbackInfoReturnable<class_2391> ci) {
		if (this.player != null && this.player.world.dimension instanceof TheBackroomsDimension) {
			Biome biome = this.player.world.dimension.world.getBiomeAt(this.player.getBlockPos().x, this.player.getBlockPos().z);
			if (biome instanceof BackroomsBiome) {
				ci.setReturnValue(((BackroomsBiome) biome).getMusic(player));
			}
		}
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/SoundManager;tick()V", ordinal = 0))
	private void BACKROOMS_tickAmbientSounds(CallbackInfo ci) {
		if (this.player != null && this.player.world.dimension instanceof TheBackroomsDimension) {
			Biome biome = this.player.world.dimension.world.getBiomeAt(this.player.getBlockPos().x, this.player.getBlockPos().z);
			if (biome instanceof BackroomsBiome) {
				((BackroomsBiome) biome).runAmbientSounds((MinecraftClient) (Object) this);
			}
		}
	}

}
