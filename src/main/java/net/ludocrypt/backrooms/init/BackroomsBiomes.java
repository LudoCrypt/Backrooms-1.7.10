package net.ludocrypt.backrooms.init;

import java.util.List;

import com.google.common.collect.Lists;

import net.ludocrypt.backrooms.world.biome.ClaustrophobicLobby;
import net.ludocrypt.backrooms.world.biome.ClaustrophobicLobbyLightFixture;
import net.ludocrypt.backrooms.world.biome.ConcreteBiome;
import net.ludocrypt.backrooms.world.biome.ConcreteHallwaysX;
import net.ludocrypt.backrooms.world.biome.ConcreteHallwaysZ;
import net.ludocrypt.backrooms.world.biome.ConcreteLongX;
import net.ludocrypt.backrooms.world.biome.ConcreteLongZ;
import net.ludocrypt.backrooms.world.biome.ConcreteToPipesHallwaysX;
import net.ludocrypt.backrooms.world.biome.ConcreteToPipesHallwaysZ;
import net.ludocrypt.backrooms.world.biome.EmptierConcreteBiome;
import net.ludocrypt.backrooms.world.biome.EmptierLobby;
import net.ludocrypt.backrooms.world.biome.EmptierLobbyLightFixture;
import net.ludocrypt.backrooms.world.biome.LobbyBiome;
import net.ludocrypt.backrooms.world.biome.LobbyBiomeLightFixture;
import net.ludocrypt.backrooms.world.biome.LobbyToMaintenanceX;
import net.ludocrypt.backrooms.world.biome.LobbyToMaintenanceZ;
import net.ludocrypt.backrooms.world.biome.LongXLobby;
import net.ludocrypt.backrooms.world.biome.LongXLobbyLightFixture;
import net.ludocrypt.backrooms.world.biome.LongZLobby;
import net.ludocrypt.backrooms.world.biome.LongZLobbyLightFixture;
import net.ludocrypt.backrooms.world.biome.MaintenanceBiome;
import net.ludocrypt.backrooms.world.biome.MaintenanceLongX;
import net.ludocrypt.backrooms.world.biome.MaintenanceLongZ;
import net.ludocrypt.backrooms.world.biome.MaintenanceToConcreteX;
import net.ludocrypt.backrooms.world.biome.MaintenanceToConcreteZ;
import net.ludocrypt.backrooms.world.biome.PipesBiome;
import net.ludocrypt.backrooms.world.biome.PipesHallwaysX;
import net.ludocrypt.backrooms.world.biome.PipesHallwaysZ;
import net.ludocrypt.backrooms.world.biome.PipesLongX;
import net.ludocrypt.backrooms.world.biome.PipesLongZ;
import net.minecraft.world.biome.Biome;

public class BackroomsBiomes {

	public static final List<Biome> BIOMES = Lists.newArrayList();

	// The Lobby
	public static final Biome LOBBY = add(new LobbyBiome(40));
	public static final Biome LOBBY_X = add(new LongXLobby(41));
	public static final Biome LOBBY_Z = add(new LongZLobby(42));
	public static final Biome LOBBY_EMPTIER = add(new EmptierLobby(43));
	public static final Biome LOBBY_CLAUSTROPHOBIC = add(new ClaustrophobicLobby(44));

	// The Lobby 2 electric boogaloo
	public static final Biome LOBBY_LIGHT_FIXTURE = add(new LobbyBiomeLightFixture(45));
	public static final Biome LOBBY_X_LIGHT_FIXTURE = add(new LongXLobbyLightFixture(46));
	public static final Biome LOBBY_Z_LIGHT_FIXTURE = add(new LongZLobbyLightFixture(47));
	public static final Biome LOBBY_EMPTIER_LIGHT_FIXTURE = add(new EmptierLobbyLightFixture(48));
	public static final Biome LOBBY_CLAUSTROPHOBIC_LIGHT_FIXTURE = add(new ClaustrophobicLobbyLightFixture(49));

	// Transition space 1
	public static final Biome LOBBY_TO_MAINTENANCE_X = add(new LobbyToMaintenanceX(50));
	public static final Biome LOBBY_TO_MAINTENANCE_Z = add(new LobbyToMaintenanceZ(51));

	// Maintenance
	public static final Biome MAINTENANCE = add(new MaintenanceBiome(52));
	public static final Biome MAINTENANCE_LONG_X = add(new MaintenanceLongX(53));
	public static final Biome MAINTENANC_LONG_Z = add(new MaintenanceLongZ(54));

	// Transition space 2
	public static final Biome MAINTENANCE_TO_CONCRETE_X = add(new MaintenanceToConcreteX(55));
	public static final Biome MAINTENANCE_TO_CONCRETE_Z = add(new MaintenanceToConcreteZ(56));

	// Transition space 3
	public static final Biome CONCRETE = add(new ConcreteBiome(57));
	public static final Biome CONCRETE_LONG_X = add(new ConcreteLongX(58));
	public static final Biome CONCRETE_LONG_Z = add(new ConcreteLongZ(59));
	public static final Biome CONCRETE_HALLWAYS_X = add(new ConcreteHallwaysX(60));
	public static final Biome CONCRETE_HALLWAYS_Z = add(new ConcreteHallwaysZ(61));
	public static final Biome EMPTIER_CONCRETE = add(new EmptierConcreteBiome(62));

	// Transition space 4
	public static final Biome CONCRETE_TO_PIPES_HALLWAYS_X = add(new ConcreteToPipesHallwaysX(63));
	public static final Biome CONCRETE_TO_PIPES_HALLWAYS_Z = add(new ConcreteToPipesHallwaysZ(64));

	// Pipes
	public static final Biome PIPES = add(new PipesBiome(65));
	public static final Biome PIPES_LONG_X = add(new PipesLongX(66));
	public static final Biome PIPES_LONG_Z = add(new PipesLongZ(67));
	public static final Biome PIPES_HALLWAYS_X = add(new PipesHallwaysX(68));
	public static final Biome PIPES_HALLWAYS_Z = add(new PipesHallwaysZ(69));

	private static Biome add(Biome b) {
		BIOMES.add(b);
		return b;
	}

	@SuppressWarnings("unchecked")
	public static void init() {
		Biome.BIOMESET.removeAll(BIOMES);
	}

}
