package net.ludocrypt.backrooms.world.biome.base;

import net.ludocrypt.backrooms.init.Preinitialize;
import net.minecraft.class_2322;
import net.minecraft.class_2391;
import net.minecraft.client.MinecraftClient;

public abstract class LobbyToMaintenanceBaseBiome extends BaseAmbiencePlayer {

	protected LobbyToMaintenanceBaseBiome(int id) {
		super(id);
	}

	@Override
	public class_2391 getMusic(class_2322 player) {
		return class_2391.valueOf(Preinitialize.THE_LOBBY_MUSIC);
	}

	@Override
	public void runAmbientSounds(MinecraftClient client) {
		super.runSoundTicker(client);
	}

}
