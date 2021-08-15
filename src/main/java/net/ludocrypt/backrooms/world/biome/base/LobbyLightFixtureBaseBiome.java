package net.ludocrypt.backrooms.world.biome.base;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.backrooms.init.Preinitialize;
import net.ludocrypt.backrooms.util.MathHelperUpdated;
import net.minecraft.class_2322;
import net.minecraft.class_2391;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;

public abstract class LobbyLightFixtureBaseBiome extends BaseAmbiencePlayer {

	@Environment(EnvType.CLIENT)
	private SoundInstance lasio;

	protected LobbyLightFixtureBaseBiome(int id) {
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
			if (rand.nextDouble() < MathHelperUpdated.reverseLerp(MathHelperUpdated.DISFZZ(client.player.x, client.player.z), 800, 1200) / 4) {
				client.world.playSound(client.player.x + rand.nextInt(48) - 24, client.player.y + rand.nextInt(48) - 24, client.player.z + rand.nextInt(48) - 24, "backrooms:ambient.game.backrooms.lobby.light_pop", MathHelperUpdated.lerpF(rand.nextDouble(), 0.9, 1.0), MathHelperUpdated.lerpF(rand.nextDouble(), 0.85, 1.2), true);
			}
		}
	}

}
