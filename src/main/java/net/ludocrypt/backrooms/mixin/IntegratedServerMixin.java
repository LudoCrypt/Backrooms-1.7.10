package net.ludocrypt.backrooms.mixin;

import java.io.File;
import java.net.Proxy;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.SaveHandler;
import net.minecraft.world.level.LevelGeneratorType;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin extends MinecraftServer {

	@Unique
	private int worldIn;

	public IntegratedServerMixin(File gameDir, Proxy proxy) {
		super(gameDir, proxy);
	}

	@Inject(method = "Lnet/minecraft/server/integrated/IntegratedServer;setupWorld(Ljava/lang/String;Ljava/lang/String;JLnet/minecraft/world/level/LevelGeneratorType;Ljava/lang/String;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/server/integrated/IntegratedServer;worlds:[Lnet/minecraft/server/world/ServerWorld;", opcode = Opcodes.PUTFIELD, ordinal = 0, shift = Shift.AFTER))
	private void BACKROOMS_addMoreDimensions(String world, String string, long l, LevelGeneratorType generatorType, String string2, CallbackInfo ci) {
		this.worlds = new ServerWorld[worlds.length + 1];
	}

	@ModifyArg(method = "Lnet/minecraft/server/integrated/IntegratedServer;setupWorld(Ljava/lang/String;Ljava/lang/String;JLnet/minecraft/world/level/LevelGeneratorType;Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2569;<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/world/SaveHandler;Ljava/lang/String;ILnet/minecraft/world/level/LevelInfo;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/profiler/Profiler;)V"), index = 3)
	private int BACKROOMS_modifyId(int in) {
		if (worldIn == 3) {
			return 672760;
		}
		return in;
	}

	@Inject(method = "Lnet/minecraft/server/integrated/IntegratedServer;setupWorld(Ljava/lang/String;Ljava/lang/String;JLnet/minecraft/world/level/LevelGeneratorType;Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2569;<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/world/SaveHandler;Ljava/lang/String;ILnet/minecraft/world/level/LevelInfo;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/profiler/Profiler;)V", shift = Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
	private void BACKROOMS_storeId(String world, String string, long l, LevelGeneratorType generatorType, String string2, CallbackInfo ci, SaveHandler var7, int var8, int var9) {
		this.worldIn = var8;
	}
}
