package net.ludocrypt.backrooms.mixin;

import java.io.File;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.ludocrypt.backrooms.world.TheBackroomsDimension;
import net.minecraft.class_629;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.chunk.ChunkWriter;
import net.minecraft.world.chunk.ThreadedAnvilChunkStorage;
import net.minecraft.world.dimension.Dimension;

@Mixin(class_629.class)
public abstract class ChunkWorldSaverMixin extends WorldSaveHandler {

	public ChunkWorldSaverMixin(File savesFolder, String worldName, boolean createPlayerDataDir) {
		super(savesFolder, worldName, createPlayerDataDir);
	}

	@Inject(method = "getChunkWriter", at = @At("HEAD"), cancellable = true)
	private void BACKROOMS_getBackroomsChunkWriter(Dimension dim, CallbackInfoReturnable<ChunkWriter> ci) {
		if (dim instanceof TheBackroomsDimension) {
			File backroomsWorld = new File(this.getWorldFolder(), "DIM672760");
			backroomsWorld.mkdirs();
			ci.setReturnValue(new ThreadedAnvilChunkStorage(backroomsWorld));
		}
	}

}
