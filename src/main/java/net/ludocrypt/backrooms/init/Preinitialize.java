package net.ludocrypt.backrooms.init;

import com.chocohead.mm.api.ClassTinkerers;
import com.chocohead.mm.api.EnumAdder;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.ludocrypt.backrooms.Backrooms;

public class Preinitialize implements Runnable {

	public static final String THE_LOBBY_MUSIC = "THE_LOBBY_BACKROOMS";
	public static final String MAINTENANCE_MUSIC = "MAINTENANCE_BACKROOMS";
	public static final String PIPES_MUSIC = "PIPES_BACKROOMS";

	@Override
	public void run() {
		MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {

			// Music Types
			EnumAdder adder = ClassTinkerers.enumBuilder(remapper.mapClassName("intermediary", "net.minecraft.class_2391"), "L" + remapper.mapClassName("intermediary", "net.minecraft.class_1605") + ";", "I", "I");
			adder.addEnum(THE_LOBBY_MUSIC, () -> new Object[] { Backrooms.id("music.game.backrooms.lobby"), 1200, 3600 });
			adder.addEnum(MAINTENANCE_MUSIC, () -> new Object[] { Backrooms.id("music.game.backrooms.maintenance"), 1200, 3600 });
			adder.addEnum(PIPES_MUSIC, () -> new Object[] { Backrooms.id("music.game.backrooms.pipes"), 1200, 3600 });
			adder.build();
		}
	}

}
