package net.ludocrypt.backrooms.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.ludocrypt.backrooms.block.ThickPipeBlock;
import net.ludocrypt.backrooms.world.biome.base.BackroomsBiome;
import net.minecraft.class_2336;
import net.minecraft.block.Block;
import net.minecraft.world.WorldView;

@Mixin(class_2336.class)
public abstract class RenderBlockMixin {

	@Shadow
	public WorldView field_10326;

	@Inject(method = "method_9420", at = @At("HEAD"), cancellable = true)
	private void BACKROOMS_renderPipe(Block block, int i, int j, int k, CallbackInfoReturnable<Boolean> ci) {
		if (block.getBlockType() == 9897) {
			ci.setReturnValue(this.BACKROOMS_renderPipe((ThickPipeBlock) block, i, j, k));
		}
	}

	@Unique
	public boolean BACKROOMS_renderPipe(ThickPipeBlock block, int i, int j, int k) {
		int meta = field_10326.getBlockMeta(i, j, k);
		String b = block.encodeBinary(meta);

		this.setBoundingBlock(3, 3, 3, 13, 13, 13);
		this.method_9448(block, i, j, k);

		if (block.isNorth(b)) {
			this.setBoundingBlock(3, 3, 0, 13, 13, 3);
			this.method_9448(block, i, j, k);
			if (field_10326.getBlock(i, j, k - 1).isFullCube()) {
				this.setBoundingBlock(1, 1, 0, 15, 15, 2);
				this.method_9448(block, i, j, k);
			}
		}
		if (block.isEast(b)) {
			this.setBoundingBlock(13, 3, 3, 16, 13, 13);
			this.method_9448(block, i, j, k);
			if (field_10326.getBlock(i + 1, j, k).isFullCube()) {
				this.setBoundingBlock(14, 1, 1, 16, 15, 15);
				this.method_9448(block, i, j, k);
			}
		}
		if (block.isSouth(b)) {
			this.setBoundingBlock(3, 3, 13, 13, 13, 16);
			this.method_9448(block, i, j, k);
			if (field_10326.getBlock(i, j, k + 1).isFullCube()) {
				this.setBoundingBlock(1, 1, 14, 15, 15, 16);
				this.method_9448(block, i, j, k);
			}
		}
		if (block.isWest(b)) {
			this.setBoundingBlock(0, 3, 3, 3, 13, 13);
			this.method_9448(block, i, j, k);
			if (field_10326.getBlock(i - 1, j, k).isFullCube()) {
				this.setBoundingBlock(0, 1, 1, 2, 15, 15);
				this.method_9448(block, i, j, k);
			}
		}

		Random random = BackroomsBiome.getRandomOfPos(i, j, k, 241689931L);

		if ((block.connectsOnlyOne(b) || random.nextDouble() < 0.0125) && field_10326.getBlock(i, j + 1, k).isFullCube()) {
			this.setBoundingBlock(1, 13, 1, 15, 16, 15);
			this.method_9448(block, i, j, k);
		}

		return true;
	}

	@Unique
	public void setBoundingBlock(int x, int y, int z, int maxX, int maxY, int maxZ) {
		this.method_9366((float) (((double) x) / 16.0D), (float) (((double) y) / 16.0D), (float) (((double) z) / 16.0D), (float) (((double) maxX) / 16.0D), (float) (((double) maxY) / 16.0D), (float) (((double) maxZ) / 16.0D));
	}

	@Shadow
	public abstract boolean method_9448(Block block, int i, int j, int k);

	@Shadow
	public abstract void method_9366(double d, double e, double f, double g, double h, double i);
}
