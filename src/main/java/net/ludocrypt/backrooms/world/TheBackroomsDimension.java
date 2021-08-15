package net.ludocrypt.backrooms.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.backrooms.init.BackroomsBiomes;
import net.ludocrypt.backrooms.util.BlockPos;
import net.ludocrypt.backrooms.util.Pair;
import net.ludocrypt.backrooms.world.biome.BackroomsBiomeSource;
import net.ludocrypt.backrooms.world.biome.MultiNoiseBiomeSource;
import net.ludocrypt.backrooms.world.biome.MultiNoiseBiomeSource.MixedNoisePoint;
import net.ludocrypt.backrooms.world.chunk.BackroomsChunkGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.dimension.Dimension;

public class TheBackroomsDimension extends Dimension {

	public static final Map<Biome, MultiNoiseBiomeSource.MixedNoisePoint> LOBBY_BIOME_POINTS = new HashMap<>();
	public static final Map<Biome, MultiNoiseBiomeSource.MixedNoisePoint> MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_POINTS = new HashMap<>();
	public static final Map<Biome, MultiNoiseBiomeSource.MixedNoisePoint> MAINTENANCE_TRANSITION_LOBBY_POINTS = new HashMap<>();
	public static final Map<Biome, MultiNoiseBiomeSource.MixedNoisePoint> MAINTENANCE_POINTS = new HashMap<>();
	public static final Map<Biome, MultiNoiseBiomeSource.MixedNoisePoint> MAINTENANCE_TRANSITION_CONCRETE_POINTS = new HashMap<>();
	public static final Map<Biome, MultiNoiseBiomeSource.MixedNoisePoint> CONCRETE_POINTS = new HashMap<>();
	public static final Map<Biome, MultiNoiseBiomeSource.MixedNoisePoint> CONCRETE_TO_PIPES_POINTS = new HashMap<>();
	public static final Map<Biome, MultiNoiseBiomeSource.MixedNoisePoint> PIPES_POINTS = new HashMap<>();

	public static final MultiNoiseBiomeSource.Preset LOBBY_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier("backrooms:lobby_biome_source"), (preset, seed) -> {
		List<Pair<MultiNoiseBiomeSource.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		LOBBY_BIOME_POINTS.forEach((biomeKey, noisePoint) -> biomes.add(Pair.of(noisePoint, () -> biomeKey)));
		return new MultiNoiseBiomeSource(seed, biomes);
	});

	public static final MultiNoiseBiomeSource.Preset MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier("backrooms:maintenance_transition_lobby_transition_lobby_biome_source"), (preset, seed) -> {
		List<Pair<MultiNoiseBiomeSource.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_POINTS.forEach((biomeKey, noisePoint) -> biomes.add(Pair.of(noisePoint, () -> biomeKey)));
		return new MultiNoiseBiomeSource(seed, biomes);
	});

	public static final MultiNoiseBiomeSource.Preset MAINTENANCE_TRANSITION_LOBBY_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier("backrooms:maintenance_transition_lobby_biome_source"), (preset, seed) -> {
		List<Pair<MultiNoiseBiomeSource.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		MAINTENANCE_TRANSITION_LOBBY_POINTS.forEach((biomeKey, noisePoint) -> biomes.add(Pair.of(noisePoint, () -> biomeKey)));
		return new MultiNoiseBiomeSource(seed, biomes);
	});

	public static final MultiNoiseBiomeSource.Preset MAINTENANCE_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier("backrooms:maintenance_biome_source"), (preset, seed) -> {
		List<Pair<MultiNoiseBiomeSource.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		MAINTENANCE_POINTS.forEach((biomeKey, noisePoint) -> biomes.add(Pair.of(noisePoint, () -> biomeKey)));
		return new MultiNoiseBiomeSource(seed, biomes);
	});

	public static final MultiNoiseBiomeSource.Preset MAINTENANCE_TRANSITION_CONCRETE_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier("backrooms:maintenance_transition_concrete_biome_source"), (preset, seed) -> {
		List<Pair<MultiNoiseBiomeSource.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		MAINTENANCE_TRANSITION_CONCRETE_POINTS.forEach((biomeKey, noisePoint) -> biomes.add(Pair.of(noisePoint, () -> biomeKey)));
		return new MultiNoiseBiomeSource(seed, biomes);
	});

	public static final MultiNoiseBiomeSource.Preset CONCRETE_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier("backrooms:concrete_biome_source"), (preset, seed) -> {
		List<Pair<MultiNoiseBiomeSource.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		CONCRETE_POINTS.forEach((biomeKey, noisePoint) -> biomes.add(Pair.of(noisePoint, () -> biomeKey)));
		return new MultiNoiseBiomeSource(seed, biomes);
	});

	public static final MultiNoiseBiomeSource.Preset CONCRETE_TO_PIPES_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier("backrooms:concrete_to_pipes_biome_source"), (preset, seed) -> {
		List<Pair<MultiNoiseBiomeSource.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		CONCRETE_TO_PIPES_POINTS.forEach((biomeKey, noisePoint) -> biomes.add(Pair.of(noisePoint, () -> biomeKey)));
		return new MultiNoiseBiomeSource(seed, biomes);
	});

	public static final MultiNoiseBiomeSource.Preset PIPES_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier("backrooms:pipes_biome_source"), (preset, seed) -> {
		List<Pair<MultiNoiseBiomeSource.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		PIPES_POINTS.forEach((biomeKey, noisePoint) -> biomes.add(Pair.of(noisePoint, () -> biomeKey)));
		return new MultiNoiseBiomeSource(seed, biomes);
	});

	static {
		LOBBY_BIOME_POINTS.put(BackroomsBiomes.LOBBY, new MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		LOBBY_BIOME_POINTS.put(BackroomsBiomes.LOBBY_X, new MixedNoisePoint(0.0F, 0.0F, 0.25F, 0.0F, 0.0F));
		LOBBY_BIOME_POINTS.put(BackroomsBiomes.LOBBY_Z, new MixedNoisePoint(0.0F, 0.0F, -0.3F, 0.0F, 0.0F));
		LOBBY_BIOME_POINTS.put(BackroomsBiomes.LOBBY_EMPTIER, new MixedNoisePoint(0.0F, 0.3F, 0.45F, 0.0F, 0.35F));
		LOBBY_BIOME_POINTS.put(BackroomsBiomes.LOBBY_CLAUSTROPHOBIC, new MixedNoisePoint(0.0F, -0.175F, -0.375F, 0.0F, 0.4F));

		MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_POINTS.put(BackroomsBiomes.LOBBY_LIGHT_FIXTURE, new MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_POINTS.put(BackroomsBiomes.LOBBY_X_LIGHT_FIXTURE, new MixedNoisePoint(0.0F, 0.0F, 0.25F, 0.0F, 0.0F));
		MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_POINTS.put(BackroomsBiomes.LOBBY_Z_LIGHT_FIXTURE, new MixedNoisePoint(0.0F, 0.0F, -0.3F, 0.0F, 0.0F));
		MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_POINTS.put(BackroomsBiomes.LOBBY_EMPTIER_LIGHT_FIXTURE, new MixedNoisePoint(0.0F, 0.3F, 0.45F, 0.0F, 0.35F));
		MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_POINTS.put(BackroomsBiomes.LOBBY_CLAUSTROPHOBIC_LIGHT_FIXTURE, new MixedNoisePoint(0.0F, -0.175F, -0.375F, 0.0F, 0.4F));

		MAINTENANCE_TRANSITION_LOBBY_POINTS.put(BackroomsBiomes.LOBBY_TO_MAINTENANCE_X, new MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		MAINTENANCE_TRANSITION_LOBBY_POINTS.put(BackroomsBiomes.LOBBY_TO_MAINTENANCE_Z, new MixedNoisePoint(0.0F, 0.0F, 0.5F, 0.0F, 0.0F));
		MAINTENANCE_TRANSITION_LOBBY_POINTS.put(BackroomsBiomes.LOBBY_TO_MAINTENANCE_Z, new MixedNoisePoint(0.0F, 0.0F, -0.5F, 0.0F, 0.0F));

		MAINTENANCE_POINTS.put(BackroomsBiomes.MAINTENANCE, new MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		MAINTENANCE_POINTS.put(BackroomsBiomes.MAINTENANCE_LONG_X, new MixedNoisePoint(0.0F, 0.0F, -0.3F, 0.0F, 0.0F));
		MAINTENANCE_POINTS.put(BackroomsBiomes.MAINTENANC_LONG_Z, new MixedNoisePoint(0.0F, 0.0F, 0.45F, 0.0F, 0.15F));

		MAINTENANCE_TRANSITION_CONCRETE_POINTS.put(BackroomsBiomes.MAINTENANCE_TO_CONCRETE_X, new MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		MAINTENANCE_TRANSITION_CONCRETE_POINTS.put(BackroomsBiomes.MAINTENANCE_TO_CONCRETE_Z, new MixedNoisePoint(0.0F, 0.0F, 0.4F, 0.0F, 0.0F));
		MAINTENANCE_TRANSITION_CONCRETE_POINTS.put(BackroomsBiomes.MAINTENANCE_TO_CONCRETE_Z, new MixedNoisePoint(0.0F, 0.0F, -0.65F, 0.0F, 0.1F));

		CONCRETE_POINTS.put(BackroomsBiomes.CONCRETE, new MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		CONCRETE_POINTS.put(BackroomsBiomes.CONCRETE_HALLWAYS_X, new MixedNoisePoint(0.0F, 0.35F, 0.0F, 0.0F, 0.1F));
		CONCRETE_POINTS.put(BackroomsBiomes.CONCRETE_HALLWAYS_Z, new MixedNoisePoint(0.0F, -0.5F, 0.0F, 0.0F, 0.075F));
		CONCRETE_POINTS.put(BackroomsBiomes.CONCRETE_LONG_X, new MixedNoisePoint(0.0F, -0.6F, 0.4F, 0.0F, 0.1F));
		CONCRETE_POINTS.put(BackroomsBiomes.CONCRETE_LONG_Z, new MixedNoisePoint(0.0F, 0.3F, -0.35F, 0.0F, 0.075F));
		CONCRETE_POINTS.put(BackroomsBiomes.EMPTIER_CONCRETE, new MixedNoisePoint(0.5F, 0.0F, 0.4F, 0.0F, 0.0F));

		CONCRETE_TO_PIPES_POINTS.put(BackroomsBiomes.CONCRETE_TO_PIPES_HALLWAYS_Z, new MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		CONCRETE_TO_PIPES_POINTS.put(BackroomsBiomes.CONCRETE_TO_PIPES_HALLWAYS_X, new MixedNoisePoint(0.0F, 0.0F, 0.45F, 0.0F, 0.1F));
		CONCRETE_TO_PIPES_POINTS.put(BackroomsBiomes.CONCRETE_TO_PIPES_HALLWAYS_X, new MixedNoisePoint(0.0F, 0.0F, -0.35F, 0.0F, 0.0F));

		PIPES_POINTS.put(BackroomsBiomes.PIPES, new MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PIPES_POINTS.put(BackroomsBiomes.PIPES_HALLWAYS_X, new MixedNoisePoint(0.0F, -0.4F, 0.0F, 0.0F, -0.125F));
		PIPES_POINTS.put(BackroomsBiomes.PIPES_HALLWAYS_Z, new MixedNoisePoint(0.0F, 0.45F, 0.0F, 0.0F, -0.175F));
		PIPES_POINTS.put(BackroomsBiomes.PIPES_LONG_X, new MixedNoisePoint(0.0F, 0.65F, -0.4F, 0.0F, -0.2F));
		PIPES_POINTS.put(BackroomsBiomes.PIPES_LONG_Z, new MixedNoisePoint(0.0F, -0.4F, 0.25F, 0.0F, -0.175F));
	}

	@Override
	public void method_1473() {
		long seed = this.world.getSeed();
		this.layeredBiomeSource = new BackroomsBiomeSource(seed, LOBBY_BIOME_SOURCE.getBiomeSource(seed), MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_BIOME_SOURCE.getBiomeSource(seed), MAINTENANCE_TRANSITION_LOBBY_BIOME_SOURCE.getBiomeSource(seed), MAINTENANCE_BIOME_SOURCE.getBiomeSource(seed), MAINTENANCE_TRANSITION_CONCRETE_BIOME_SOURCE.getBiomeSource(seed), CONCRETE_BIOME_SOURCE.getBiomeSource(seed), CONCRETE_TO_PIPES_BIOME_SOURCE.getBiomeSource(seed), PIPES_BIOME_SOURCE.getBiomeSource(seed));
		// Dimension Id
		this.field_9616 = 672760;
		// Water Stays
		this.field_9615 = false;
	}

	// Chunk Provider
	@Override
	public ChunkProvider method_1476() {
		return new BackroomsChunkGenerator(this.world, this.world.getSeed());
	}

	@Override
	public float method_1471(long l, float f) {
		return 0.0F;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float[] getBackgroundColor(float skyAngle, float tickDelta) {
		return new float[] { 0.2F, 0.2F, 0.2F, 0.2F };
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Vec3d method_1474(float f, float g) {
		return Vec3d.of(0.23D, 0.23D, 0.23D);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean hasGround() {
		return true;
	}

	@Override
	public boolean method_1478() {
		return false;
	}

	@Override
	public boolean method_1477() {
		return false;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public float getCloudHeight() {
		return Float.NaN;
	}

	@Override
	public boolean method_1469(int i, int j) {
		return false;
	}

	@Override
	public BlockPos getForcedSpawnPoint() {
		return new BlockPos(-1, 101, -1);
	}

	@Override
	public int method_1482() {
		return 50;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean isFogThick(int x, int z) {
		return true;
	}

	@Override
	protected void method_1466() {
		float var1 = 0.05F;

		for (int var2 = 0; var2 <= 15; ++var2) {
			float var3 = 1.0F - (float) var2 / 15.0F;
			this.lightLevelToBrightness[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
		}

	}

	@Override
	public String getName() {
		return "The Backrooms";
	}
}
