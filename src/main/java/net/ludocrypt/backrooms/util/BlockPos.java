package net.ludocrypt.backrooms.util;

public class BlockPos extends net.minecraft.util.math.BlockPos {

	public BlockPos(int x, int y, int z) {
		super(x, y, z);
	}

	public BlockPos(double x, double y, double z) {
		this((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public BlockPos add(int x, int y, int z) {
		return new BlockPos(this.x + x, this.y + y, this.z + z);
	}

	public BlockPos up() {
		return up(1);
	}

	public BlockPos up(int i) {
		return add(0, i, 0);
	}

	public BlockPos down() {
		return up(-1);
	}

	public BlockPos down(int i) {
		return up(-i);
	}

	public BlockPos south() {
		return south(1);
	}

	public BlockPos south(int i) {
		return add(0, 0, i);
	}

	public BlockPos north() {
		return south(-1);
	}

	public BlockPos north(int i) {
		return south(-i);
	}

	public BlockPos east() {
		return east(1);
	}

	public BlockPos east(int i) {
		return add(i, 0, 0);
	}

	public BlockPos west() {
		return east(-1);
	}

	public BlockPos west(int i) {
		return east(-i);
	}

}
