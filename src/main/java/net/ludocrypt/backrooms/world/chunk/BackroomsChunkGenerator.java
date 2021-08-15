package net.ludocrypt.backrooms.world.chunk;

import java.util.List;

import net.ludocrypt.backrooms.init.BackroomsBlocks;
import net.ludocrypt.backrooms.util.BlockPos;
import net.ludocrypt.backrooms.world.biome.base.BackroomsBiome;
import net.minecraft.class_2631;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ProgressListener;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.BlockStorage;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkProvider;

public class BackroomsChunkGenerator implements ChunkProvider {
	private long seed;
	private World world;

	Block[] blocks = new Block[256];
	byte[] states = new byte[256];

	public BackroomsChunkGenerator(World world, long l) {
		this.world = world;
		this.seed = l;

		for (int b = 0; b < blocks.length; b++) {
			blocks[b] = Blocks.AIR;
			states[b] = 0;
			if (b >= 105 || b <= 99) {
				blocks[b] = BackroomsBlocks.SHALEWALL;
			}
		}
	}

	@Override
	public boolean chunkExists(int chunkX, int chunkZ) {
		return true;
	}

	@Override
	public Chunk getChunk(int i, int j) {

		Chunk chunk = new Chunk(this.world, i, j);

		int inArray;
		for (int chosenBlock = 0; chosenBlock < this.blocks.length; ++chosenBlock) {
			Block block = this.blocks[chosenBlock];
			if (block != null) {
				inArray = chosenBlock >> 4;
				BlockStorage blockStorage = chunk.getBlockStorage()[inArray];
				if (blockStorage == null) {
					blockStorage = new BlockStorage(chosenBlock, !this.world.dimension.field_9615);
					chunk.getBlockStorage()[inArray] = blockStorage;
				}

				for (int chunkX = 0; chunkX < 16; ++chunkX) {
					for (int chunkZ = 0; chunkZ < 16; ++chunkZ) {
						blockStorage.method_8937(chunkX, chosenBlock & 15, chunkZ, block);
						blockStorage.method_8936(chunkX, chosenBlock & 15, chunkZ, this.states[chosenBlock]);
					}
				}
			}
		}

		chunk.method_1368();
		Biome[] biomeArray = this.world.getBiomeSource().method_576((Biome[]) null, i * 16, j * 16, 16, 16);
		byte[] bytes = chunk.method_1404();

		for (inArray = 0; inArray < bytes.length; ++inArray) {
			bytes[inArray] = (byte) biomeArray[inArray].id;
		}

		chunk.method_1368();
		return chunk;

	}

	@Override
	public Chunk method_8913(int i, int j) {
		return this.getChunk(i, j);
	}

	@Override
	public void method_1323(ChunkProvider chunk, int i, int j) {
		int chunkPosX = i * 16;
		int chunkPosZ = j * 16;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				int x = chunkPosX + (a * 4);
				int z = chunkPosZ + (b * 4);
				Biome biomeAt = this.world.getBiomeAt(x, z);
				if (biomeAt instanceof BackroomsBiome && this.world.getBlock(x, 100, z).isEqualTo(Blocks.AIR)) {
					((BackroomsBiome) biomeAt).generateRoom(world, seed, BackroomsBiome.getRandomOfPos(x, 100, z, seed), new BlockPos(x, 100, z));
				}
			}
		}
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				int x = chunkPosX + (a * 4);
				int z = chunkPosZ + (b * 4);
				Biome biomeAt = this.world.getBiomeAt(x, z);
				if (biomeAt instanceof BackroomsBiome && this.world.getBlock(x, 100, z).isEqualTo(Blocks.AIR)) {
					((BackroomsBiome) biomeAt).cleanupRoom(world, seed, BackroomsBiome.getRandomOfPos(x, 100, z, seed), new BlockPos(x, 100, z));
				}
			}
		}
	}

	@Override
	public boolean method_1328(boolean bl, ProgressListener progressListener) {
		return true;
	}

	@Override
	public boolean tickChunks() {
		return false;
	}

	@Override
	public boolean method_1332() {
		return true;
	}

	@Override
	public String getChunkProviderName() {
		return "BackroomsChunkProvider";
	}

	@Override
	public List<?> method_8912(class_2631 arg, int i, int j, int k) {
		Biome biome = this.world.getBiomeAt(i, k);
		return biome.method_8567(arg);
	}

	@Override
	public Vec3i getNearestStructurePos(World world, String structureName, int i, int j, int k) {
		return null;
	}

	@Override
	public int getLoadedChunksCount() {
		return 0;
	}

	@Override
	public void method_1325(int i, int j) {

	}

	@Override
	public void method_1329() {

	}

}
