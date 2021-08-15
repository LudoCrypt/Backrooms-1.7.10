package net.ludocrypt.backrooms.world.biome;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.Biome;

@SuppressWarnings("all")
public class BackroomsBiomeSource extends MultiNoiseBiomeSource {

	public final MultiNoiseBiomeSource lobby;
	public final MultiNoiseBiomeSource lobby_lobby_maintenance_transition_transition;
	public final MultiNoiseBiomeSource lobby_maintenance_transition;
	public final MultiNoiseBiomeSource maintenance;
	public final MultiNoiseBiomeSource maintenance_concrete_transition;
	public final MultiNoiseBiomeSource concrete;
	public final MultiNoiseBiomeSource concrete_to_pipes_transition;
	public final MultiNoiseBiomeSource pipes;

	public BackroomsBiomeSource(long seed, MultiNoiseBiomeSource lobby, MultiNoiseBiomeSource lobby_lobby_maintenance_transition_transition, MultiNoiseBiomeSource lobby_maintenance_transition, MultiNoiseBiomeSource maintenance, MultiNoiseBiomeSource maintenance_concrete_transition, MultiNoiseBiomeSource concrete, MultiNoiseBiomeSource concrete_to_pipes_transition, MultiNoiseBiomeSource pipes) {
		super(seed, addLists(lobby.biomePoints, lobby_lobby_maintenance_transition_transition.biomePoints, lobby_maintenance_transition.biomePoints, maintenance.biomePoints, maintenance_concrete_transition.biomePoints, concrete.biomePoints, concrete_to_pipes_transition.biomePoints, pipes.biomePoints));
		this.lobby = lobby;
		this.lobby_lobby_maintenance_transition_transition = lobby_lobby_maintenance_transition_transition;
		this.lobby_maintenance_transition = lobby_maintenance_transition;
		this.maintenance = maintenance;
		this.maintenance_concrete_transition = maintenance_concrete_transition;
		this.concrete = concrete;
		this.concrete_to_pipes_transition = concrete_to_pipes_transition;
		this.pipes = pipes;
	}

	@Override
	public Biome getBiomeAt(int biomeX, int biomeZ) {
		if (isInside(biomeX, biomeZ, 800)) {
			return lobby.getBiomeAt(biomeX, biomeZ);
		} else if (isInside(biomeX, biomeZ, 1200)) {
			return lobby_lobby_maintenance_transition_transition.getBiomeAt(biomeX, biomeZ);
		} else if (isInside(biomeX, biomeZ, 1400)) {
			return lobby_maintenance_transition.getBiomeAt(biomeX, biomeZ);
		} else if (isInside(biomeX, biomeZ, 2000)) {
			return maintenance.getBiomeAt(biomeX, biomeZ);
		} else if (isInside(biomeX, biomeZ, 2600)) {
			return maintenance_concrete_transition.getBiomeAt(biomeX, biomeZ);
		} else if (isInside(biomeX, biomeZ, 3200)) {
			return concrete.getBiomeAt(biomeX, biomeZ);
		} else if (isInside(biomeX, biomeZ, 3500)) {
			return concrete_to_pipes_transition.getBiomeAt(biomeX, biomeZ);
		} else {
			return pipes.getBiomeAt(biomeX, biomeZ);
		}
	}

	@Override
	public List getBiomes() {
		List lobbyBiomes = getBiomesFromBiomePoint(lobby.biomePoints);
		List lobby_lobby_maintenance_transition_transitionBiomes = getBiomesFromBiomePoint(lobby_lobby_maintenance_transition_transition.biomePoints);
		List lobby_maintenance_transitionBiomes = getBiomesFromBiomePoint(lobby_maintenance_transition.biomePoints);
		List maintenanceBiome = getBiomesFromBiomePoint(maintenance.biomePoints);
		List maintenance_concrete_transitionBiomes = getBiomesFromBiomePoint(maintenance_concrete_transition.biomePoints);
		List concreteBiomes = getBiomesFromBiomePoint(concrete.biomePoints);
		List concrete_to_pipes_transitionBiomes = getBiomesFromBiomePoint(concrete_to_pipes_transition.biomePoints);
		List pipesBiomes = getBiomesFromBiomePoint(pipes.biomePoints);
		List biomes = new ArrayList();
		biomes.addAll(lobbyBiomes);
		biomes.addAll(lobby_lobby_maintenance_transition_transitionBiomes);
		biomes.addAll(lobby_maintenance_transitionBiomes);
		biomes.addAll(maintenanceBiome);
		biomes.addAll(maintenance_concrete_transitionBiomes);
		biomes.addAll(concreteBiomes);
		biomes.addAll(concrete_to_pipes_transitionBiomes);
		biomes.addAll(pipesBiomes);
		return biomes;
	}

	public static boolean isInside(double x, double z, double width, double height) {
		return (Math.pow(x, 2) / Math.pow(width, 2)) + (Math.pow(z, 2) / Math.pow(height, 2)) <= 1;
	}

	public static boolean isInside(double x, double z, double radius) {
		return isInside(x, z, radius, radius);
	}

	public static double distToCircle(double x, double z, double radius) {
		return Math.abs(Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2))) - radius;
	}

	public static double distToCircleEdge(double x, double z, double radius) {
		if (isInside(x, z, radius)) {
			return 0.0D;
		}
		return distToCircle(x, z, radius);
	}

	public static <T> List<T> addLists(List<T>... lists) {
		List<T> returnList = new ArrayList<T>();
		for (int i = 0; i < lists.length; i++) {
			returnList.addAll(lists[i]);
		}
		return returnList;
	}
}
