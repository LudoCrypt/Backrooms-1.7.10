package net.ludocrypt.backrooms.client;

import net.ludocrypt.backrooms.Backrooms;
import net.ludocrypt.backrooms.mixin.MinecraftClientAccessor;
import net.ludocrypt.backrooms.mixin.MusicTrackerAccessor;
import net.ludocrypt.backrooms.util.MathHelperUpdated;
import net.ludocrypt.backrooms.world.biome.BackroomsBiomeSource;
import net.ludocrypt.backrooms.world.biome.base.ConcreteBaseBiome;
import net.ludocrypt.backrooms.world.biome.base.MaintenanceBaseBiome;
import net.ludocrypt.backrooms.world.biome.base.PipesBaseBiome;
import net.minecraft.class_2388;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundCategory;
import net.minecraft.client.sound.TickableSoundInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;

public class BackroomsAmbienceSoundInstance implements TickableSoundInstance {

	private final MinecraftClient client;
	private double delta = 0.0D;
	public Identifier idBefore;

	public BackroomsAmbienceSoundInstance(MinecraftClient client) {
		this.client = client;
		idBefore = getIdentifier();
	}

	public boolean isOn() {
		return BackroomsBiomeSource.isInside(getX(), getZ(), 1100);
	}

	@Override
	public Identifier getIdentifier() {
		if (biomeAt() instanceof PipesBaseBiome) {
			return Backrooms.id("ambient.game.backrooms.pipes.bg");
		}
		if (biomeAt() instanceof ConcreteBaseBiome) {
			return Backrooms.id("ambient.game.backrooms.concrete.bg");
		}
		if (biomeAt() instanceof MaintenanceBaseBiome) {
			return Backrooms.id("ambient.game.backrooms.maintenance.bg");
		}
		if (!isOn()) {
			return Backrooms.id("ambient.game.backrooms.lobby.bg_off");
		}
		return Backrooms.id("ambient.game.backrooms.lobby.bg_on");
	}

	@Override
	public boolean isRepeatable() {
		return (((MusicTrackerAccessor) ((MinecraftClientAccessor) client).getMusicTracker()).getField_10765() == null);
	}

	@Override
	public int getRepeatDelay() {
		return 0;
	}

	@Override
	public float getVolume() {
		return MathHelperUpdated.lerpF(delta, 1.0D, 0.4D);
	}

	public Biome biomeAt() {
		return client.player.world.dimension.world.getBiomeAt(client.player.getBlockPos().x, client.player.getBlockPos().z);
	}

	@Override
	public float getPitch() {
		return 1.0F;
	}

	@Override
	public float getX() {
		return (float) client.player.x;
	}

	@Override
	public float getY() {
		return (float) client.player.y;
	}

	@Override
	public float getZ() {
		return (float) client.player.z;
	}

	@Override
	public class_2388 getAttenuationType() {
		return class_2388.field_10756;
	}

	@Override
	public void tick() {
		if (((MusicTrackerAccessor) ((MinecraftClientAccessor) client).getMusicTracker()).getField_10765() != null) {
			delta += 0.015D;
		} else {
			delta -= 0.015D;
		}
		delta = MathHelper.clamp(delta, 0.0D, 1.0D);
		if (idBefore != getIdentifier()) {
			idBefore = getIdentifier();
		}
		this.client.getSoundManager().updateSoundVolume(SoundCategory.AMBIENT, getVolume());

	}

	@Override
	public boolean isDone() {
		return false;
	}

}
