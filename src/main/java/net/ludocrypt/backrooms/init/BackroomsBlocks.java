package net.ludocrypt.backrooms.init;

import net.ludocrypt.backrooms.block.BrittleConcreteBlock;
import net.ludocrypt.backrooms.block.ConcreteBrickBlock;
import net.ludocrypt.backrooms.block.DrywallBlock;
import net.ludocrypt.backrooms.block.FlourescentBlock;
import net.ludocrypt.backrooms.block.MineralTileBlock;
import net.ludocrypt.backrooms.block.PaperedOakBlock;
import net.ludocrypt.backrooms.block.PlasterwallBlock;
import net.ludocrypt.backrooms.block.PolyesterWool;
import net.ludocrypt.backrooms.block.ShalewallBlock;
import net.ludocrypt.backrooms.block.ThickPipeBlock;
import net.minecraft.class_2066;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.group.ItemGroup;

public class BackroomsBlocks {

	public static final Block SHALEWALL = add("shalewall", 200, new ShalewallBlock(), ItemGroup.BUILDING_BLOCKS);
	public static final Block FLOURESCENT_LIGHT = add("flourescent_light", 201, new FlourescentBlock(), ItemGroup.REDSTONE);
	public static final Block POLYESTER_WOOL = add("polyester_wool", 203, new PolyesterWool(), ItemGroup.BUILDING_BLOCKS);
	public static final Block MINERAL_TILE_LEFT = add("mineral_tile_left", 204, new MineralTileBlock("backrooms:mineral_tile_left", "mineralTileLeft"), ItemGroup.BUILDING_BLOCKS);
	public static final Block MINERAL_TILE_RIGHT = add("mineral_tile_right", 205, new MineralTileBlock("backrooms:mineral_tile_right", "mineralTileRight"), ItemGroup.BUILDING_BLOCKS);
	public static final Block PAPERED_OAK = add("papered_oak", 206, new PaperedOakBlock(), ItemGroup.BUILDING_BLOCKS);
	public static final Block PLASTERWALL = add("plasterwall", 207, new PlasterwallBlock("backrooms:plasterwall", "plasterwall"), ItemGroup.BUILDING_BLOCKS);
	public static final Block PLASTERWALL_TOP = add("plasterwall_top", 208, new PlasterwallBlock("backrooms:plasterwall_top", "plasterwallTop"), ItemGroup.BUILDING_BLOCKS);
	public static final Block PLASTERWALL_BOTTOM = add("plasterwall_bottom", 209, new PlasterwallBlock("backrooms:plasterwall_bottom", "plasterwallBottom"), ItemGroup.BUILDING_BLOCKS);
	public static final Block DRYWALL = add("drywall", 210, new DrywallBlock(), ItemGroup.BUILDING_BLOCKS);
	public static final Block THICK_PIPE = add("thick_pipe", 211, new ThickPipeBlock(), ItemGroup.BUILDING_BLOCKS);
	public static final Block CONCRETE_BRICK = add("concrete_brick", 212, new ConcreteBrickBlock("backrooms:concrete_brick", "concreteBrick"), ItemGroup.BUILDING_BLOCKS);
	public static final Block CONCRETE_BRICK_PAINTED = add("concrete_brick_painted", 213, new ConcreteBrickBlock("backrooms:concrete_brick_painted", "concreteBrickBottom"), ItemGroup.BUILDING_BLOCKS);
	public static final Block BRITTLE_CONCRETE = add("brittle_concrete", 214, new BrittleConcreteBlock(), ItemGroup.BUILDING_BLOCKS);

	private static Block add(String name, int id, Block b, ItemGroup group) {
		Block.REGISTRY.register(id, "backrooms:" + name, b);
		Item.REGISTRY.register(id, "backrooms:" + name, new class_2066(b).setItemGroup(group));
		return b;
	}

	public static void init() {

	}

}
