package net.ludocrypt.backrooms;

import java.util.List;
import java.util.Random;

import net.fabricmc.api.ModInitializer;
import net.ludocrypt.backrooms.init.BackroomsBiomes;
import net.ludocrypt.backrooms.init.BackroomsBlocks;
import net.ludocrypt.backrooms.init.BackroomsRooms;
import net.ludocrypt.backrooms.mixin.IdListAccessor;
import net.ludocrypt.backrooms.mixin.SimpleRegistryAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;

public class Backrooms implements ModInitializer {

	@Override
	public void onInitialize() {
		BackroomsBlocks.init();
		BackroomsBiomes.init();
		BackroomsRooms.init();
	}

	public static Identifier id(String id) {
		return new Identifier("backrooms", id);
	}

	public static Object getRandomRegistry(SimpleRegistry registry, Random random) {
		List<?> list = ((IdListAccessor) ((SimpleRegistryAccessor) registry).getIds()).getList();
		return list.get(random.nextInt(list.size()));
	}

}
