package net.ludocrypt.backrooms.util;

public class MathHelperUpdated {

	public static double perlinFade(double d) {
		return d * d * d * (d * (d * 6.0D - 15.0D) + 10.0D);
	}

	public static double lerp(double delta, double start, double end) {
		return start + delta * (end - start);
	}

	public static double reverseLerp(double place, double start, double end) {
		return (place - start) / (end - start);
	}

	public static float lerpF(double delta, double start, double end) {
		return (float) lerp(delta, start, end);
	}

	public static float reverseLerpF(double place, double start, double end) {
		return (float) reverseLerp(place, start, end);
	}

	public static double mirror(double place, double anchor) {
		return (-place) + (anchor * 2);
	}

	public static float mirrorF(double place, double anchor) {
		return (float) mirror(place, anchor);
	}

	// Distance from center of circle
	public static double DISFC(double cX, double cZ, double pX, double pZ) {
		return Math.sqrt(Math.pow(cX - pX, 2) + Math.pow(cZ - pZ, 2));
	}

	// Distance from 0, 0
	public static double DISFZZ(double pX, double pZ) {
		return DISFC(0, 0, pX, pZ);
	}

	public static double lerp2(double deltaX, double deltaY, double val00, double val10, double val01, double val11) {
		return lerp(deltaY, lerp(deltaX, val00, val10), lerp(deltaX, val01, val11));
	}

	public static double lerp3(double deltaX, double deltaY, double deltaZ, double val000, double val100, double val010, double val110, double val001, double val101, double val011, double val111) {
		return lerp(deltaZ, lerp2(deltaX, deltaY, val000, val100, val010, val110), lerp2(deltaX, deltaY, val001, val101, val011, val111));
	}

	public static double dot(int[] gArr, double x, double y, double z) {
		return (double) gArr[0] * x + (double) gArr[1] * y + (double) gArr[2] * z;
	}

}
