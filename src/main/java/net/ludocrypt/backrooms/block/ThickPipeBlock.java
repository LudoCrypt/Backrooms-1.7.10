package net.ludocrypt.backrooms.block;

import java.util.List;
import java.util.Random;

import net.ludocrypt.backrooms.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.group.ItemGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class ThickPipeBlock extends Block {

	public boolean bounds = false;

	public ThickPipeBlock() {
		super(Material.STONE);
		this.setStrength(6.0F);
		this.setSound(Block.STONE);
		this.setTranslationKey("thickPipe");
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634("backrooms:thick_pipe");
		this.fullBlock = false;
	}

	@Override
	public boolean isFullCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean hasTransperancy() {
		return false;
	}

	@Override
	public String method_8604() {
		return this.method_8603() + "_" + "item";
	}

	@Override
	public void setBlockItemBounds() {
		this.setBoundingBlock(2, 2, 2, 14, 14, 14);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void method_8614(World world, int i, int j, int k, Box box, List list, Entity entity) {
		int meta = world.getBlockMeta(i, j, k);
		String b = encodeBinary(meta);

		this.setBoundingBlock(3, 3, 3, 13, 13, 13);
		super.method_8614(world, i, j, k, box, list, entity);

		if (isNorth(b)) {
			this.setBoundingBlock(3, 3, 0, 13, 13, 3);
			super.method_8614(world, i, j, k, box, list, entity);
			if (world.getBlock(i, j, k - 1).isFullCube()) {
				this.setBoundingBlock(0, 0, 0, 16, 16, 3);
				super.method_8614(world, i, j, k, box, list, entity);
			}
		}
		if (isEast(b)) {
			this.setBoundingBlock(13, 3, 3, 16, 13, 13);
			super.method_8614(world, i, j, k, box, list, entity);
			if (world.getBlock(i + 1, j, k).isFullCube()) {
				this.setBoundingBlock(13, 1, 1, 15, 15, 15);
				super.method_8614(world, i, j, k, box, list, entity);
			}
		}
		if (isSouth(b)) {
			this.setBoundingBlock(3, 3, 13, 13, 13, 16);
			super.method_8614(world, i, j, k, box, list, entity);
			if (world.getBlock(i, j, k + 1).isFullCube()) {
				this.setBoundingBlock(1, 0, 13, 15, 15, 13);
				super.method_8614(world, i, j, k, box, list, entity);
			}
		}
		if (isWest(b)) {
			this.setBoundingBlock(0, 3, 3, 3, 13, 13);
			super.method_8614(world, i, j, k, box, list, entity);
			if (world.getBlock(i - 1, j, k).isFullCube()) {
				this.setBoundingBlock(1, 1, 1, 3, 15, 15);
				super.method_8614(world, i, j, k, box, list, entity);
			}
		}

		this.setBlockItemBounds();
	}

	public void setBoundingBlock(int x, int y, int z, int maxX, int maxY, int maxZ) {
		this.setBoundingBox((float) (((double) x) / 16.0D), (float) (((double) y) / 16.0D), (float) (((double) z) / 16.0D), (float) (((double) maxX) / 16.0D), (float) (((double) maxY) / 16.0D), (float) (((double) maxZ) / 16.0D));
	}

	public String encodeBinary(int b) {
		String binary = Integer.toBinaryString(b);
		while (binary.length() < 4) {
			binary = String.join("", "0", binary);
		}
		return binary;
	}

	public int decodeBinary(String b) {
		return Integer.parseInt(b, 2);
	}

	public String setNorth(String in, boolean b) {
		StringBuilder btb = new StringBuilder(in);
		btb.setCharAt(0, b ? '1' : '0');
		return btb.toString();
	}

	public String setEast(String in, boolean b) {
		StringBuilder btb = new StringBuilder(in);
		btb.setCharAt(1, b ? '1' : '0');
		return btb.toString();
	}

	public String setSouth(String in, boolean b) {
		StringBuilder btb = new StringBuilder(in);
		btb.setCharAt(2, b ? '1' : '0');
		return btb.toString();
	}

	public String setWest(String in, boolean b) {
		StringBuilder btb = new StringBuilder(in);
		btb.setCharAt(3, b ? '1' : '0');
		return btb.toString();
	}

	public boolean isNorth(String in) {
		return in.charAt(0) == '1';
	}

	public boolean isEast(String in) {
		return in.charAt(1) == '1';
	}

	public boolean isSouth(String in) {
		return in.charAt(2) == '1';
	}

	public boolean isWest(String in) {
		return in.charAt(3) == '1';
	}

	public boolean connectsOnlyOne(String in) {
		long count = in.chars().filter(ch -> ch == '1').count();
		return count == 1L;
	}

	@Override
	public int getBlockType() {
		return 9897;
	}

	@Override
	public void neighborUpdate(World world, int i, int j, int k, Block block) {
		this.update(world, i, j, k);
		if (world.getBlock(i + 1, j, k) == this) {
			this.update(world, i + 1, j, k);
		}
		if (world.getBlock(i - 1, j, k) == this) {
			this.update(world, i - 1, j, k);
		}
		if (world.getBlock(i, j, k + 1) == this) {
			this.update(world, i, j, k + 1);
		}
		if (world.getBlock(i, j, k - 1) == this) {
			this.update(world, i, j, k - 1);
		}
	}

	@Override
	public void onPlaced(World world, int i, int j, int k, LivingEntity livingEntity, ItemStack itemStack) {
		this.update(world, i, j, k);
	}

	public void update(World world, int i, int j, int k) {
		String binary = "0000";

		binary = setNorth(binary, world.getBlock(i, j, k - 1) == this || (world.getBlock(i, j, k - 1).isFullCube() && world.getBlock(i + 1, j, k) != this && world.getBlock(i - 1, j, k) != this) || (world.getBlock(i, j, k - 1).isFullCube() && world.getBlock(i + 1, j, k) == this && world.getBlock(i - 1, j, k).equals(Blocks.AIR)) || (world.getBlock(i, j, k - 1).isFullCube() && world.getBlock(i - 1, j, k) == this && world.getBlock(i + 1, j, k).equals(Blocks.AIR)));
		binary = setEast(binary, world.getBlock(i + 1, j, k) == this || (world.getBlock(i + 1, j, k).isFullCube() && world.getBlock(i, j, k + 1) != this && world.getBlock(i, j, k - 1) != this) || (world.getBlock(i + 1, j, k).isFullCube() && world.getBlock(i, j, k + 1) == this && world.getBlock(i, j, k - 1).equals(Blocks.AIR)) || (world.getBlock(i + 1, j, k).isFullCube() && world.getBlock(i, j, k - 1) == this && world.getBlock(i, j, k + 1).equals(Blocks.AIR)));
		binary = setSouth(binary, world.getBlock(i, j, k + 1) == this || (world.getBlock(i, j, k + 1).isFullCube() && world.getBlock(i + 1, j, k) != this && world.getBlock(i - 1, j, k) != this) || (world.getBlock(i, j, k + 1).isFullCube() && world.getBlock(i + 1, j, k) == this && world.getBlock(i - 1, j, k).equals(Blocks.AIR)) || (world.getBlock(i, j, k + 1).isFullCube() && world.getBlock(i - 1, j, k) == this && world.getBlock(i + 1, j, k).equals(Blocks.AIR)));
		binary = setWest(binary, world.getBlock(i - 1, j, k) == this || (world.getBlock(i - 1, j, k).isFullCube() && world.getBlock(i, j, k + 1) != this && world.getBlock(i, j, k - 1) != this) || (world.getBlock(i - 1, j, k).isFullCube() && world.getBlock(i, j, k + 1) == this && world.getBlock(i, j, k - 1).equals(Blocks.AIR)) || (world.getBlock(i - 1, j, k).isFullCube() && world.getBlock(i, j, k - 1) == this && world.getBlock(i, j, k + 1).equals(Blocks.AIR)));

		int meta = decodeBinary(binary);

		world.method_8510(i, j, k, this, meta, 2);
	}

	@Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		if (random.nextDouble() < 0.3) {
			DrywallBlock.displayParticles(world, new BlockPos(i, j, k), random, "coloredSuspended_183_255_252_85", 1.5, 6.5, 0.45, 0.2);
		} else {
			DrywallBlock.displayParticles(world, new BlockPos(i, j, k), random, "coloredSuspended_135_151_255_43", 1.5, 6.5, 0.45, 0.2);
		}
	}
}
