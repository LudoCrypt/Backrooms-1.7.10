package net.ludocrypt.backrooms.world.biome;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.ludocrypt.backrooms.util.ChunkRandom;
import net.ludocrypt.backrooms.util.DoublePerlinNoiseSampler;
import net.ludocrypt.backrooms.util.Pair;
import net.minecraft.class_604;
import net.minecraft.util.Identifier;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.LayeredBiomeSource;
import net.minecraft.world.biome.Biome;

@SuppressWarnings("rawtypes")
public class MultiNoiseBiomeSource extends LayeredBiomeSource {
	public static final MultiNoiseBiomeSource.NoiseParameters DEFAULT_NOISE_PARAMETERS = new MultiNoiseBiomeSource.NoiseParameters(-7, ImmutableList.of(1.0D, 1.0D));
	public final MultiNoiseBiomeSource.NoiseParameters temperatureNoiseParameters;
	public final MultiNoiseBiomeSource.NoiseParameters humidityNoiseParameters;
	public final MultiNoiseBiomeSource.NoiseParameters altitudeNoiseParameters;
	public final MultiNoiseBiomeSource.NoiseParameters weirdnessNoiseParameters;
	public final DoublePerlinNoiseSampler temperatureNoise;
	public final DoublePerlinNoiseSampler humidityNoise;
	public final DoublePerlinNoiseSampler altitudeNoise;
	public final DoublePerlinNoiseSampler weirdnessNoise;
	public final List<Pair<MixedNoisePoint, Supplier<Biome>>> biomePoints;
	public final long seed;

	public MultiNoiseBiomeSource(long seed, List<Pair<MixedNoisePoint, Supplier<Biome>>> biomePoints) {
		this(seed, biomePoints, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS, DEFAULT_NOISE_PARAMETERS);
	}

	public MultiNoiseBiomeSource(long seed, List<Pair<MixedNoisePoint, Supplier<Biome>>> biomePoints, MultiNoiseBiomeSource.NoiseParameters temperatureNoiseParameters, MultiNoiseBiomeSource.NoiseParameters humidityNoiseParameters, MultiNoiseBiomeSource.NoiseParameters altitudeNoiseParameters, MultiNoiseBiomeSource.NoiseParameters weirdnessNoiseParameters) {
		super();
		this.seed = seed;
		this.temperatureNoiseParameters = temperatureNoiseParameters;
		this.humidityNoiseParameters = humidityNoiseParameters;
		this.altitudeNoiseParameters = altitudeNoiseParameters;
		this.weirdnessNoiseParameters = weirdnessNoiseParameters;
		this.temperatureNoise = DoublePerlinNoiseSampler.method_30846(new ChunkRandom(seed), temperatureNoiseParameters.getFirstOctave(), temperatureNoiseParameters.getAmplitudes());
		this.humidityNoise = DoublePerlinNoiseSampler.method_30846(new ChunkRandom(seed + 1L), humidityNoiseParameters.getFirstOctave(), humidityNoiseParameters.getAmplitudes());
		this.altitudeNoise = DoublePerlinNoiseSampler.method_30846(new ChunkRandom(seed + 2L), altitudeNoiseParameters.getFirstOctave(), altitudeNoiseParameters.getAmplitudes());
		this.weirdnessNoise = DoublePerlinNoiseSampler.method_30846(new ChunkRandom(seed + 3L), weirdnessNoiseParameters.getFirstOctave(), weirdnessNoiseParameters.getAmplitudes());
		this.biomePoints = biomePoints;
	}

	public Biome getBiomeAt(int biomeX, int biomeZ) {
		MixedNoisePoint mixedNoisePoint = new MixedNoisePoint((float) this.temperatureNoise.sample(biomeX, 0, biomeZ), (float) this.humidityNoise.sample(biomeX, 0, biomeZ), (float) this.altitudeNoise.sample(biomeX, 0, biomeZ), (float) this.weirdnessNoise.sample(biomeX, 0, biomeZ), 0.0F);
		return this.biomePoints.stream().min(Comparator.comparing((pair) -> {
			return pair.getFirst().calculateDistanceTo(mixedNoisePoint);
		})).map(Pair::getSecond).map(Supplier::get).orElse(Biome.field_9163);
	}

	public Biome[] method_573(Biome[] biomes, int i, int j, int k, int l) {
		if (biomes == null || biomes.length < k * l) {
			biomes = new Biome[k * l];
		}

		Arrays.fill(biomes, 0, k * l, getBiomeAt(i, j));
		return biomes;
	}

	public float[] method_572(float[] fs, int x, int z, int w, int h) {
		class_604.method_1824();
		if (fs == null || fs.length < w * h) {
			fs = new float[w * h];
		}

		Biome biome = getBiomeAt(x, z);

		for (int var7 = 0; var7 < w * h; ++var7) {
			try {
				float var8 = (float) biome.getDownfall() / 65536.0F;
				if (var8 > 1.0F) {
					var8 = 1.0F;
				}

				fs[var7] = var8;
			} catch (Throwable var11) {
				CrashReport var9 = CrashReport.create(var11, "Invalid Biome id");
				CrashReportSection var10 = var9.addElement("DownfallBlock");
				var10.addElement("biome id", var7);
				var10.addElement("downfalls[] size", fs.length);
				var10.addElement("x", x);
				var10.addElement("z", z);
				var10.addElement("w", w);
				var10.addElement("h", h);
				throw new CrashException(var9);
			}
		}

		return fs;
	}

	public Biome[] method_576(Biome[] biomes, int i, int j, int k, int l) {
		if (biomes == null || biomes.length < k * l) {
			biomes = new Biome[k * l];
		}

		Arrays.fill(biomes, 0, k * l, getBiomeAt(i, j));
		return biomes;
	}

	public Biome[] method_574(Biome[] biomes, int i, int j, int k, int l, boolean bl) {
		return this.method_576(biomes, i, j, k, l);
	}

	public Vec3i method_569(int i, int j, int k, List list, Random random) {
		return list.contains(getBiomeAt(i, j)) ? new Vec3i(i - k + random.nextInt(k * 2 + 1), 0, j - k + random.nextInt(k * 2 + 1)) : null;
	}

	public boolean isValid(int x, int z, int radius, List biomes) {
		return biomes.contains(getBiomeAt(x, z));
	}

	@Override
	public List getBiomes() {
		return getBiomesFromBiomePoint(biomePoints);
	}

	public static List getBiomesFromBiomePoint(List<Pair<MixedNoisePoint, Supplier<Biome>>> biomePoints) {
		return (biomePoints.stream().map(Pair::getSecond)).map(Supplier::get).collect(ImmutableList.toImmutableList());
	}

	public static class NoiseParameters {
		public final int firstOctave;
		public final DoubleList amplitudes;

		public NoiseParameters(int firstOctave, List<Double> amplitudes) {
			this.firstOctave = firstOctave;
			this.amplitudes = new DoubleArrayList(amplitudes);
		}

		public int getFirstOctave() {
			return this.firstOctave;
		}

		public DoubleList getAmplitudes() {
			return this.amplitudes;
		}
	}

	public static class Preset {
		public static final Map<Identifier, MultiNoiseBiomeSource.Preset> BY_IDENTIFIER = Maps.newHashMap();
		public final Identifier id;
		public final BiFunction<MultiNoiseBiomeSource.Preset, Long, MultiNoiseBiomeSource> biomeSourceFunction;

		public Preset(Identifier id, BiFunction<MultiNoiseBiomeSource.Preset, Long, MultiNoiseBiomeSource> biomeSourceFunction) {
			this.id = id;
			this.biomeSourceFunction = biomeSourceFunction;
			BY_IDENTIFIER.put(id, this);
		}

		public MultiNoiseBiomeSource getBiomeSource(long seed) {
			return this.biomeSourceFunction.apply(this, seed);
		}
	}

	public static final class Instance {
		public final MultiNoiseBiomeSource.Preset preset;
		public final long seed;

		public Instance(MultiNoiseBiomeSource.Preset preset, long seed) {
			this.preset = preset;
			this.seed = seed;
		}

		public MultiNoiseBiomeSource.Preset getPreset() {
			return this.preset;
		}

		public long getSeed() {
			return this.seed;
		}

		public MultiNoiseBiomeSource getBiomeSource() {
			return this.preset.getBiomeSource(this.seed);
		}
	}

	public static class MixedNoisePoint {
		public final float temperature;
		public final float humidity;
		public final float altitude;
		public final float weirdness;
		public final float weight;

		public MixedNoisePoint(float temperature, float humidity, float altitude, float weirdness, float weight) {
			this.temperature = temperature;
			this.humidity = humidity;
			this.altitude = altitude;
			this.weirdness = weirdness;
			this.weight = weight;
		}

		public boolean equals(Object object) {
			if (this == object) {
				return true;
			} else if (object != null && this.getClass() == object.getClass()) {
				MixedNoisePoint mixedNoisePoint = (MixedNoisePoint) object;
				if (Float.compare(mixedNoisePoint.temperature, this.temperature) != 0) {
					return false;
				} else if (Float.compare(mixedNoisePoint.humidity, this.humidity) != 0) {
					return false;
				} else if (Float.compare(mixedNoisePoint.altitude, this.altitude) != 0) {
					return false;
				} else {
					return Float.compare(mixedNoisePoint.weirdness, this.weirdness) == 0;
				}
			} else {
				return false;
			}
		}

		public int hashCode() {
			int i = this.temperature != 0.0F ? Float.floatToIntBits(this.temperature) : 0;
			i = 31 * i + (this.humidity != 0.0F ? Float.floatToIntBits(this.humidity) : 0);
			i = 31 * i + (this.altitude != 0.0F ? Float.floatToIntBits(this.altitude) : 0);
			i = 31 * i + (this.weirdness != 0.0F ? Float.floatToIntBits(this.weirdness) : 0);
			return i;
		}

		public float calculateDistanceTo(MixedNoisePoint other) {
			return (this.temperature - other.temperature) * (this.temperature - other.temperature) + (this.humidity - other.humidity) * (this.humidity - other.humidity) + (this.altitude - other.altitude) * (this.altitude - other.altitude) + (this.weirdness - other.weirdness) * (this.weirdness - other.weirdness) + (this.weight - other.weight) * (this.weight - other.weight);
		}
	}
}
