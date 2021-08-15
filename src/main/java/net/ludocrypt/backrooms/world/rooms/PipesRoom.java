package net.ludocrypt.backrooms.world.rooms;

import java.util.List;

import com.google.common.collect.Lists;

import net.ludocrypt.backrooms.util.OctavePerlinNoiseSampler;
import net.ludocrypt.backrooms.util.Pair;
import net.minecraft.block.Block;

public class PipesRoom extends PipedRoom {

	public PipesRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop, Block carpet, Block ceilingA, Block ceilingB) {
		super(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop, carpet, ceilingA, ceilingB);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapDoor(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false), seed);
	}

}
