package net.ludocrypt.backrooms.world.biome.base;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.backrooms.client.BackroomsAmbienceSoundInstance;
import net.ludocrypt.backrooms.mixin.MinecraftClientAccessor;
import net.ludocrypt.backrooms.mixin.MusicTrackerAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;

public abstract class BaseAmbiencePlayer extends BackroomsBiome {

	protected BaseAmbiencePlayer(int id) {
		super(id);
	}

	@Environment(EnvType.CLIENT)
	public BackroomsAmbienceSoundInstance lasi;

	@Environment(EnvType.CLIENT)
	public void runSoundTicker(MinecraftClient client) {
		if (lasi == null) {
			lasi = new BackroomsAmbienceSoundInstance(client);
		}
		if (!client.getSoundManager().isPlaying(lasi) && (((MusicTrackerAccessor) ((MinecraftClientAccessor) client).getMusicTracker()).getField_10765() == null)) {
			tryPlayAllah(client, lasi);
		}
	}

	@Environment(EnvType.CLIENT)
	public void tryPlayAllah(MinecraftClient client, SoundInstance lasi) {
		try {
			client.getSoundManager().play(lasi);
		} catch (IllegalArgumentException e) {
			// Nothing
		}
	}
}
