package net.ludocrypt.backrooms.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Sets;

import net.minecraft.util.math.MathHelper;

//Stolen from minecraft code
public class OctavePerlinNoiseSampler implements NoiseSampler {
	private final PerlinNoiseSampler[] octaveSamplers;
	private final ArrayList<Double> field_26445;
	private final double field_20659;
	private final double field_20660;

	public OctavePerlinNoiseSampler(ChunkRandom random, IntStream octaves) {
		this(random, octaves.boxed().collect(ImmutableList.toImmutableList()));
	}

	public OctavePerlinNoiseSampler(ChunkRandom random, List<Integer> octaves) {
		this(random, Sets.newHashSet(octaves));
	}

	public static OctavePerlinNoiseSampler method_30847(ChunkRandom chunkRandom, int i, ArrayList<Double> DoubleArrayList) {
		return new OctavePerlinNoiseSampler(chunkRandom, Pair.of(i, DoubleArrayList));
	}

	private static Pair<Integer, ArrayList<Double>> method_30848(Set<Integer> intSortedSet) {
		if (intSortedSet.isEmpty()) {
			throw new IllegalArgumentException("Need some octaves!");
		} else {
			int i = -intSortedSet.toArray(new Integer[0])[0];
			int j = intSortedSet.toArray(new Integer[0])[intSortedSet.size() - 1];
			int k = i + j + 1;
			if (k < 1) {
				throw new IllegalArgumentException("Total number of octaves needs to be >= 1");
			} else {
				ArrayList<Double> DoubleArrayList = new ArrayList<Double>(k);
				for (int pi = 0; pi < k; pi++) {
					DoubleArrayList.add(0.0);
				}
				Iterator<Integer> intBidirectionalIterator = intSortedSet.iterator();

				while (intBidirectionalIterator.hasNext()) {
					int l = intBidirectionalIterator.next();
					DoubleArrayList.set(l + i, 1.0D);
				}

				return Pair.of(-i, DoubleArrayList);
			}
		}
	}

	private OctavePerlinNoiseSampler(ChunkRandom random, Set<Integer> octaves) {
		this(random, method_30848(octaves));
	}

	private OctavePerlinNoiseSampler(ChunkRandom chunkRandom, Pair<Integer, ArrayList<Double>> pair) {
		int i = pair.getFirst();
		this.field_26445 = pair.getSecond();
		PerlinNoiseSampler PerlinNoiseSampler = new PerlinNoiseSampler(chunkRandom);
		int j = this.field_26445.size();
		int k = -i;
		this.octaveSamplers = new PerlinNoiseSampler[j];
		if (k >= 0 && k < j) {
			double d = this.field_26445.get(k);
			if (d != 0.0D) {
				this.octaveSamplers[k] = PerlinNoiseSampler;
			}
		}

		for (int l = k - 1; l >= 0; --l) {
			if (l < j) {
				double e = this.field_26445.get(l);
				if (e != 0.0D) {
					this.octaveSamplers[l] = new PerlinNoiseSampler(chunkRandom);
				} else {
					chunkRandom.consume(262);
				}
			} else {
				chunkRandom.consume(262);
			}
		}

		if (k < j - 1) {
			long m = (long) (PerlinNoiseSampler.sample(0.0D, 0.0D, 0.0D, 0.0D, 0.0D) * 9.223372036854776E18D);
			ChunkRandom chunkRandom2 = new ChunkRandom(m);

			for (int n = k + 1; n < j; ++n) {
				if (n >= 0) {
					double f = this.field_26445.get(n);
					if (f != 0.0D) {
						this.octaveSamplers[n] = new PerlinNoiseSampler(chunkRandom2);
					} else {
						chunkRandom2.consume(262);
					}
				} else {
					chunkRandom2.consume(262);
				}
			}
		}

		this.field_20660 = Math.pow(2.0D, (double) (-k));
		this.field_20659 = Math.pow(2.0D, (double) (j - 1)) / (Math.pow(2.0D, (double) j) - 1.0D);
	}

	public double sample(double x, double y, double z) {
		return this.sample(x, y, z, 0.0D, 0.0D, false);
	}

	public double sample(double x, double y, double z, double d, double e, boolean bl) {
		double f = 0.0D;
		double g = this.field_20660;
		double h = this.field_20659;

		for (int i = 0; i < this.octaveSamplers.length; ++i) {
			PerlinNoiseSampler PerlinNoiseSampler = this.octaveSamplers[i];
			if (PerlinNoiseSampler != null) {
				f += this.field_26445.get(i) * PerlinNoiseSampler.sample(maintainPrecision(x * g), bl ? -PerlinNoiseSampler.originY : maintainPrecision(y * g), maintainPrecision(z * g), d * g, e * g) * h;
			}

			g *= 2.0D;
			h /= 2.0D;
		}

		return f;
	}

	@Nullable
	public PerlinNoiseSampler getOctave(int octave) {
		return this.octaveSamplers[this.octaveSamplers.length - 1 - octave];
	}

	public static double maintainPrecision(double d) {
		return d - (double) MathHelper.lfloor(d / 3.3554432E7D + 0.5D) * 3.3554432E7D;
	}

	public double sample(double x, double y, double d, double e) {
		return this.sample(x, y, 0.0D, d, e, false);
	}

	public static <P> List<Pair<P, OctavePerlinNoiseSampler>> createNoise(List<P> aspects, long seed) {
		Builder<Pair<P, OctavePerlinNoiseSampler>> builder = new Builder<Pair<P, OctavePerlinNoiseSampler>>();

		for (Iterator<P> var4 = aspects.iterator(); var4.hasNext(); ++seed) {
			P layer = var4.next();
			builder.add(new Pair<P, OctavePerlinNoiseSampler>(layer, new OctavePerlinNoiseSampler(new ChunkRandom(seed), ImmutableList.of(-4))));
		}

		return builder.build();
	}
}
