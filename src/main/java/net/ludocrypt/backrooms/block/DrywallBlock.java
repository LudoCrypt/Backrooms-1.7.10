package net.ludocrypt.backrooms.block;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.ludocrypt.backrooms.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DrywallBlock extends Block {

	public DrywallBlock() {
		super(Material.WOOD);
		this.setStrength(1.0F);
		this.setSound(Block.WOOD);
		this.setTranslationKey("drywall");
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634("backrooms:drywall");
	}

	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		displayParticles(world, new BlockPos(i, j, k), random, "coloredSuspended_234_234_234", 3.5, 8.5, 0.3, 0.15);
	}

	@Environment(EnvType.CLIENT)
	public static void displayParticles(World world, BlockPos pos, Random random, String effect, double scale1, double scale2, double commonality, double extraCommonality) {
		if (random.nextDouble() < commonality) {
			int i = pos.getX();
			int j = pos.getY();
			int k = pos.getZ();
			double d = i + MathHelper.nextDouble(random, -scale1, scale1);
			double e = j + MathHelper.nextDouble(random, -scale1, scale1);
			double f = k + MathHelper.nextDouble(random, -scale1, scale1);
			world.playSound(effect, d, e, f, 0.0D, 0.0D, 0.0D);
			BlockPos mutable = pos;

			if (random.nextDouble() < extraCommonality) {
				mutable = new BlockPos(i + MathHelper.nextDouble(random, -6.5, 6.5), j + MathHelper.nextDouble(random, -scale2, scale2), k + MathHelper.nextDouble(random, -scale2, scale2));
				Block blockState = world.getBlock(mutable.x, mutable.y, mutable.z);
				if (!blockState.isFullCube()) {
					world.playSound(effect, mutable.getX() + random.nextDouble(), mutable.getY() + random.nextDouble(), mutable.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
				}
			}

		}
	}

}
