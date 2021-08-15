package net.ludocrypt.backrooms.mixin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.ludocrypt.backrooms.world.TheBackroomsDimension;
import net.ludocrypt.backrooms.world.biome.BackroomsBiomeSource;
import net.minecraft.util.ProgressListener;
import net.minecraft.world.LayeredBiomeSource;
import net.minecraft.world.level.storage.SaveData;
import net.minecraft.world.level.storage.SaveStorage;

@Mixin(SaveData.class)
public abstract class SaveDataMixin extends SaveStorage {

	@Shadow
	@Final
	private static Logger LOGGER;

	public SaveDataMixin(File file) {
		super(file);
	}

	private ArrayList<?> backroomsArray;
	private File backroomsWorld;

	@Inject(method = "method_2022", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/SaveData;method_1907(Ljava/io/File;Ljava/lang/Iterable;Lnet/minecraft/world/LayeredBiomeSource;IILnet/minecraft/util/ProgressListener;)V", shift = Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
	private void BACKROOMS_saveBackrooms(String string, ProgressListener progressListener, CallbackInfoReturnable<Boolean> ci, ArrayList<?> var3, ArrayList<?> var4, ArrayList<?> var5, File var6, File var7, File var8, int var9) {
		this.method_1907(new File(backroomsWorld, "region"), backroomsArray, new BackroomsBiomeSource(672760L, TheBackroomsDimension.LOBBY_BIOME_SOURCE.getBiomeSource(672760L), TheBackroomsDimension.MAINTENANCE_TRANSITION_LOBBY_TRANSITION_LOBBY_BIOME_SOURCE.getBiomeSource(672760L), TheBackroomsDimension.MAINTENANCE_TRANSITION_LOBBY_BIOME_SOURCE.getBiomeSource(672760L), TheBackroomsDimension.MAINTENANCE_BIOME_SOURCE.getBiomeSource(672760L), TheBackroomsDimension.MAINTENANCE_TRANSITION_CONCRETE_BIOME_SOURCE.getBiomeSource(672760L), TheBackroomsDimension.CONCRETE_BIOME_SOURCE.getBiomeSource(672760L), TheBackroomsDimension.CONCRETE_TO_PIPES_BIOME_SOURCE.getBiomeSource(672760L), TheBackroomsDimension.PIPES_BIOME_SOURCE.getBiomeSource(672760L)), var3.size() + var4.size() + var5.size(), var9, progressListener);
	}

	@Inject(method = "method_2022", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/SaveData;method_1908(Ljava/io/File;Ljava/util/Collection;)V", shift = Shift.BEFORE, ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
	private void BACKROOMS_saveBackroomsPut(String string, ProgressListener progressListener, CallbackInfoReturnable<Boolean> ci, ArrayList<?> var3, ArrayList<?> var4, ArrayList<?> var5, File var6) {
		backroomsArray = new ArrayList<Object>();
		backroomsWorld = new File(var6, "DIM672760");
	}

	@Inject(method = "method_2022", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/SaveData;method_1908(Ljava/io/File;Ljava/util/Collection;)V", shift = Shift.AFTER, ordinal = 0))
	private void BACKROOMS_saveBackroomsDo(String string, ProgressListener progressListener, CallbackInfoReturnable<Boolean> ci) {
		if (backroomsWorld.exists()) {
			this.method_1908(backroomsWorld, backroomsArray);
		}
	}

	@ModifyVariable(method = "method_2022", at = @At("STORE"), ordinal = 0)
	private int BACKROOMS_modifyVar9(int in) {
		return in + backroomsArray.size();
	}

	@Shadow
	abstract void method_1907(File file, Iterable<?> iterable, LayeredBiomeSource layeredBiomeSource, int i, int j, ProgressListener progressListener);

	@Shadow
	abstract void method_1908(File file, Collection<?> collection);

}
