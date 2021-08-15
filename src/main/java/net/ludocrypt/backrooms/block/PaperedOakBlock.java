package net.ludocrypt.backrooms.block;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.group.ItemGroup;

public class PaperedOakBlock extends Block {

	public PaperedOakBlock() {
		super(Material.WOOD);
		this.setStrength(1.0F);
		this.setSound(Block.WOOD);
		this.setTranslationKey("paperedOak");
		this.setItemGroup(ItemGroup.BUILDING_BLOCKS);
		this.method_8634("backrooms:papered_oak");
	}

}
