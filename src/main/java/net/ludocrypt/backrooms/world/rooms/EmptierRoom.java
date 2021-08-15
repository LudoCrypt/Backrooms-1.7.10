package net.ludocrypt.backrooms.world.rooms;

import java.util.List;

import com.google.common.collect.Lists;

import net.ludocrypt.backrooms.util.OctavePerlinNoiseSampler;
import net.ludocrypt.backrooms.util.Pair;

public class EmptierRoom extends WalledRoom {

	public EmptierRoom(int id, String name, double xScale, double zScale) {
		super(id, name, xScale, zScale);
	}

	public EmptierRoom(int id, String name, double scale) {
		super(id, name, scale);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapEast(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, false, true, false, true, true), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapSouth(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(false, false, true, false, false, true, false), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapDoor(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true, false, false, false, true, true, false), seed);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapIron(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true, true, false, false, true, false, false), seed);
	}

}
