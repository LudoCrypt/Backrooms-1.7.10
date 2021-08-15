package net.ludocrypt.backrooms.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;

public class PlasterwallBlock extends Block {

	public PlasterwallBlock(String name, String translation) {
		super(Material.WOOD);
		this.setStrength(1.0F);
		this.setSound(Block.WOOD);
		this.setTranslationKey(translation);
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634(name);
	}

}
