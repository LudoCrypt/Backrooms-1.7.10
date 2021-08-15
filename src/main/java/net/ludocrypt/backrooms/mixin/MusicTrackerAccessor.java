package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;

@Mixin(MusicTracker.class)
public interface MusicTrackerAccessor {
	@Accessor
	SoundInstance getField_10765();
}
