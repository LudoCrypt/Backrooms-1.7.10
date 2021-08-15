package net.ludocrypt.backrooms.world.biome;

import java.util.Random;

import net.ludocrypt.backrooms.init.BackroomsRooms;
import net.ludocrypt.backrooms.util.BlockPos;
import net.ludocrypt.backrooms.world.biome.base.ConcreteBaseBiome;
import net.minecraft.world.World;

public class ConcreteLongX extends ConcreteBaseBiome {

	public ConcreteLongX(int i) {
		super(i);
		this.monsterEntries.clear();
		this.passiveEntries.clear();
		this.waterEntries.clear();
		this.flyingEntries.clear();
		this.name = "Maintenance";
		this.field_404 = false;
		this.temperature = 2.0F;
		this.downfall = 0.0F;
		this.setSeedModifier(6727654);
	}

	@Override
	public void generateRoom(World world, long seed, Random random, BlockPos pos) {
		BackroomsRooms.CONCRETE_LONG_X.generate(world, seed, random, pos);
	}

	@Override
	public void cleanupRoom(World world, long seed, Random random, BlockPos pos) {
		BackroomsRooms.CONCRETE_LONG_X.cleanup(world, seed, random, pos);
	}

}
