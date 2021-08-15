package net.ludocrypt.backrooms.world.rooms;

import java.util.Random;

import net.ludocrypt.backrooms.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public abstract class RoomLayout {

	private final int id;
	private final String name;

	public RoomLayout(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public abstract boolean generate(World world, long seed, Random random, BlockPos pos);

	public abstract boolean cleanup(World world, long seed, Random random, BlockPos pos);

	protected void setBlock(World world, BlockPos pos, Block block) {
		world.method_8510(pos.x, pos.y, pos.z, block, 0, 2);
	}

	protected void setBlockMeta(World world, BlockPos pos, Block block, int meta) {
		world.method_8510(pos.x, pos.y, pos.z, block, meta, 2);
	}

	protected Block getBlock(World world, BlockPos pos) {
		return world.getBlock(pos.x, pos.y, pos.z);
	}

	protected int getBlockMeta(World world, BlockPos pos) {
		return world.getBlockMeta(pos.x, pos.y, pos.z);
	}

	protected Block getBlockSafely(World world, BlockPos pos) {
		if (world.getChunkProvider().chunkExists(pos.x, pos.y)) {
			return getBlock(world, pos);
		}
		return Blocks.AIR;
	}

	protected int getBlockMetaSafely(World world, BlockPos pos) {
		if (world.getChunkProvider().chunkExists(pos.x, pos.y)) {
			return getBlockMeta(world, pos);
		}
		return 0;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Identifier getNameAsId() {
		return new Identifier(name);
	}

}
