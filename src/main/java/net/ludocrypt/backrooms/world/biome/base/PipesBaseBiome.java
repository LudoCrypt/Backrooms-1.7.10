package net.ludocrypt.backrooms.world.biome.base;

import java.util.Random;

import net.ludocrypt.backrooms.init.Preinitialize;
import net.ludocrypt.backrooms.util.MathHelperUpdated;
import net.minecraft.class_2322;
import net.minecraft.class_2391;
import net.minecraft.client.MinecraftClient;

public abstract class PipesBaseBiome extends BaseAmbiencePlayer {

	protected PipesBaseBiome(int id) {
		super(id);
	}

	@Override
	public class_2391 getMusic(class_2322 player) {
		return class_2391.valueOf(Preinitialize.PIPES_MUSIC);
	}

	@Override
	public void runAmbientSounds(MinecraftClient client) {
		super.runSoundTicker(client);
		Random rand = client.world.random;
		if (rand.nextDouble() < 0.1) {
			if (rand.nextDouble() < 0.4) {
				client.world.playSound(client.player.x + rand.nextInt(64) - 32, client.player.y + rand.nextInt(64) - 32, client.player.z + rand.nextInt(64) - 32, "backrooms:ambient.game.backrooms.concrete.machinery", MathHelperUpdated.lerpF(rand.nextDouble(), 0.7, 1.6), MathHelperUpdated.lerpF(rand.nextDouble(), 0.5, 2.0), true);
			} else if (rand.nextDouble() < 0.3) {
				client.world.playSound(client.player.x + rand.nextInt(64) - 32, client.player.y + rand.nextInt(64) - 32, client.player.z + rand.nextInt(64) - 32, "ambient.cave.cave", MathHelperUpdated.lerpF(rand.nextDouble(), 0.7, 1.6), MathHelperUpdated.lerpF(rand.nextDouble(), 0.5, 2.0), true);
			}
		}
	}

}
