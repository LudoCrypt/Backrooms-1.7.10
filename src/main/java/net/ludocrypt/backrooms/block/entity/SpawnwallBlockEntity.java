package net.ludocrypt.backrooms.block.entity;

import java.util.UUID;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.Tag;

public class SpawnwallBlockEntity extends BlockEntity {
	private PlayerEntity player;

	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		this.player = world.getPlayerByUuid(toUuid(tag.get("player")));
	}

	public void toTag(CompoundTag tag) {
		super.toTag(tag);
		tag.put("player", fromUuid(player.getUuid()));
	}

	public PlayerEntity getPlayer() {
		return this.player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
		this.markDirty();
	}

	public static IntArrayTag fromUuid(UUID uuid) {
		return new IntArrayTag(toIntArray(uuid));
	}

	public static UUID toUuid(Tag tag) {
		if (tag.getType() != new IntArrayTag(new int[] {}).getType()) {
			throw new IllegalArgumentException("Expected UUID-Tag to be of type " + new IntArrayTag(new int[] {}).getType() + ", but found " + tag.getType() + ".");
		} else {
			int[] is = ((IntArrayTag) tag).getIntArray();
			if (is.length != 4) {
				throw new IllegalArgumentException("Expected UUID-Array to be of length 4, but found " + is.length + ".");
			} else {
				return toUuid(is);
			}
		}
	}

	public static UUID toUuid(int[] array) {
		return new UUID((long) array[0] << 32 | (long) array[1] & 4294967295L, (long) array[2] << 32 | (long) array[3] & 4294967295L);
	}

	public static int[] toIntArray(UUID uuid) {
		long l = uuid.getMostSignificantBits();
		long m = uuid.getLeastSignificantBits();
		return toIntArray(l, m);
	}

	public static int[] toIntArray(long uuidMost, long uuidLeast) {
		return new int[] { (int) (uuidMost >> 32), (int) uuidMost, (int) (uuidLeast >> 32), (int) uuidLeast };
	}
}
