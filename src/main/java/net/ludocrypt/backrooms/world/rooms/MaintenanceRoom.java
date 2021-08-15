package net.ludocrypt.backrooms.world.rooms;

import java.util.List;

import com.google.common.collect.Lists;

import net.ludocrypt.backrooms.util.OctavePerlinNoiseSampler;
import net.ludocrypt.backrooms.util.Pair;
import net.minecraft.block.Block;

public class MaintenanceRoom extends WalledRoom {

	public MaintenanceRoom(int id, String name, double xScale, double zScale, Block block) {
		super(id, name, xScale, zScale, block);
	}

	public MaintenanceRoom(int id, String name, double xScale, double zScale, Block blockBottom, Block blockTop, Block blockTopTop, Block carpet) {
		super(id, name, xScale, zScale, blockBottom, blockTop, blockTopTop, carpet);
	}

	@Override
	protected List<Pair<Boolean, OctavePerlinNoiseSampler>> setNoisemapIron(long seed) {
		return OctavePerlinNoiseSampler.createNoise(Lists.newArrayList(true, true, true, false, true, false, true), seed);
	}

}
