package net.ludocrypt.backrooms.world.biome.base;

import java.util.Random;

import net.ludocrypt.backrooms.init.Preinitialize;
import net.ludocrypt.backrooms.util.MathHelperUpdated;
import net.minecraft.class_2322;
import net.minecraft.class_2391;
import net.minecraft.client.MinecraftClient;

public abstract class ConcreteBaseBiome extends BaseAmbiencePlayer {

	protected ConcreteBaseBiome(int id) {
		super(id);
	}

	@Override
	public class_2391 getMusic(class_2322 player) {
		return class_2391.valueOf(Preinitialize.MAINTENANCE_MUSIC);
	}

	@Override
	public void runAmbientSounds(MinecraftClient client) {
		super.runSoundTicker(client);
		Random rand = client.world.random;
		if (rand.nextDouble() < MathHelperUpdated.reverseLerp(MathHelperUpdated.DISFZZ(client.player.x, client.player.z), 2200, 3500) / 4) {
			client.world.playSound(client.player.x + rand.nextInt(48) - 24, client.player.y + rand.nextInt(48) - 24, client.player.z + rand.nextInt(48) - 24, "backrooms:ambient.game.backrooms.concrete.machinery", MathHelperUpdated.lerpF(rand.nextDouble(), 0.9, 1.75), MathHelperUpdated.lerpF(rand.nextDouble(), 0.65, 1.5), true);
		}
	}

}
