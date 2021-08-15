package net.ludocrypt.backrooms.world.rooms;

import java.util.Random;

import net.ludocrypt.backrooms.init.BackroomsBlocks;
import net.ludocrypt.backrooms.util.BlockPos;
import net.ludocrypt.backrooms.util.MathHelperUpdated;
import net.ludocrypt.backrooms.world.biome.base.BackroomsBiome;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class EmptyRoom extends RoomLayout {

	public EmptyRoom() {
		super(0, "empty");
	}

	@Override
	public boolean generate(World world, long seed, Random random, BlockPos pos) {
		return generate(world, seed, random, pos, 1.0F, BackroomsBlocks.POLYESTER_WOOL, BackroomsBlocks.MINERAL_TILE_LEFT, BackroomsBlocks.MINERAL_TILE_RIGHT);
	}

	public boolean lerpGenerate(World world, long seed, Random random, BlockPos pos, Block carpet) {
		return lerpGenerate(world, seed, random, pos, carpet, BackroomsBlocks.MINERAL_TILE_LEFT, BackroomsBlocks.MINERAL_TILE_RIGHT);
	}

	public boolean lerpGenerate(World world, long seed, Random random, BlockPos pos, Block carpet, Block ceilingA, Block ceilingB) {
		double c = MathHelperUpdated.reverseLerp(MathHelperUpdated.DISFZZ(pos.x, pos.z), 800, 1200);
		return generate(world, seed, random, pos, c > 0 ? c : 0, carpet, ceilingA, ceilingB);
	}

	public boolean lerpGenerate(World world, long seed, Random random, BlockPos pos) {
		return lerpGenerate(world, seed, random, pos, BackroomsBlocks.POLYESTER_WOOL, BackroomsBlocks.MINERAL_TILE_LEFT, BackroomsBlocks.MINERAL_TILE_RIGHT);

	}

	public boolean generate(World world, long seed, Random random, BlockPos pos, double chance, Block carpet, Block ceilingA, Block ceilingB) {
		for (int x = 0; x < 4; x++) {
			for (int z = 0; z < 4; z++) {
				setBlock(world, new BlockPos(pos.x + x, pos.y + 0, pos.z + z), carpet);

				if ((((x % 4 == 1) && (BackroomsBiome.getRandomOfPos((pos.x + x), (pos.y + 4), (pos.z + z), seed).nextDouble() > chance)) || ((x % 4 == 2) && (BackroomsBiome.getRandomOfPos((pos.x + x - 1), (pos.y + 4), (pos.z + z), seed).nextDouble() > chance))) && (z % 5 == 2)) {
					setBlock(world, new BlockPos(pos.x + x, pos.y + 4, pos.z + z), BackroomsBlocks.FLOURESCENT_LIGHT);
				} else {
					if (x % 2 == 1) {
						setBlock(world, new BlockPos(pos.x + x, pos.y + 4, pos.z + z), ceilingA);
					} else {
						setBlock(world, new BlockPos(pos.x + x, pos.y + 4, pos.z + z), ceilingB);
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean cleanup(World world, long seed, Random random, BlockPos pos) {
		return false;
	}

}
