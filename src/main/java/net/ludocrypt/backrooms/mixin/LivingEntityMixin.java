package net.ludocrypt.backrooms.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ludocrypt.backrooms.access.LivingEntityAccess;
import net.ludocrypt.backrooms.block.ShalewallBlock;
import net.ludocrypt.backrooms.util.BlockPos;
import net.ludocrypt.backrooms.world.TheBackroomsDimension;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityAccess {

	BlockPos exitDoorPos = new BlockPos(0, -1, 0);

	public LivingEntityMixin(World world) {
		super(world);
	}

	@Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", shift = Shift.AFTER))
	private void BACKROOMS_setInBackrooms(CallbackInfo ci) {
		if (((LivingEntity) (Object) this) instanceof PlayerEntity) {
			if (this.isInsideWall() && world.getBlock((int) Math.floor(this.x), (int) Math.floor(this.y), (int) Math.floor(this.z)) instanceof ShalewallBlock) {
				if (this.exitDoorPos.y == -1) {
					this.teleportToDimension(0);
					this.positAfterTeleport(this.world.getSpawnPos().x, this.world.getSpawnPos().y, this.world.getSpawnPos().z);
				} else {
					this.teleportToDimension(0);
					this.positAfterTeleport(exitDoorPos.x, exitDoorPos.y, exitDoorPos.z);
				}
			} else {
				if (!(this.world.dimension instanceof TheBackroomsDimension)) {
					if (world.random.nextDouble() < 0.03) {
						if (this.ridingEntity == null && this.rider == null && !world.isClient) {
							this.teleportToDimension(672760);
							this.positAfterTeleport((this.x - 100) % 200, 101, (this.z - 100) % 200);
						}
					}
				} else {
					if (random.nextBoolean() && random.nextBoolean() && random.nextBoolean() && random.nextBoolean()) {
						double x = (this.x * 3) + 1;
						double z = (this.z * 3) + 1;
						this.positAfterTeleport(x + 0.5, 101, z + 0.5);
					} else {
						double x = (world.random.nextBoolean() ? world.random.nextBoolean() ? this.x : -this.x : world.random.nextBoolean() ? this.z : -this.z) + 1;
						double z = (world.random.nextBoolean() ? world.random.nextBoolean() ? this.x : -this.x : world.random.nextBoolean() ? this.z : -this.z) + 1;
						this.positAfterTeleport(x + 0.5, 101, z + 0.5);
					}
					double p = 0;
					while (this.isInsideWall() && p < 25) {
						this.positAfterTeleport(this.x + 1.5, 101, this.z + 1.5);
						p++;
					}
				}
			}
		}
	}

	@Inject(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V", ordinal = 0, shift = Shift.AFTER))
	private void BACKROOMS_baseTick(CallbackInfo ci) {
		if (((LivingEntity) (Object) this) instanceof PlayerEntity) {

			if (this.world.dimension instanceof TheBackroomsDimension && this.y > 104) {
				this.positAfterTeleport(this.x, 101.999, this.z);
			}

			if (exitDoorPos.y == -1) {
				this.exitDoorPos = new BlockPos(this.x, this.y, this.z);
			}
		}
	}

	@Shadow
	public abstract void positAfterTeleport(double d, double e, double f);

	@Override
	public BlockPos getExitDoorPos() {
		return exitDoorPos;
	}

	@Override
	public void setExitDoorPos(BlockPos pos) {
		this.exitDoorPos = pos;
	}
}
