package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;

@Mixin(MinecraftClient.class)
public interface MinecraftClientAccessor {
	@Accessor
	MusicTracker getMusicTracker();
}
