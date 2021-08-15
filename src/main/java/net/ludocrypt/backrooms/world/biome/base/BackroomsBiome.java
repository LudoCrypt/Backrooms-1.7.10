package net.ludocrypt.backrooms.world.biome.base;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.backrooms.util.BlockPos;
import net.minecraft.class_2322;
import net.minecraft.class_2391;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public abstract class BackroomsBiome extends Biome {

	protected BackroomsBiome(int id) {
		super(id);
	}

	public abstract void generateRoom(World world, long seed, Random random, BlockPos pos);

	public abstract void cleanupRoom(World world, long seed, Random random, BlockPos pos);

	@Environment(EnvType.CLIENT)
	public abstract class_2391 getMusic(class_2322 player);

	@Environment(EnvType.CLIENT)
	public abstract void runAmbientSounds(MinecraftClient client);

	@Override
	public void buildSurface(World world, Random random, int i, int j) {
		// Nothing
	}

	public static Random getRandomOfPos(int x, int y, int z, long seed) {
		return new Random(seed ^ x * z ^ y + 3L);
	}

	public static Random getRandomOfPos(BlockPos pos, long seed) {
		return getRandomOfPos(pos.x, pos.y, pos.z, seed);
	}

}
