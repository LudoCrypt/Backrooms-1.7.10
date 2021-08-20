package net.ludocrypt.backrooms.util;

import java.util.ArrayList;
import java.util.Iterator;

//Stolen from minecraft code
public class DoublePerlinNoiseSampler {
	private final double amplitude;
	private final OctavePerlinNoiseSampler firstSampler;
	private final OctavePerlinNoiseSampler secondSampler;

	public static DoublePerlinNoiseSampler method_30846(ChunkRandom chunkRandom, int i, ArrayList<Double> doubleList) {
		return new DoublePerlinNoiseSampler(chunkRandom, i, doubleList);
	}

	private DoublePerlinNoiseSampler(ChunkRandom chunkRandom, int i, ArrayList<Double> doubleList) {
		this.firstSampler = OctavePerlinNoiseSampler.method_30847(chunkRandom, i, doubleList);
		this.secondSampler = OctavePerlinNoiseSampler.method_30847(chunkRandom, i, doubleList);
		int j = Integer.MAX_VALUE;
		int k = Integer.MIN_VALUE;
		Iterator<Double> doubleListIterator = doubleList.stream().iterator();

		while (doubleListIterator.hasNext()) {
			double d = doubleListIterator.next();
			int l = doubleList.indexOf(d);
			if (d != 0.0D) {
				j = Math.min(j, l);
				k = Math.max(k, l);
			}
		}

		this.amplitude = 0.16666666666666666D / createAmplitude(k - j);
	}

	private static double createAmplitude(int octaves) {
		return 0.1D * (1.0D + 1.0D / (double) (octaves + 1));
	}

	public double sample(double x, double y, double z) {
		double d = x * 1.0181268882175227D;
		double e = y * 1.0181268882175227D;
		double f = z * 1.0181268882175227D;
		return (this.firstSampler.sample(x, y, z) + this.secondSampler.sample(d, e, f)) * this.amplitude;
	}
}
