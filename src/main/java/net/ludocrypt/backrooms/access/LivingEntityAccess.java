package net.ludocrypt.backrooms.access;

import net.ludocrypt.backrooms.util.BlockPos;

public interface LivingEntityAccess {

	public BlockPos getExitDoorPos();

	public void setExitDoorPos(BlockPos pos);
}
