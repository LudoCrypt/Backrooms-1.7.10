package net.ludocrypt.backrooms.world.rooms;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.ludocrypt.backrooms.init.BackroomsBlocks;
import net.ludocrypt.backrooms.init.BackroomsRooms;
import net.ludocrypt.backrooms.util.BlockPos;
import net.ludocrypt.backrooms.util.OctavePerlinNoiseSampler;
import net.ludocrypt.backrooms.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;

public class WalledRoom extends RoomLayout {

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> noisemapEast = Lists.newArrayList();
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> noisemapSouth = Lists.newArrayList();

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> noisemapDoors = Lists.newArrayList();
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> noisemapIronDoors = Lists.newArrayList();

	protected final double xScale;
	protected final double zScale;

	protected final Block blockBottom;
	protected final Block blockTop;
	protected final Block blockTopTop;
	protected final Block carpet;
	protected final Block ceilingA;
	protected final Block ceilingB;

	public WalledRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop, Block carpet, Block ceilingA, Block ceilingB) {
		super(id, name);
		this.xScale = xScale;
		this.zScale = zScale;
		this.blockBottom = blockBottom;
		this.blockTop = blockTop;
		this.blockTopTop = blockTopTop;
		this.carpet = carpet;
		this.ceilingA = ceilingA;
		this.ceilingB = ceilingB;
	}

	public WalledRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop, Block carpet) {
		this(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop, carpet, BackroomsBlocks.MINERAL_TILE_LEFT, BackroomsBlocks.MINERAL_TILE_RIGHT);
	}

	public WalledRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop) {
		this(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop, BackroomsBlocks.POLYESTER_WOOL, BackroomsBlocks.MINERAL_TILE_LEFT, BackroomsBlocks.MINERAL_TILE_RIGHT);
	}

	public WalledRoom(int id, String name, double xScale, double zScale, Block block) {
		this(id, name, xScale, zScale, block, block, block);
	}

	public WalledRoom(int id, String name, double scale) {
		this(id, name, scale, scale);
	}

	public WalledRoom(int id, String name, double xScale, double zScale) {
		this(id, name, xScale, zScale, BackroomsBlocks.PLASTERWALL_BOTTOM, BackroomsBlocks.PLASTERWALL_TOP, BackroomsBlocks.PLASTERWALL_BOTTOM);
	}

	public WalledRoom() {
		this(1, "walled", 1.0D);
	}

	@Override
	public boolean generate(World world, long seed, Random random, BlockPos pos) {
		BackroomsRooms.EMPTY.lerpGenerate(world, seed, random, pos, carpet, ceilingA, ceilingB);

		if (this.noisemapEast.isEmpty() || this.noisemapSouth.isEmpty() || this.noisemapDoors.isEmpty() || this.noisemapIronDoors.isEmpty()) {
			this.noisemapEast = setNoisemapEast(seed);
			this.noisemapSouth = setNoisemapSouth(seed);

			this.noisemapDoors = setNoisemapDoor(seed);
			this.noisemapIronDoors = setNoisemapIron(seed);
		}

		boolean east = this.noisemapEast.stream().max(Comparator.comparing((entry) -> {
			return entry.getSecond().sample(pos.getX() * 5 * xScale, 0, pos.getZ() * 5 * zScale);
		})).get().getFirst();
		boolean south = this.noisemapSouth.stream().max(Comparator.comparing((entry) -> {
			return entry.getSecond().sample(pos.getX() * 5 * xScale, 0, pos.getZ() * 5 * zScale);
		})).get().getFirst();

		boolean placedDoor = false;

		boolean door = this.noisemapDoors.stream().max(Comparator.comparing((entry) -> {
			return entry.getSecond().sample(pos.getX() * 3 * xScale, 0, pos.getZ() * 3 * zScale);
		})).get().getFirst();
		boolean iron = this.noisemapIronDoors.stream().max(Comparator.comparing((entry) -> {
			return entry.getSecond().sample(pos.getX() * 3 * xScale, 0, pos.getZ() * 3 * zScale);
		})).get().getFirst();

		if (south) {
			setBlock(world, pos.up(1).south(1), blockBottom);
			if (door && !placedDoor && random.nextBoolean()) {
				placedDoor = false;
				setBlockMeta(world, pos.up(1).south(2), iron ? Blocks.IRON_DOOR : Blocks.WOODEN_DOOR, 0);
				setBlockMeta(world, pos.up(2).south(2), iron ? Blocks.IRON_DOOR : Blocks.WOODEN_DOOR, 8);
			} else {
				setBlock(world, pos.up(1).south(2), blockBottom);
				setBlock(world, pos.up(2).south(2), blockTop);
			}
			setBlock(world, pos.up(1).south(3), blockBottom);
			setBlock(world, pos.up(2).south(1), blockTop);
			setBlock(world, pos.up(2).south(3), blockTop);
			setBlock(world, pos.up(3).south(1), blockTopTop);
			setBlock(world, pos.up(3).south(2), blockTopTop);
			setBlock(world, pos.up(3).south(3), blockTopTop);
		}
		if (east) {
			setBlock(world, pos.up(1).east(1), blockBottom);
			if (door && !placedDoor) {
				setBlockMeta(world, pos.up(1).east(2), iron ? Blocks.IRON_DOOR : Blocks.WOODEN_DOOR, 1);
				setBlockMeta(world, pos.up(2).east(2), iron ? Blocks.IRON_DOOR : Blocks.WOODEN_DOOR, 9);
			} else {
				setBlock(world, pos.up(1).east(2), blockBottom);
				setBlock(world, pos.up(2).east(2), blockTop);
			}
			setBlock(world, pos.up(1).east(3), blockBottom);
			setBlock(world, pos.up(2).east(1), blockTop);
			setBlock(world, pos.up(2).east(3), blockTop);
			setBlock(world, pos.up(3).east(1), blockTopTop);
			setBlock(world, pos.up(3).east(2), blockTopTop);
			setBlock(world, pos.up(3).east(3), blockTopTop);
		}

		if (!getBlock(world, pos.up().north()).equals(Blocks.AIR) || !getBlock(world, pos.up().east()).equals(Blocks.AIR) || !getBlock(world, pos.up().south()).equals(Blocks.AIR) || !getBlock(world, pos.up().west()).equals(Blocks.AIR)) {
			setBlock(world, pos.up(1), blockBottom);
			setBlock(world, pos.up(2), blockTop);
			setBlock(world, pos.up(3), blockTopTop);
		}

		return true;
	}

	@Override
	public boolean cleanup(World world, long seed, Random random, BlockPos pos) {
		return false;
	}

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapEast(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true, false, false, true, false, false, true), seed);
	}

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapSouth(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, true, true, false, true, false), seed);
	}

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapDoor(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, false, true, true, false, true), seed);
	}

	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapIron(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, false, false, true, true, false), seed);
	}

}
