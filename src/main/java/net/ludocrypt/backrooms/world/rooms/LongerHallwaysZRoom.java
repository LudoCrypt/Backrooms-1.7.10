package net.ludocrypt.backrooms.world.rooms;

import java.util.List;

import com.google.common.collect.Lists;

import net.ludocrypt.backrooms.util.OctavePerlinNoiseSampler;
import net.ludocrypt.backrooms.util.Pair;
import net.minecraft.block.Block;

public class LongerHallwaysZRoom extends WalledRoom {

	public LongerHallwaysZRoom(int id, String name, double xScale, double zScale) {
		super(id, name, xScale, zScale);
	}

	public LongerHallwaysZRoom(int id, String name, double scale) {
		super(id, name, scale);
	}

	public LongerHallwaysZRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop) {
		super(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapEast(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true, true, false, true, true, true, false), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapSouth(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, true, false, false, false, false, true), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapDoor(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true, false, true, true, false, false, false), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapIron(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, false, true, false, true, true), seed);
	}

}
