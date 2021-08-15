package net.ludocrypt.backrooms.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;

public class MineralTileBlock extends Block {

	public MineralTileBlock(String name, String translation) {
		super(Material.STONE);
		this.setStrength(4.0F);
		this.setSound(Block.STONE);
		this.setTranslationKey(translation);
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634(name);
	}

}
