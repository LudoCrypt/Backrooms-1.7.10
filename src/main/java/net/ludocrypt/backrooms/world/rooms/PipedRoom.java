package net.ludocrypt.backrooms.world.rooms;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.ludocrypt.backrooms.init.BackroomsBlocks;
import net.ludocrypt.backrooms.util.BlockPos;
import net.ludocrypt.backrooms.util.OctavePerlinNoiseSampler;
import net.ludocrypt.backrooms.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;

public class PipedRoom extends WalledRoom {

	private List<Pair<Boolean, OctavePerlinNoiseSampler>> noisemapPipeEast = Lists.newArrayList();
	private List<Pair<Boolean, OctavePerlinNoiseSampler>> noisemapPipeSouth = Lists.newArrayList();

	private List<Pair<Boolean, OctavePerlinNoiseSampler>> noisemapPipeCEast = Lists.newArrayList();
	private List<Pair<Boolean, OctavePerlinNoiseSampler>> noisemapPipeCSouth = Lists.newArrayList();

	public PipedRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop, Block carpet, Block ceilingA, Block ceilingB) {
		super(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop, carpet, ceilingA, ceilingB);
	}

	public PipedRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop, Block carpet) {
		super(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop, carpet);
	}

	public PipedRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop) {
		super(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop);
	}

	public PipedRoom(int id, String name, double xScale, double zScale, Block block) {
		super(id, name, xScale, zScale);
	}

	public PipedRoom(int id, String name, double scale) {
		super(id, name, scale);
	}

	public PipedRoom(int id, String name, double xScale, double zScale) {
		super(id, name, xScale, zScale);
	}

	@Override
	public boolean generate(World world, long seed, Random random, BlockPos pos) {
		super.generate(world, seed, random, pos);

		if (this.noisemapPipeEast.isEmpty() || this.noisemapPipeSouth.isEmpty() || this.noisemapPipeCEast.isEmpty() || this.noisemapPipeCSouth.isEmpty()) {
			this.noisemapPipeEast = setNoisemapEast(seed + 1L);
			this.noisemapPipeSouth = setNoisemapSouth(seed + 2L);

			this.noisemapPipeCEast = setNoisemapDoor(seed + 3L);
			this.noisemapPipeCSouth = setNoisemapIron(seed + 4L);
		}

		boolean east = this.noisemapPipeEast.stream().max(Comparator.comparing((entry) -> {
			return entry.getSecond().sample(pos.getX() * 5 * xScale, 0, pos.getZ() * 5 * zScale);
		})).get().getFirst();
		boolean south = this.noisemapPipeSouth.stream().max(Comparator.comparing((entry) -> {
			return entry.getSecond().sample(pos.getX() * 5 * xScale, 0, pos.getZ() * 5 * zScale);
		})).get().getFirst();

		boolean cEast = this.noisemapPipeCEast.stream().max(Comparator.comparing((entry) -> {
			return entry.getSecond().sample(pos.getX() * 3 * xScale, 0, pos.getZ() * 3 * zScale);
		})).get().getFirst();
		boolean cSouth = this.noisemapPipeCSouth.stream().max(Comparator.comparing((entry) -> {
			return entry.getSecond().sample(pos.getX() * 3 * xScale, 0, pos.getZ() * 3 * zScale);
		})).get().getFirst();

		if (!east && !south) {
			if (random.nextBoolean()) {
				east = true;
			} else {
				south = true;
			}
		}

		if (east) {
			if (getBlock(world, pos.up().south()).equals(Blocks.AIR)) {
				setBlock(world, pos.up(3).south(1), BackroomsBlocks.THICK_PIPE);
			}
			setBlock(world, pos.up(3).east(1).south(1), BackroomsBlocks.THICK_PIPE);
			setBlock(world, pos.up(3).east(2).south(1), BackroomsBlocks.THICK_PIPE);
			setBlock(world, pos.up(3).east(3).south(1), BackroomsBlocks.THICK_PIPE);
		} else if (cEast) {
			setBlock(world, pos.up(3).east(1).south(2), BackroomsBlocks.THICK_PIPE);
			setBlock(world, pos.up(3).east(2).south(2), BackroomsBlocks.THICK_PIPE);
			setBlock(world, pos.up(3).east(3).south(2), BackroomsBlocks.THICK_PIPE);
		}

		if (south) {
			if (getBlock(world, pos.up().east()).equals(Blocks.AIR)) {
				setBlock(world, pos.up(3).east(1), BackroomsBlocks.THICK_PIPE);
			}
			setBlock(world, pos.up(3).south(1).east(1), BackroomsBlocks.THICK_PIPE);
			setBlock(world, pos.up(3).south(2).east(1), BackroomsBlocks.THICK_PIPE);
			setBlock(world, pos.up(3).south(3).east(1), BackroomsBlocks.THICK_PIPE);
		} else if (cSouth) {
			setBlock(world, pos.up(3).south(1).east(2), BackroomsBlocks.THICK_PIPE);
			setBlock(world, pos.up(3).south(2).east(2), BackroomsBlocks.THICK_PIPE);
			setBlock(world, pos.up(3).south(3).east(2), BackroomsBlocks.THICK_PIPE);
		}

		cleanup(world, seed, random, pos);

		return true;
	}

	@Override
	public boolean cleanup(World world, long seed, Random random, BlockPos pos) {
		for (int x = 0; x <= 4; x++) {
			for (int z = 0; z <= 4; z++) {
				BlockPos blockPos = pos.add(x, 3, z);
				if (getBlock(world, blockPos) == BackroomsBlocks.THICK_PIPE) {
					world.method_8512(blockPos.x, blockPos.y, blockPos.z, BackroomsBlocks.THICK_PIPE);
				}
			}
		}
		return false;
	}

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapPipeEast(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, true, true, false, false, true, true), seed);
	}

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapPipeSouth(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, false, true, true, true, false), seed);
	}

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapPipeCEast(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true, false, false, true, false, false, false), seed);
	}

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapPipeCSouth(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, true, false, true, false, false, false), seed);
	}

}
