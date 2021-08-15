package net.ludocrypt.backrooms.world.rooms;

import java.util.List;

import com.google.common.collect.Lists;

import net.ludocrypt.backrooms.util.OctavePerlinNoiseSampler;
import net.ludocrypt.backrooms.util.Pair;
import net.minecraft.block.Block;

public class ConcreteHallwaysXRoom extends WalledRoom {

	public ConcreteHallwaysXRoom(int id, String name, double xScale, double zScale, Block block) {
		super(id, name, xScale, zScale, block);
	}

	public ConcreteHallwaysXRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop, Block carpet) {
		super(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop, carpet);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapEast(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, false, true, false, false, true), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapSouth(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true, true, true, false, false, true, false), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapDoor(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, false, false, false, true, true), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapIron(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true), seed);
	}

}
