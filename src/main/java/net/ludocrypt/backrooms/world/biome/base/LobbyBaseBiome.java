package net.ludocrypt.backrooms.world.biome.base;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.backrooms.client.BackroomsAmbienceSoundInstance;
import net.ludocrypt.backrooms.init.Preinitialize;
import net.ludocrypt.backrooms.util.MathHelperUpdated;
import net.minecraft.class_2322;
import net.minecraft.class_2391;
import net.minecraft.client.MinecraftClient;

public abstract class LobbyBaseBiome extends BaseAmbiencePlayer {

	@Environment(EnvType.CLIENT)
	private BackroomsAmbienceSoundInstance lasi;

	protected LobbyBaseBiome(int id) {
		super(id);
	}

	@Override
	public class_2391 getMusic(class_2322 player) {
		return class_2391.valueOf(Preinitialize.THE_LOBBY_MUSIC);
	}

	@Override
	public void runAmbientSounds(MinecraftClient client) {
		super.runSoundTicker(client);
		Random rand = client.world.random;
		if (rand.nextDouble() < 0.3) {
			if (rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean() && rand.nextDouble() < 0.01 && rand.nextDouble() < 0.01) {
				client.world.playSound(client.player.x + rand.nextInt(8), client.player.y + rand.nextInt(8), client.player.z + rand.nextInt(8), "backrooms:ambient.game.backrooms.lobby.rainstorm", MathHelperUpdated.lerpF(rand.nextDouble(), 1.0, 2.0), 1.0F, true);
			}
		}
	}

}
