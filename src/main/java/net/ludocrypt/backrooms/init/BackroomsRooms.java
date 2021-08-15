package net.ludocrypt.backrooms.init;

import net.ludocrypt.backrooms.world.rooms.ClaustrophobicRoom;
import net.ludocrypt.backrooms.world.rooms.ConcreteHallwaysXRoom;
import net.ludocrypt.backrooms.world.rooms.ConcreteHallwaysZRoom;
import net.ludocrypt.backrooms.world.rooms.ConcreteRoom;
import net.ludocrypt.backrooms.world.rooms.EmptierConcreteRoom;
import net.ludocrypt.backrooms.world.rooms.EmptierRoom;
import net.ludocrypt.backrooms.world.rooms.EmptyRoom;
import net.ludocrypt.backrooms.world.rooms.LongerHallwaysXRoom;
import net.ludocrypt.backrooms.world.rooms.LongerHallwaysZRoom;
import net.ludocrypt.backrooms.world.rooms.MaintenanceRoom;
import net.ludocrypt.backrooms.world.rooms.PipesHallwaysXRoom;
import net.ludocrypt.backrooms.world.rooms.PipesHallwaysZRoom;
import net.ludocrypt.backrooms.world.rooms.PipesRoom;
import net.ludocrypt.backrooms.world.rooms.RoomLayout;
import net.ludocrypt.backrooms.world.rooms.WalledRoom;
import net.minecraft.class_2409;
import net.minecraft.util.registry.SimpleRegistry;

public class BackroomsRooms {

	public static final SimpleRegistry REGISTRY = new class_2409("empty");

	// The Lobby
	public static final EmptyRoom EMPTY = add(new EmptyRoom());
	public static final WalledRoom WALLED = add(new WalledRoom());
	public static final WalledRoom WALLED_LONG_X = add(new WalledRoom(2, "long_x", 7.5D, 0.0D));
	public static final WalledRoom WALLED_LONG_Z = add(new WalledRoom(3, "long_z", 0.0D, 7.5D));
	public static final EmptierRoom EMPTIER_WALLED = add(new EmptierRoom(4, "emptier", 2.35D, 1.75D));
	public static final ClaustrophobicRoom CLAUSTROPHOBIC_WALLED = add(new ClaustrophobicRoom(5, "claustrophobic", 5.65D, 3.75D));

	// Transition Space 1
	public static final LongerHallwaysXRoom LOBBY_TO_MAINTENANCE_X = add(new LongerHallwaysXRoom(6, "lobby_to_maintenance_x", 7.5D, 0.0D));
	public static final LongerHallwaysZRoom LOBBY_TO_MAINTENANCE_Z = add(new LongerHallwaysZRoom(7, "lobby_to_maintenance_z", 0.0D, 7.5D));

	// Maintenance
	public static final WalledRoom DRYWALL = add(new MaintenanceRoom(8, "drywall", 1.0D, 1.0D, BackroomsBlocks.DRYWALL));
	public static final WalledRoom DRYWALL_LONG_X = add(new MaintenanceRoom(9, "drywall_long_x", 6.35D, 0.0D, BackroomsBlocks.DRYWALL));
	public static final WalledRoom DRYWALL_LONG_Z = add(new MaintenanceRoom(10, "drywall_long_z", 0.0D, 5.75D, BackroomsBlocks.DRYWALL));

	// Transition Space 3
	public static final LongerHallwaysXRoom MAINTENANCE_TO_CONCRETE_X = add(new LongerHallwaysXRoom(11, "maintenance_to_concrete_x", 7.5D, 0.0D, BackroomsBlocks.DRYWALL, BackroomsBlocks.DRYWALL, BackroomsBlocks.DRYWALL));
	public static final LongerHallwaysZRoom MAINTENANCE_TO_CONCRETE_Z = add(new LongerHallwaysZRoom(12, "maintenance_to_concrete_z", 0.0D, 7.5D, BackroomsBlocks.DRYWALL, BackroomsBlocks.DRYWALL, BackroomsBlocks.DRYWALL));

	// Transition Space 4
	public static final WalledRoom CONCRETE = add(new ConcreteRoom(13, "concrete", 1.0D, 1.0D, BackroomsBlocks.CONCRETE_BRICK_PAINTED, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom CONCRETE_LONG_X = add(new ConcreteRoom(14, "concrete_long_x", 8.25D, 0.0D, BackroomsBlocks.CONCRETE_BRICK_PAINTED, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom CONCRETE_LONG_Z = add(new ConcreteRoom(15, "concrete_long_z", 0.0D, 6.5D, BackroomsBlocks.CONCRETE_BRICK_PAINTED, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom CONCRETE_HALLWAYS_X = add(new ConcreteHallwaysXRoom(14, "concrete_hallways_x", 8.25D, 0.0D, BackroomsBlocks.CONCRETE_BRICK_PAINTED, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom CONCRETE_HALLWAYS_Z = add(new ConcreteHallwaysZRoom(15, "concrete_hallways_z", 0.0D, 6.5D, BackroomsBlocks.CONCRETE_BRICK_PAINTED, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom EMPTIER_CONCRETE = add(new EmptierConcreteRoom(16, "emptier_concrete", 3.5D, 3.5D, BackroomsBlocks.CONCRETE_BRICK_PAINTED, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.CONCRETE_BRICK, BackroomsBlocks.BRITTLE_CONCRETE));

	// Pipes
	public static final WalledRoom PIPES = add(new PipesRoom(17, "pipes", 1.0D, 1.0D, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom PIPES_LONG_X = add(new PipesRoom(18, "pipes_long_x", 12.25D, 0.0D, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom PIPES_LONG_Z = add(new PipesRoom(19, "pipes_long_z", 0.0D, 11.5D, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom PIPES_HALLWAYS_X = add(new PipesHallwaysXRoom(20, "pipes_hallways_x", 10.25D, 0.0D, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE));
	public static final WalledRoom PIPES_HALLWAYS_Z = add(new PipesHallwaysZRoom(21, "pipes_hallways_z", 0.0D, 13.5D, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE, BackroomsBlocks.BRITTLE_CONCRETE));

	private static <R extends RoomLayout> R add(R layout) {
		REGISTRY.register(layout.getId(), layout.getName(), layout);
		return layout;
	}

	public static void init() {

	}

}
