package net.ludocrypt.backrooms.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;

public class ConcreteBrickBlock extends Block {

	public ConcreteBrickBlock(String name, String translation) {
		super(Material.STONE);
		this.setStrength(3.0F);
		this.setSound(Block.STONE);
		this.setTranslationKey(translation);
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634(name);
	}

}
