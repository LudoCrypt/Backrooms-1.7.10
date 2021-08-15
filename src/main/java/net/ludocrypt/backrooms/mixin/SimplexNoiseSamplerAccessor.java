package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.util.math.noise.SimplexNoiseSampler;

@Mixin(SimplexNoiseSampler.class)
public interface SimplexNoiseSamplerAccessor {

	@Accessor("field_2171")
	public static int[][] getField_2171() {
		throw new AssertionError();
	}
}
